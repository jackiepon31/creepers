/*
 * Copyright (c) 2009 - 2015, Aven's Lab. All rights reserved.
 *                   http://www.ocrking.com
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *  $Id: OcrKing.java,v 0.1 2014/10/29 23:50:18 $
 * The Java script for OcrKing Api
 * By Aven <Aven@OcrKing.Com>
 * Welcome to follow us 
 * http://weibo.com/OcrKing
 * http://t.qq.com/OcrKing
 * Warning! 
 * Before running this script , you should modify some parameter
 * within the post data according to what you wanna do
 */

package com.fosun.fc.projects.creepers.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public final class OcrKing {	 

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// the url below can not be changed !!
		String apiUrl = "http://lab.ocrking.com/ok.html";
		// replace the word KEY below with your apiKey obtained by Email
		String apiKey = "0a09ebda8a9d338c49hELjXKrSQgoXWngv04pnNXwF3uRt1omqcXOXFQRMDrxD2UYroeApaOBctTnW";
		// you need to specify the full path of image you wanna OCR		
//		String filePath="E:/image/6f7f7ed4-3828-4a46-b411-2a4ac4d6dfff.jpg";			

		Map<String, String> dataMap = new HashMap<String, String>();

		// you need to modify parameters according to OcrKing Api Document 
		dataMap.put("service", "OcrKingForCaptcha");
		dataMap.put("language", "eng");
		dataMap.put("charset", "7");
		// 如果不传递原始url到type或乱传一个地址到type 结果很可能就是错的
        // 如果想禁止后台做任何预处理  type可以设为 http://www.nopreprocess.com
        // 如果确实不确定验证码图片的原url  type可以设为 http://www.unknown.com  此时后台只进行常用预处理
		//此demo中type值只针对此demo中的图片，其它网站图片请不要用此值
		@SuppressWarnings("unused")
        SimpleDateFormat sf = new SimpleDateFormat("EEE%20MMM%20dd%20yyyy%20HH:mm:ss",Locale.ENGLISH);
        //sf.format(new Date())+"%20GMT%200800 (中国标准时间)";
		dataMap.put("type", "http://gsxt.gzgs.gov.cn/search!generateCode.shtml?validTag=searchImageCode&1467265246123");
		dataMap.put("url", "http://gsxt.gzgs.gov.cn/search!generateCode.shtml?validTag=searchImageCode&1467265246123");
		// you need to modify parameters according to OcrKing Api Document 

		dataMap.put("apiKey", apiKey);
		Map<String, String> fileMap = new HashMap<String, String>();
//		fileMap.put("ocrfile", filePath);
		String ret = postMultipart(apiUrl, dataMap, fileMap);
		System.out.println(ret);
	}

	/**
	 * post data with file uploading
	 * @param urlStr  the address to upload
	 * @param dataMap post data
	 * @param fileMap file to upload
	 * @return xml result
	 */
	public static String postMultipart(String urlStr, Map<String, String> dataMap, Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String boundary = "----------------------------OcrKing_Client_Aven_s_Lab";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Referer", "http://lab.ocrking.com/?javaclient0.1)");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20100101 Firefox/8.0");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// data   
			if (dataMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = dataMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file  
			if (fileMap != null) {
				Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
					strBuf.append("Content-Type:application/octet-stream\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// handle the response 
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("error " + urlStr);
			//e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}


}
