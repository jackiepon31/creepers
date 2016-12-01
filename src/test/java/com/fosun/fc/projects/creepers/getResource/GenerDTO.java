package com.fosun.fc.projects.creepers.getResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

/**
 * 
 * <p>
 * 生成DAO.java pipeLine.java
 * </p>
 * 
 * @author MaXin
 * @since 2016-7-14 15:37:00
 * @see
 */
public class GenerDTO {

    public static void main(String[] args) {
        String filePaht = "D:/tableColumn/now/";
        readAndWriteFile(filePaht);
    }

    public static String addPackage() {
        return "package com.fosun.fc.projects.creepers.dto;\n\n";
    }

    public static String addDescription(String desc) {
        StringBuffer result = new StringBuffer();
        result.append("\n");
        result.append("/**").append("\n");
        result.append("*").append("\n");
        result.append("* <p>").append("\n");
        result.append("* description:").append("\n");
        result.append("* ").append(desc).append("\n");
        result.append("* <p>").append("\n");
        result.append("* @author MaXin").append("\n");
        result.append("* @since ").append(
                CommonMethodUtils.dateFormat(new Date(), BaseConstant.DateFormatPatternType.TYPE_ONE.getValue()))
                .append("\n");
        result.append("* @see").append("\n");
        result.append("*/").append("\n").append("\n");
        return result.toString();
    }

    public static String addTop(String className, String entityName) {
        StringBuffer result = new StringBuffer();
        result.append("public class ").append(className);
        result.append(" extends CreepersBaseDTO").append(" {\n");
        result.append("private static final long serialVersionUID = 1L;\n");
        return result.toString();
    }


    public static void readAndWriteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            String[] result = file.list();
            for (String string : result) {
                StringBuffer exportString = new StringBuffer();
                String entityName = TranslateMethodUtil
                        .firstUpperOtherLower(TranslateMethodUtil.deleteFileSuffix(string));
                String className = TranslateMethodUtil.deleteFirstWord(entityName) + "DTO";
                String inputFilePath = filePath + string;
                String outPutFilePath = BaseConstant.JavaFilePath.CREEPERS_DTO_FILE_PATH.getValue()
                        + className + BaseConstant.FileSuffix.JAVA.getValue();
                try {
                    @SuppressWarnings("resource")
                    String desc = new BufferedReader(new FileReader(inputFilePath)).readLine();
                    exportString.append(addPackage());
                    exportString.append(addDescription(desc));
                    exportString.append(addTop(className, entityName));
                    exportString.append(readInputFile(inputFilePath));
                    System.out.println("==========>>>开始生成：" + outPutFilePath);
                    PrintWriter print = new PrintWriter(new FileWriter(new File(outPutFilePath)));
                    print.write(exportString.toString());
                    print.flush();
                    print.close();
                    System.out.println("==========>>>完成！" + outPutFilePath);
                } catch (IOException e) {
                    System.out.println("==========>>>生成文件错误！" + outPutFilePath);
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readInputFile(String eachFilePath) {
        File eachFile = new File(eachFilePath);
        boolean isFirst = true;
        StringBuffer result = new StringBuffer();
        if (eachFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(eachFile));
                String line = null;
                String topLine = "";
                while ((line = br.readLine()) != null) {
                    if (isFirst) {
                        isFirst = false;
                        continue;
                    }
                    result.append(addContent(line));
                }
                result.append(addBottom(topLine));
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    private static String addBottom(String topLine) {
        return topLine + "\n}";
    }

    private static String addContent(String str) {
        String[] arr = str.split("\t");
        StringBuffer result = new StringBuffer();
        if (arr.length == 2) {
            result.append("//").append(arr[1]).append("\n");
        }
        result.append("private String ").append(TranslateMethodUtil.toLower(arr[0])).append(";").append("\n");
        return result.toString();
    }
}
