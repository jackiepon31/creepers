package com.fosun.fc.projects.creepers.downloader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.service.ICreepersExceptionHandleService;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.FileUtil;
import com.google.common.collect.Sets;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * 
 * <p>
 * description: 整合httpClient 请求 升级jar版本，修复post请求bug
 * </p>
 * 
 * @author MaXin
 * @since 2016-8-31 15:12:51
 * @see
 */

@Component("httpRequestDownloader")
@Scope("prototype")
public class HttpRequestDownloader extends HttpClientDownloader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected CreepersParamDTO param = new CreepersParamDTO();

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

    private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    @Autowired
    protected ICreepersExceptionHandleService creepersExceptionHandleServiceImpl;

    @SuppressWarnings("deprecation")
    @Override
    protected HttpUriRequest getHttpUriRequest(Request request, Site site, Map<String, String> headers) {
        RequestBuilder requestBuilder = selectRequestMethod(request).setUri(request.getUrl());
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setConnectionRequestTimeout(site.getTimeOut()).setSocketTimeout(site.getTimeOut())
                .setConnectTimeout(site.getTimeOut()).setCookieSpec(CookieSpecs.BEST_MATCH);
        if (site.getHttpProxyPool() != null && site.getHttpProxyPool().isEnable()) {
            HttpHost host = site.getHttpProxyFromPool();
            requestConfigBuilder.setProxy(host);
            request.putExtra(Request.PROXY, host);
        } else if (site.getHttpProxy() != null) {
            HttpHost host = site.getHttpProxy();
            requestConfigBuilder.setProxy(host);
            request.putExtra(Request.PROXY, host);
        }
        requestBuilder.setConfig(requestConfigBuilder.build());
        requestBuilder.setCharset(Consts.UTF_8);
        return requestBuilder.build();
    }

    @Override
    public Page download(Request request, Task task) {
        logger.info("entry HttpRequestDownloader download!");
        param.setErrorPath(getClass().toString());
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        String charset = null;
        Map<String, String> headers = null;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
            charset = site.getCharset();
            headers = site.getHeaders();
        } else {
            acceptStatCode = Sets.newHashSet(200);
        }
        logger.info("downloading page {}", request.getUrl());
        CloseableHttpResponse httpResponse = null;
        int statusCode = 0;
        try {
            HttpUriRequest httpUriRequest = getHttpUriRequest(request, site, headers);
            httpUriRequest.addHeader("User-Agent", CommonMethodUtils.getRandomUserAgent());
            CloseableHttpClient httpClient = getHttpClient(site);
            httpResponse = httpClient.execute(httpUriRequest);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            request.putExtra(Request.STATUS_CODE, statusCode);
            if (statusAccept(acceptStatCode, statusCode)) {
                if (request.getUrl().matches(param.getOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX))) {
                    String url = param.getOrderUrl(BaseConstant.OrderUrlKey.DOWNLOAD_FILE_URL_1_REGEX);
                    String fileInputStream = FileUtil.createRandomUUIDFileName(url.substring(url.lastIndexOf(".")));
                    FileUtils.copyInputStreamToFile(httpResponse.getEntity().getContent(), new File(fileInputStream));
                    logger.info("create download file:" + fileInputStream);
                    Page page = new Page();
                    page.setUrl(new PlainText(request.getUrl()));
                    page.setRequest(request);
                    onSuccess(request);
                    page.putField(BaseConstant.PARAM_DTO_KEY, param);
                    page.putField(BaseConstant.PAGE_FILE_PATH, fileInputStream);
                    logger.info("exit HttpRequestDownloader download!");
                    return page;
                } else {
                    Page page = handleResponse(request, charset, httpResponse, task);
                    onSuccess(request);
                    page.putField(BaseConstant.PARAM_DTO_KEY, param);
                    logger.info("exit HttpRequestDownloader download!");
                    return page;
                }
            } else {
                logger.warn("code error " + statusCode + "\t" + request.getUrl());
                logger.info("exit HttpRequestDownloader download!");
                return null;
            }
        } catch (IOException e) {
            logger.warn("download page " + request.getUrl() + " error", e);
            e.printStackTrace();
            logger.warn("downloader error \t:" + request.getUrl());
            StringBuffer errorInfo = new StringBuffer("downloader error \t:");
            errorInfo.append(request.getUrl()).append("\n");
            errorInfo.append(e.getMessage());
            param.setErrorInfo(errorInfo.toString());
            creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            if (site.getCycleRetryTimes() > 0) {
                logger.info("exit HttpRequestDownloader download!");
                return addToCycleRetry(request, site);
            }
            onError(request);
            logger.info("exit HttpRequestDownloader download!");
            return null;
        } finally {
            request.putExtra(Request.STATUS_CODE, statusCode);
            try {
                if (httpResponse != null) {
                    // ensure the connection is released back to pool
                    EntityUtils.consume(httpResponse.getEntity());
                }
            } catch (IOException e) {
                logger.warn("close response fail", e);
                e.printStackTrace();
                logger.warn("close response fail \t:" + request.getUrl());
                StringBuffer errorInfo = new StringBuffer("downloader error \t:");
                errorInfo.append(request.getUrl()).append("\n");
                errorInfo.append(e.getCause().getClass());
                errorInfo.append(e.getCause().getMessage());
                param.setErrorInfo(errorInfo.toString());
                creepersExceptionHandleServiceImpl.handleExceptionAndPrintLog(param);
            }
        }
    }

    protected CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    public CreepersParamDTO getParam() {
        return param;
    }

    public HttpRequestDownloader setParam(CreepersParamDTO param) {
        this.param = param;
        return this;
    }
}
