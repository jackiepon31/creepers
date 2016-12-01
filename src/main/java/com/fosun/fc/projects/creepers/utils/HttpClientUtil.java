/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.downloader.HttpClientGenerator;

import us.codecraft.webmagic.utils.HttpConstant;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * <p>
 * description: httpClient 工具类
 * </p>
 * 
 * @author MaXin
 * @since 2016年10月18日
 * @see
 */
public class HttpClientUtil {

    private static Map<String, CloseableHttpClient> clientPostPool = new HashMap<String, CloseableHttpClient>();

    private static CloseableHttpClient getCloseableHttpClient(String name){
        CloseableHttpClient client = clientPostPool.get(name);
        if (client == null) {
            HttpClientGenerator generator = new HttpClientGenerator();
            client = generator.getClient(null);
            clientPostPool.put(BaseConstant.SUPPER_MAN_CLIENT, client);
        }
        return client;
    }
    public static String supperManPost(String imageData) {
        CloseableHttpClient client = getCloseableHttpClient(BaseConstant.SUPPER_MAN_CLIENT);
        CloseableHttpResponse httpResponse = null;
        HttpRequestBase supperManPost = recvByteSupperManPost(imageData);
        try {
            httpResponse = client.execute(supperManPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String content = getContent(null,httpResponse);
                System.out.println("\n=====>>> content:"+content);
                return content;
            }else {
                System.err.println("\n=====>>> 请求打码超人错误！返回码："+statusCode);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static void supperManError(String imgid) {
        CloseableHttpClient client = getCloseableHttpClient(BaseConstant.SUPPER_MAN_CLIENT);
        CloseableHttpResponse httpResponse = null;
        HttpRequestBase supperManPost = reportErrorSupperManPost(imgid);
        try {
            httpResponse = client.execute(supperManPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String content = getContent(null,httpResponse);
                System.out.println("\n=====>>> content:"+content);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpRequestBase recvByteSupperManPost(String imgdata) {
        Map<String, String> nameValuePairMap = new HashMap<String, String>();
        nameValuePairMap.put("username", "20161012");
        nameValuePairMap.put("password", "123456");
        nameValuePairMap.put("softId", "55147");
        nameValuePairMap.put("imgdata", imgdata);
        return getRequestByMethod(HttpConstant.Method.POST,nameValuePairMap,"http://api2.sz789.net:88/RecvByte.ashx");
        
    }
    private static HttpRequestBase reportErrorSupperManPost(String imgid) {
        Map<String, String> nameValuePairMap = new HashMap<String, String>();
        nameValuePairMap.put("username", "20161012");
        nameValuePairMap.put("password", "123456");
        nameValuePairMap.put("imgid", imgid);
        return getRequestByMethod(HttpConstant.Method.POST,nameValuePairMap,"http://api2.sz789.net:88/ReportError.ashx");
        
    }

    protected static HttpRequestBase getRequestByMethod(String method, Map<String, String> nameValuePairMap,String url) {
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
            // default get
            return new HttpGet(url);
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
            HttpPost post = new HttpPost(url);
            NameValuePair[] valuePairs = new NameValuePair[nameValuePairMap.size()];
            Set<Entry<String, String>> entrySet = nameValuePairMap.entrySet();
            int index = 0;
            for (Entry<String, String> entry : entrySet) {
                valuePairs[index] = new BasicNameValuePair(entry.getKey(), entry.getValue());
                index++;
            }
            if (valuePairs != null && valuePairs.length > 0) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                Collections.addAll(list, valuePairs);
                post.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
            }
            return post;
        } else if (method.equalsIgnoreCase(HttpConstant.Method.HEAD)) {
            return new HttpHead(url);
        } else if (method.equalsIgnoreCase(HttpConstant.Method.PUT)) {
            return new HttpPut(url);
        } else if (method.equalsIgnoreCase(HttpConstant.Method.DELETE)) {
            return new HttpDelete(url);
        } else if (method.equalsIgnoreCase(HttpConstant.Method.TRACE)) {
            return new HttpTrace(url);
        }
        throw new IllegalArgumentException("Illegal HTTP Method " + method);
    }

    
    public static  String getContent(String charset, HttpResponse httpResponse) throws IOException {
        if (charset == null) {
            byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
            String htmlCharset = getHtmlCharset(httpResponse, contentBytes);
            if (htmlCharset != null) {
                return new String(contentBytes, htmlCharset);
            } else {
                return new String(contentBytes);
            }
        } else {
            return IOUtils.toString(httpResponse.getEntity().getContent(), charset);
        }
    }

    public static String getHtmlCharset(HttpResponse httpResponse, byte[] contentBytes) throws IOException {
        String charset;
        // charset
        // 1、encoding in http header Content-Type
        String value = httpResponse.getEntity().getContentType().getValue();
        charset = UrlUtils.getCharset(value);
        if (StringUtils.isNotBlank(charset)) {
            return charset;
        }
        // use default charset to decode first time
        Charset defaultCharset = Charset.defaultCharset();
        String content = new String(contentBytes, defaultCharset.name());
        // 2、charset in meta
        if (StringUtils.isNotEmpty(content)) {
            Document document = Jsoup.parse(content);
            Elements links = document.select("meta");
            for (Element link : links) {
                // 2.1、html4.01 <meta http-equiv="Content-Type"
                // content="text/html; charset=UTF-8" />
                String metaContent = link.attr("content");
                String metaCharset = link.attr("charset");
                if (metaContent.indexOf("charset") != -1) {
                    metaContent = metaContent.substring(metaContent.indexOf("charset"), metaContent.length());
                    charset = metaContent.split("=")[1];
                    break;
                }
                // 2.2、html5 <meta charset="UTF-8" />
                else if (StringUtils.isNotEmpty(metaCharset)) {
                    charset = metaCharset;
                    break;
                }
            }
        }
        // 3、todo use tools as cpdetector for content decode
        return charset;
    }
    
    /**
     * <p>
     * description:
     * </p>
     * 
     * @param args
     * @author Administrator
     * @see
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
