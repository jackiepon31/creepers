package com.fosun.fc.projects.creepers.pipeline;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersListCreditService;
import com.fosun.fc.projects.creepers.utils.FileUtil;
import com.fosun.fc.projects.creepers.utils.Html2OtherUtil;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.HasKey;

/**
 * 
 * <p>
 * 将爬取的HTMLContent写入Text文本中: 其他信息写入数据库
 * </p>
 * 
 * @author MaXin
 * @since 2016-10-19 10:19:37
 * 
 */
@Component("creditReferenceUploadFilePipline")
public class CreditReferenceUploadFilePipline extends BasePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public CreditReferenceUploadFilePipline() {
        setPath("/webmagic/");
    }

    public CreditReferenceUploadFilePipline(String path) {
        setPath(path);
    }

    @Autowired
    private ICreepersListCreditService creepersListCreditServiceImpl;

    @Override
    public void process(ResultItems resultItems, Task task) {
        CreepersLoginParamDTO param = resultItems.get(BaseConstant.PARAM_DTO_KEY);
        // 1.生成文件
        String path = "/image" + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        String filename = "";
        try {
            if (resultItems instanceof HasKey) {
                filename = path + ((HasKey) resultItems).key() + ".txt";
            } else {
                filename = path + DigestUtils.md5Hex(ToStringBuilder.reflectionToString(resultItems)) + ".txt";
            }
            PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(filename)));
            Document doc = Jsoup.parse(resultItems.get("htmlContent").toString());
            printWriter.write(doc.html());
            printWriter.close();
            
            String PDFPath = Html2OtherUtil.generatePDFByPD4ML(filename);
            
            String imagePath = FileUtil.upload(PDFPath);
            if (StringUtils.isBlank(imagePath)) {
                logger.error("====>>>上传PDF，返回路径为空！");
            } else if (StringUtils.isBlank(filename)) {
                logger.error("====>>>html生成文件路径为空！");
            } else if (StringUtils.isNotBlank(imagePath) && StringUtils.isNotBlank(filename)) {
                creepersListCreditServiceImpl.updateImageAndHtmlPath(param.getLoginName(), imagePath, filename);
            }
        } catch (Exception e) {
            logger.error("step:  ======>>  CreditReferenceUploadFilePipline save FALSE!!!");
            logger.error("write DB error", e.getCause().getClass() + e.getCause().getMessage());
            param.setErrorInfo("write DB error" + e.getCause().getClass() + e.getCause().getMessage());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
        }
    }
}
