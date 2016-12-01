package com.fosun.fc.projects.creepers.getResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

/**
 * 
 * <p>
 * 生成CreepersConstant中 tableColumn  数据库表对照常量
 * </p>
 * 
 * @author MaXin
 * @since 2016-7-14 15:57:44
 * @see
 */
public class GenerTableColumn {

    public static void main(String[] args) {
        File file = new File("D:/tableColumn/now");
        String[] fileList = file.list();
        for (String string : fileList) {
            String importFilePath = file.getPath().replaceAll("\\\\", "/") + "/" + string;
            System.out.println("===================>>>>开始导入:" + importFilePath);
            readFile(importFilePath, System.getProperty("user.dir").replaceAll("\\\\", "/")
                    + "/src/main/java/com/fosun/fc/projects/creepers/constant/CreepersConstant.java");
            System.out.println("===================>>>>导入成功:" + importFilePath);

        }
    }
    public static String addContent(String str) {

        String[] arr = str.split("\t");
        StringBuffer result = new StringBuffer();
        if (arr.length == 2) {
            result.append("//").append(arr[1]).append("\n");
        }
        result.append(arr[0]).append("(\"").append(TranslateMethodUtil.toLower(arr[0])).append("\")").append(",")
                .append("\n");
        // System.out.println(result);
        return result.toString();
    }

    public static String addTop(String str) {
        String[] arr = str.split("\t");
        StringBuffer result = new StringBuffer();
        result.append("public static enum ")
                .append(TranslateMethodUtil.firstWordToUpper(TranslateMethodUtil.toLower(arr[0]))).append("Column")
                .append(" {");
        if (arr.length == 2) {
            result.append("//").append(arr[1]);
        }
        result.append("\n");
        // System.out.println(result.toString());
        return result.toString();
    }

    public static String addBottom(String str) {
        String[] arr = str.split("\t");
        StringBuffer result = new StringBuffer();
        result.append("private ").append(TranslateMethodUtil.firstWordToUpper(TranslateMethodUtil.toLower(arr[0])))
                .append("Column").append(" (String string) {").append("\n");
        result.append("this.value = string;").append("\n");
        result.append("}").append("\n");
        result.append("private String value;").append("\n");
        result.append("public String getValue() {").append("\n");
        result.append("return value;").append("\n");
        result.append("}").append("\n");
        result.append("\n }");
        // System.out.println(result.toString());
        return result.toString();
    }

    public static void readFile(String filePath, String targeFilePath) {
        File file = new File(filePath);
        boolean isFirst = true;
        StringBuffer result = new StringBuffer();
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                String topLine = "";
                while ((line = br.readLine()) != null) {
                    if (isFirst) {
                        topLine = line;
                        result.append(addTop(line));
                        isFirst = false;
                        continue;
                    }
                    result.append(addContent(line));
                }
                result = new StringBuffer(result.toString().subSequence(0, result.length() - 2) + ";");
                result.append(addBottom(topLine));
                br.close();
                System.out.println("===============================>>读取拼写完成。");

                PrintWriter printWriter = new PrintWriter(new FileWriter(new File(targeFilePath), true));
                printWriter.write(result.toString());
                printWriter.flush();
                printWriter.close();
                System.out.println("===============================>>输出完成。");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
