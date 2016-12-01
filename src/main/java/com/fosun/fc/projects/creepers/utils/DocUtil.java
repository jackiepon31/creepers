/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.CreepersConstant;
import com.lowagie.text.DocumentException;

/**
 * <p>
 * description: doc文件解析处理
 * </p>
 * 
 * @author MaXin
 * @since 2016-11-29 13:57:05
 * @see
 */
public class DocUtil {

    public static String wordToString(String filePathName) {
        String result = StringUtils.EMPTY;
        try {
            if (StringUtils.isNotBlank(filePathName) && filePathName.endsWith(BaseConstant.FileSuffix.DOC.getValue())) {
                InputStream is = new FileInputStream(new File(filePathName));
                WordExtractor ex = new WordExtractor(is);
                result = ex.getText();
            } else if (StringUtils.isNotBlank(filePathName) && filePathName.endsWith(BaseConstant.FileSuffix.DOCX.getValue())) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePathName);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                result = extractor.getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String excelToString(String filePathName) {
        StringBuffer result = new StringBuffer();
        try {
            if (FileUtil.checkFilePath(filePathName)) {
                return result.toString();
            }
            InputStream is = new FileInputStream(filePathName);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                // 循环行Row
                for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }

                    // 循环列Cell
                    for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                        HSSFCell hssfCell = hssfRow.getCell(cellNum);
                        if (hssfCell == null) {
                            continue;
                        }

                        result.append(getValue(hssfCell));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    public static void readWordTable(String sourceFile, List<Map<String, String>> listMap) {
        readWordTable(sourceFile, listMap, 0);
    }

    public static void readWordTable(String sourceFile, List<Map<String, String>> listMap, int titleRowNum) {
        readWordTable(sourceFile, listMap, titleRowNum, false, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    public static void readWordTable(String sourceFile, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readWordTable(sourceFile, listMap, 0, keyContent, keyColumn);
    }

    public static void readWordTable(String sourceFile, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readWordTable(sourceFile, listMap, titleRowNum, true, keyContent, keyColumn);
    }

    private static void readWordTable(String sourceFile, List<Map<String, String>> listMap, int titleRowNum, boolean setKeyToMap, String keyContent, String keyColumn) {
        FileInputStream in = null;
        try {
            File file = new File(sourceFile);
            if (!file.exists()) {
                System.err.println("======>>>文件不存在！！\n" + sourceFile);
                file.createNewFile();
            }
            in = new FileInputStream(sourceFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("DocUtil.readWordSingleTable 文件输入流读取错误！！！");
        }
        readWordTable(in, listMap, titleRowNum, setKeyToMap, keyContent, keyColumn);

    }

    public static void readWordTable(InputStream fileInputStream, List<Map<String, String>> listMap) {
        readWordTable(fileInputStream, listMap, 0);
    }

    public static void readWordTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum) {
        readWordTable(fileInputStream, listMap, titleRowNum, false, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    public static void readWordTable(InputStream fileInputStream, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readWordTable(fileInputStream, listMap, 0, keyContent, keyColumn);
    }

    public static void readWordTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readWordTable(fileInputStream, listMap, titleRowNum, true, keyContent, keyColumn);
    }

    private static void readWordTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, boolean setKeyToMap, String keyContent, String keyColumn) {
        try {
            HWPFDocument hwpf = new HWPFDocument(fileInputStream);
            Range range = hwpf.getRange();// 得到文档的读取范围
            TableIterator it = new TableIterator(range);
            // 迭代文档中的表格
            while (it.hasNext()) {
                Table tb = (Table) it.next();
                // 迭代行，默认从0开始
                String[] key = {};
                if (titleRowNum > tb.numRows()) {
                    titleRowNum = 0;
                }
                for (int i = titleRowNum; i < tb.numRows(); i++) {
                    TableRow tr = tb.getRow(i);
                    // 迭代列，默认从0开始
                    if (i == titleRowNum) {
                        key = new String[tr.numCells()];
                        for (int j = 0; j < tr.numCells(); j++) {
                            TableCell td = tr.getCell(j);// 取得单元格
                            // 取得单元格的内容
                            StringBuffer value = new StringBuffer();
                            for (int k = 0; k < td.numParagraphs(); k++) {
                                Paragraph para = td.getParagraph(k);
                                String s = para.text().trim();
                                value.append(s);
                            }
                            key[j] = value.toString();
                        }
                    } else {
                        Map<String, String> map = new HashMap<String, String>();
                        if (key.length < tr.numCells()) {
                            titleRowNum++;
                            continue;
                        } else {
                            for (int j = 0; j < tr.numCells(); j++) {
                                TableCell td = tr.getCell(j);// 取得单元格
                                // 取得单元格的内容
                                StringBuffer value = new StringBuffer();
                                for (int k = 0; k < td.numParagraphs(); k++) {
                                    Paragraph para = td.getParagraph(k);
                                    String s = para.text().trim();
                                    value.append(s);
                                }
                                if (setKeyToMap && CommonMethodUtils.matchesChineseValue(keyContent).equals(CommonMethodUtils.matchesChineseValue(key[j]))) {
                                    map.put(keyColumn, value.toString());
                                }
                                map.put(key[j], value.toString());
                            }
                            listMap.add(map);
                        }
                    }
                }
            }
        } catch (Exception e) {
            listMap = new ArrayList<Map<String, String>>();
            e.printStackTrace();
            System.err.println("DocUtil.readWordSingleTable error!!! \n" + e.getMessage());
        }
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap) {
        readExcelTable(filePathName, listMap, true);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, boolean singleSheet) {
        readExcelTable(filePathName, listMap, 0, singleSheet);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, int titleRowNum) {
        readExcelTable(filePathName, listMap, titleRowNum, true);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readExcelTable(filePathName, listMap, keyContent, keyColumn, true);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, int titleRowNum, boolean singleSheet) {
        readExcelTable(filePathName, listMap, titleRowNum, false, StringUtils.EMPTY, StringUtils.EMPTY, singleSheet);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(filePathName, listMap, 0, true, keyContent, keyColumn, singleSheet);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readExcelTable(filePathName, listMap, titleRowNum, keyContent, keyColumn, true);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(filePathName, listMap, titleRowNum, true, keyContent, keyColumn, singleSheet);
    }

    public static void readExcelTable(String filePathName, List<Map<String, String>> listMap, int titleRowNum, boolean setKeyToMap, String keyContent, String keyColumn, boolean singleSheet) {
        try {
            File file = new File(filePathName);
            if (!file.exists()) {
                System.err.println("======>>>文件不存在！！\n" + filePathName);
                file.createNewFile();
            }
            InputStream fileInputStream = new FileInputStream(filePathName);
            readExcelTable(fileInputStream, listMap, titleRowNum, setKeyToMap, keyContent, keyColumn, singleSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap) {
        readExcelTable(fileInputStream, listMap, true);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, 0, singleSheet);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum) {
        readExcelTable(fileInputStream, listMap, titleRowNum, true);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readExcelTable(fileInputStream, listMap, keyContent, keyColumn, true);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, titleRowNum, false, StringUtils.EMPTY, StringUtils.EMPTY, singleSheet);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, 0, true, keyContent, keyColumn, singleSheet);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readExcelTable(fileInputStream, listMap, titleRowNum, keyContent, keyColumn, true);
    }

    public static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, titleRowNum, true, keyContent, keyColumn, singleSheet);
    }

    private static void readExcelTable(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, boolean setKeyToMap, String keyContent, String keyColumn, boolean singleSheet) {
        try {

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
            // 循环工作表Sheet
            int sheetNum = 1;
            if (!singleSheet) {
                sheetNum = hssfWorkbook.getNumberOfSheets();
            }
            String[] key = {};
            for (int numSheet = 0; numSheet < sheetNum; numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                if (titleRowNum > hssfSheet.getLastRowNum()) {
                    titleRowNum = 0;
                }
                // 循环行Row
                for (int rowNum = titleRowNum; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    if (rowNum == titleRowNum) {
                        // 循环列Cell
                        key = new String[hssfRow.getLastCellNum()];
                        for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                            HSSFCell hssfCell = hssfRow.getCell(cellNum);
                            if (hssfCell == null) {
                                continue;
                            }
                            key[cellNum] = getValue(hssfCell);
                        }
                    } else {
                        if (key.length < hssfRow.getLastCellNum()) {
                            titleRowNum++;
                            continue;
                        }
                        // 循环列Cell
                        Map<String, String> map = new HashMap<String, String>();
                        for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                            HSSFCell hssfCell = hssfRow.getCell(cellNum);
                            if (hssfCell == null) {
                                continue;
                            }
                            if (setKeyToMap && CommonMethodUtils.matchesChineseValue(keyContent).equals(CommonMethodUtils.matchesChineseValue(key[cellNum]))) {
                                map.put(keyColumn, getValue(hssfCell));
                            }
                            map.put(key[cellNum], getValue(hssfCell));
                        }
                        listMap.add(map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * <p>
     * description:医药读word 写入Medical表
     * </p>
     * 
     * @param sourceFile
     *            源文件
     * @param listMap
     *            结果集
     * @author MaXin
     * @since 2016-11-29 16:47:13
     * @see
     */
    public static void readWordTableMedical(String sourceFile, List<Map<String, String>> listMap) {
        readWordTableMedical(sourceFile, listMap, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
    }

    public static void readWordTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap) {
        readWordTableMedical(fileInputStream, listMap, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
    }

    /**
     * 
     * <p>
     * description:医药读word 写入Medical表
     * </p>
     * 
     * @param sourceFile
     *            源文件
     * @param listMap
     *            结果集
     * @param titleRowNum
     *            表头起始行
     * @author MaXin
     * @since 2016-11-29 16:48:05
     * @see
     */
    public static void readWordTableMedical(String sourceFile, List<Map<String, String>> listMap, int titleRowNum) {
        readWordTableMedical(sourceFile, listMap, titleRowNum, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
    }

    public static void readWordTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum) {
        readWordTableMedical(fileInputStream, listMap, titleRowNum, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue());
    }

    /**
     * 
     * <p>
     * description:医药读word 写入Medical表
     * </p>
     * 
     * @param sourceFile
     *            源文件
     * @param listMap
     *            结果集
     * @param keyContent
     *            key对应的表头名
     * @param keyColumn
     *            key对应的数据库column
     * @author MaXin
     * @since 2016-11-29 16:50:37
     * @see
     */
    public static void readWordTableMedical(String sourceFile, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readWordTable(sourceFile, listMap, 0, keyContent, keyColumn);
    }

    public static void readWordTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readWordTable(fileInputStream, listMap, 0, keyContent, keyColumn);
    }

    /**
     * 
     * <p>
     * description:医药读word 写入Medical表
     * </p>
     * 
     * @param sourceFile
     *            源文件
     * @param listMap
     *            结果集
     * @param titleRowNum
     *            表头起始行
     * @param keyContent
     *            key对应的表头名
     * @param keyColumn
     *            key对应的数据库column
     * @author MaXin
     * @since 2016-11-29 16:52:53
     * @see
     */
    public static void readWordTableMedical(String sourceFile, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readWordTable(sourceFile, listMap, titleRowNum, true, keyContent, keyColumn);
    }

    public static void readWordTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readWordTable(fileInputStream, listMap, titleRowNum, true, keyContent, keyColumn);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @author MaXin
     * @since 2016-11-30 16:53:05
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap) {
        readExcelTableMedical(filePathName, listMap, true);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap) {
        readExcelTableMedical(fileInputStream, listMap, true);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param singleSheet
     *            是否值需要单sheet 默认第一个
     * @author MaXin
     * @since 2016-11-30 16:54:23
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, boolean singleSheet) {
        readExcelTableMedical(filePathName, listMap, 0, true);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, boolean singleSheet) {
        readExcelTableMedical(fileInputStream, listMap, 0, singleSheet);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param titleRowNum
     *            头信息行数
     * @author MaXin
     * @since 2016-11-30 16:55:46
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, int titleRowNum) {
        readExcelTableMedical(filePathName, listMap, titleRowNum, true);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum) {
        readExcelTableMedical(fileInputStream, listMap, titleRowNum, true);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param keyContent
     *            key对应的表头名
     * @param keyColumn
     *            key对应的数据库column
     * @author MaXin
     * @since 2016-11-30 16:59:52
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readExcelTableMedical(filePathName, listMap, keyContent, keyColumn, true);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, String keyContent, String keyColumn) {
        readExcelTableMedical(fileInputStream, listMap, keyContent, keyColumn, true);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param titleRowNum
     *            头信息行数
     * @param singleSheet
     *            是否值需要单sheet 默认第一个
     * @author MaXin
     * @since 2016-11-30 17:01:16
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, int titleRowNum, boolean singleSheet) {
        readExcelTable(filePathName, listMap, titleRowNum, true, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), singleSheet);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, titleRowNum, true, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), singleSheet);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param keyContent
     *            key对应的表头名
     * @param keyColumn
     *            key对应的数据库column
     * @param singleSheet
     *            是否值需要单sheet 默认第一个
     * @author MaXin
     * @since 2016-11-30 17:02:01
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(filePathName, listMap, 0, true, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), singleSheet);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, 0, true, "企业名称", CreepersConstant.TCreepersMedicalColumn.KEY.getValue(), singleSheet);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param titleRowNum
     *            头信息行数
     * @param keyContent
     *            key对应的表头名
     * @param keyColumn
     *            key对应的数据库column
     * @author MaXin
     * @since 2016-11-30 17:03:07
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readExcelTableMedical(filePathName, listMap, titleRowNum, keyContent, keyColumn, true);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn) {
        readExcelTableMedical(fileInputStream, listMap, titleRowNum, keyContent, keyColumn, true);
    }

    /**
     * 
     * <p>
     * description:医药读excel 写入Medical表
     * </p>
     * 
     * @param filePathName
     *            源文件
     * @param listMap
     *            结果集
     * @param titleRowNum
     *            头信息行数
     * @param keyContent
     *            key对应的表头名
     * @param keyColumn
     *            key对应的数据库column
     * @param singleSheet
     *            是否值需要单sheet 默认第一个
     * @author MaXin
     * @since 2016-11-30 17:03:17
     * @see
     */
    public static void readExcelTableMedical(String filePathName, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(filePathName, listMap, titleRowNum, true, keyContent, keyColumn, singleSheet);
    }

    public static void readExcelTableMedical(InputStream fileInputStream, List<Map<String, String>> listMap, int titleRowNum, String keyContent, String keyColumn, boolean singleSheet) {
        readExcelTable(fileInputStream, listMap, titleRowNum, true, keyContent, keyColumn, singleSheet);
    }

    public static void main(String[] args) throws IOException, DocumentException {
        try {
            List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
            readWordTable("C:\\Users\\Administrator\\Downloads\\ueO2q8qh0qnGt76t06rG89K1R1NQyMWpLmryr65q7jmo6i12jI2MbrFo6m4vbz+LmRvYw== (1).doc", resultList);
            for (Map<String, String> map : resultList) {
                Set<Entry<String, String>> set = map.entrySet();
                for (Entry<String, String> entry : set) {
                    System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
                }
                System.out.println("==================================>>>>");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
