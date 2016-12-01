/**
 * <p>
 * Copyright(c) @2016 Fortune Credit Management Co., Ltd.
 * </p>
 */
package com.fosun.fc.projects.creepers.utils;

import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;

/**
 * <p>
 * description: html代码转成其他格式文件
 * </p>
 * 
 * @author MaXin
 * @since 2016年10月12日
 * @see
 */
public class Html2OtherUtil {

    /**
     * 
     * <p>
     * description: 目前可以完整兼容，先用这个
     * </p>
     * 
     * @param inputHtmlFilePath
     * @return
     * @author MaXin
     * @see 2016-10-17 16:55:04
     */
    public static String generatePDFByPD4ML(String inputHtmlFilePath) {
        return generatePDFByPD4ML(inputHtmlFilePath, null);
    }

    /**
     * 
     * <p>
     * description: 目前可以完整兼容，先用这个
     * </p>
     * 
     * @param inputHtmlFilePath
     * @return
     * @author MaXin
     * @see 2016-10-17 16:55:04
     */
    public static String generatePDFByPD4ML(String inputHtmlFilePath, String outputPDFFilePath) {
        if (StringUtils.isBlank(outputPDFFilePath)) {
            outputPDFFilePath = "/image/" + new Date().getTime() + ".pdf";
        }
        try {
            OutputStream os = new FileOutputStream(new File(outputPDFFilePath));
            InputStreamReader FIS = new InputStreamReader(new FileInputStream(inputHtmlFilePath), PropertiesUtil.getApplicationValue("file.charSet","UTF-8"));
            PD4ML pd4ml = new PD4ML();
            pd4ml.setPageInsets(new Insets(20, 10, 10, 10));
            pd4ml.setHtmlWidth(1050);
            pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
            pd4ml.useTTF("java:fonts", true);// "classpath:/application.properties"
            pd4ml.setDefaultTTFs("KaiTi_GB2312", "KaiTi_GB2312", "KaiTi_GB2312");
            pd4ml.enableDebugInfo();
            pd4ml.render(FIS, os);
            return outputPDFFilePath;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 
     * <p>
     * description:目前还有bug 先不要使用
     * </p>
     * 
     * @param inputHtmlFilePath
     * @return
     * @author MaXin
     * @see 2016-10-17 16:54:34
     */
    public static String generatePDFByITextRenderer(String inputHtmlFilePath) {
        return generatePDFByITextRenderer(inputHtmlFilePath, null);
    }

    /**
     * 
     * <p>
     * description:目前还有bug 先不要使用
     * </p>
     * 
     * @param inputHtmlFilePath
     * @return
     * @author MaXin
     * @see 2016-10-17 16:54:34
     */
    public static String generatePDFByITextRenderer(String inputHtmlFilePath, String outputPDFFilePath) {
        if (StringUtils.isBlank(outputPDFFilePath)) {
            outputPDFFilePath = "/image/" + new Date().getTime() + ".pdf";
        }
        try {
            OutputStream os = new FileOutputStream(new File(outputPDFFilePath));
            InputStreamReader FIS = new InputStreamReader(new FileInputStream(inputHtmlFilePath), PropertiesUtil.getApplicationValue("file.charSet","UTF-8"));
            ITextRenderer renderer = new ITextRenderer();
            File tempFile = new File(new Date().getTime() + ".pdf");
            OutputStreamWriter FOS = new OutputStreamWriter(new FileOutputStream(tempFile), PropertiesUtil.getApplicationValue("file.charSet","UTF-8"));
            Tidy tidy = new Tidy();
            tidy.setQuiet(true);
            tidy.setShowWarnings(false);
            Document D = tidy.parseDOM(FIS, FOS);

            renderer.setDocument(D, null);
            // 解决中文支持问题
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont("E:/2015/workspace/fortune-projects/projects-creepers-v1.0.0/fonts/SIMKAI.TTF",
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 解决图片的相对路径问题
            renderer.getSharedContext().setBaseURL("file:/D:/pdf/");
            renderer.layout();
            renderer.createPDF(os);
            os.close();
            tempFile.deleteOnExit();
            return outputPDFFilePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws IOException, DocumentException {
        String outputFile = "D:/pdf/firstdoc" + new Date().getTime() + ".pdf";
//         Html2OtherUtil.generatePDFByPD4ML("D:/image/www.pbccrc.org.cn/d31a27b4bcd6760fad75af80f696ee87.txt", outputFile);
         Html2OtherUtil.generatePDFByPD4ML("D:/pdf/60942568930fe6d8b5b2285f28fb811b.txt", outputFile);
//        Html2OtherUtil.generatePDFByITextRenderer("D:/image/www.pbccrc.org.cn/0fb46f521667016452727cc3c79c0c1b.txt", outputFile);
    }

}
