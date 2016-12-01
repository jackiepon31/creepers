package com.fosun.fc.projects.creepers.pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * 
 *<p>
 *将爬取结果的List集合写入Text文本中:
 *</p>
 * @author maxin
 * @since 2016年5月4日
 * @see
 */
public class FileTxtPageListModelPipline extends FilePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public FileTxtPageListModelPipline() {
        setPath("/data/webmagic/");
    }

    public FileTxtPageListModelPipline(String path) {
        setPath(path);
    }
    
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Page> pageList = resultItems.get("pageList");
        for(Page page : pageList){
            ResultItems eachResultItems = page.getResultItems();
            String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
            try {
                String filename;
                if (eachResultItems instanceof HasKey) {
                    filename = path + ((HasKey) eachResultItems).key() + ".txt";
                } else {
                    filename = path + DigestUtils.md5Hex(ToStringBuilder.reflectionToString(eachResultItems)) + ".txt";
                }
                PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(filename)));
                StringBuffer result = new StringBuffer();
                Set<Entry<String, Object>> entrySet = eachResultItems.getAll().entrySet();
                for (Entry<String, Object> entry : entrySet) {
                    result.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
                }
                printWriter.write(result.toString());
                printWriter.close();
            } catch (IOException e) {
                logger.warn("write file error", e);
            }
        }
    }
}
