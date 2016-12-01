package com.fosun.fc.projects.creepers.getResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

/**
 * 
 *<p>
 * 生成CreepersConstant中  tableName  数据库表对照常量
 *</p>
 * @author MaXin
 * @since 2016-7-14 16:00:17
 * @see
 */
public class GenerTableName {

    public static void main(String[] args) {

        File file = new File("D:/tableColumn/tCreepersListFund/");
        String methodName = "TableNamesFund";
        System.out.println("===================>>>>开始导入:" + methodName);
        readFile(file, BaseConstant.JavaFilePath.CREEPERS_CONSTANT.getValue(), methodName);
        System.out.println("===================>>>>导入成功:" + methodName);

    }

    @SuppressWarnings("resource")
    private static void readFile(File file2, String targeFilePath, String methodName) {
        String rootPath = file2.getPath().replaceAll("\\\\", "/");
        String[] fileList = file2.list();
        StringBuffer result = new StringBuffer();
        result.append(addTop(methodName));
        for (String filePath : fileList) {
            
            File file = new File(rootPath+"/"+filePath);
            if (file.exists()) {
                try {
                    result.append(addContent(new BufferedReader(new FileReader(file)).readLine()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        result = new StringBuffer(result.toString().subSequence(0, result.length() - 2) + ";");
        result.append(addBottom(methodName));
        System.out.println("===============================>>读取拼写完成。");

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(new File(targeFilePath), true));
            printWriter.write(result.toString());
            printWriter.flush();
            printWriter.close();
            System.out.println("===============================>>输出完成。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String addContent(String str) {
        String[] arr = str.split("\t");
        StringBuffer result = new StringBuffer();
        if (arr.length == 2) {
            result.append("//").append(arr[1]).append("\n");
        }
        result.append(arr[0]).append("(\"").append(TranslateMethodUtil.toLower(arr[0])).append("\",false)").append(",").append("\n");
        // System.out.println(result);
        return result.toString();
    }

    public static String addTop(String str) {
        StringBuffer result = new StringBuffer();
        result.append("public static enum ").append(str).append(" {");
        result.append("\n");
        System.out.println(result.toString());
        return result.toString();
    }

    public static String addBottom(String str) {
        StringBuffer result = new StringBuffer();
        result.append("\n\n");
        result.append("private ").append(str).append(" (String string, boolean isList) {").append("\n");
        result.append("this.value = string;").append("\n");
        result.append("this.isList = isList;").append("\n");
        result.append("}").append("\n");
        result.append("private String value;").append("\n");
        result.append("public String getMapKey() {").append("\n");
        result.append("return value;").append("\n");
        result.append("}").append("\n");
        result.append("private boolean isList;").append("\n");
        result.append("public boolean isList() {").append("\n");
        result.append("return isList;").append("\n");
        result.append("}").append("\n");
        result.append("\n }");
        // result.append("\n }");
        System.out.println(result.toString());
        return result.toString();
    }

}
