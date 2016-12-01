package com.fosun.fc.projects.creepers.pipeline;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.pipeline.FilePipeline;

/**
 * 
 * <p>
 * 返回结果在List中，循环输出到文件。
 * </p>
 * 
 * @author maxin
 * @since 2016年5月4日
 * @see
 */
public class ResultListToFileModelPipline extends FilePipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public ResultListToFileModelPipline() {
        setPath("/data/webmagic/");
    }

    public ResultListToFileModelPipline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Map<String ,Object>> resultList = resultItems.get("resultList");
            String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
            try {
                String filename;
                if (resultList instanceof HasKey) {
                    filename = path + ((HasKey) resultList).key() + ".txt";
                } else {
                    filename = path + DigestUtils.md5Hex(ToStringBuilder.reflectionToString(resultList)) + ".txt";
                }
                PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(filename)));
                StringBuffer result = new StringBuffer();
                for (Map<String, Object> map : resultList) {
                    Set<Entry<String, Object>> entrySet = map.entrySet();
                    for (Entry<String, Object> entry : entrySet) {
                        result.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
                    }
                    result.append("\n");
                }
                printWriter.write(result.toString());
                printWriter.close();
            } catch (IOException e) {
                logger.warn("write file error", e);
            }
    }
}
