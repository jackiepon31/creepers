package com.fosun.fc.projects.creepers.downloader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
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
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.constant.BaseConstant.SupperMan;
import com.fosun.fc.projects.creepers.dto.CreepersLoginParamDTO;
import com.fosun.fc.projects.creepers.dto.CreepersParamDTO;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.FileUtil;
import com.google.common.collect.Sets;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.HttpConstant;
import us.codecraft.webmagic.utils.UrlUtils;

/**
 * 
 * <p>
 * description: httpClient 模拟登陆
 * </p>
 * 
 * @author MaXin
 * @since 2016-9-9 11:40:10
 * @see
 */
@Component("simulateLoginDownloader")
@Scope("prototype")
public class SimulateLoginDownloader extends AbstractDownloader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected CreepersLoginParamDTO param = new CreepersLoginParamDTO();

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

    private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    private CloseableHttpClient getHttpClient(Site site) {
        logger.info("================>>>  current httpClients size:" + httpClients.size());
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        logger.info("================>>>  current domain:" + domain);
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

    @Override
    public Page download(Request request, Task task) {
        Site site = null;
        // 此处的task的值是一个spider，spider实现了该接口
        // task不为null，则获取其site值
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        String charset = null;
        Map<String, String> headers = null;
        // site不为null，获取状态码、编码方式和头信息，否则状态码为200
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
            // 获取httpclient用于执行request请求
            CloseableHttpClient closeaHttpClient = getHttpClient(site);
            // 获取request作为执行器的参数
            HttpRequestBase httpRequest = getHttpRequest(request, site, headers);
            logger.info("==============>>> httpRequest.head.length:" + httpRequest.getAllHeaders().length);
            Header[] allHeader = httpRequest.getAllHeaders();
            for (Header header : allHeader) {
                logger.debug("==============>>> httpRequest.head:" + header.getName() + ":" + header.getValue());
            }
            // 执行http请求，并接收其响应结果
            httpResponse = closeaHttpClient.execute(httpRequest);
            // 获取响应的状态码
            statusCode = httpResponse.getStatusLine().getStatusCode();
            request.putExtra(Request.STATUS_CODE, statusCode);
            // 判断http请求是否成功
            if (statusAccept(acceptStatCode, statusCode)) {
                // 如果是验证码请求，则解析验证码图片并放入param中，并将param放入page中返回，释放链接
                if (request.getUrl().equals(param.getOrderUrl(BaseConstant.OrderUrlKey.CAPTCHA_URL))) {
                    String imagePath = FileUtil.creatCaptchaImageFile(httpResponse.getEntity().getContent());
                    logger.info("imagePath:" + imagePath);
                    String resultValue = "";
                    String imageId = "";
                    while (StringUtils.isBlank(resultValue)) {
                        Map<SupperMan, String> imageMap = CommonMethodUtils.imageAnalyticalBySupperManHttp(imagePath);
                        resultValue = CommonMethodUtils.getImageAnalyticalValueByChaoren(imageMap);
                        logger.info("resultValue:" + resultValue);
                        imageId = CommonMethodUtils.getImageIdByChaoren(imageMap);
                        logger.info("imageId:" + imageId);
                    }
                    // @SuppressWarnings("resource")
                    // Scanner scanner = new Scanner(System.in);// 创建输入流扫描器
                    // System.out.println("请输入验证号：");// 提示用户输入
                    // resultValue = scanner.nextLine();// 获取用户输入的一行文本
                    param.setCaptchaFilePath(imagePath);
                    param.setCaptchaValue(resultValue);
                    param.setCaptchaId(imageId);
                    Page page = new Page();
                    page.setUrl(new PlainText(request.getUrl()));
                    page.setRequest(request);
                    page.putField(BaseConstant.PARAM_DTO_KEY, param);
                    httpRequest.releaseConnection();
                    return page;
                } else if (param.isDoRedirect()
                        && ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                                || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                                || (statusCode == HttpStatus.SC_SEE_OTHER)
                                || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT))) {
                    Header[] allHeaders = httpResponse.getAllHeaders();
                    String newUrl = "";
                    for (Header header : allHeaders) {
                        if (BaseConstant.HEADER_NAME_LOCATION.equalsIgnoreCase(header.getName())) {
                            newUrl = header.getValue();
                            if (StringUtils.isBlank(newUrl)) {
                                newUrl = "/";
                                break;
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(newUrl)) {
                        if (newUrl.startsWith("./")) {
                            newUrl = newUrl.substring(1);
                            String requestUrl = request.getUrl();
                            newUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/")) + newUrl;
                        }
                        httpRequest.releaseConnection();
                        HttpGet redirectGet = new HttpGet(newUrl);
                        logger.info("downloading redirect page {}", redirectGet.getURI().toString());
                        httpResponse = closeaHttpClient.execute(redirectGet);
                        statusCode = httpResponse.getStatusLine().getStatusCode();
                        if (statusAccept(acceptStatCode, statusCode)) {
                            Page page = handleResponse(request, charset, httpResponse, task);
                            onSuccess(request);
                            page.setUrl(new PlainText(redirectGet.getURI().toString()));
                            page.setRequest(request);
                            page.putField(BaseConstant.PARAM_DTO_KEY, param);
                            redirectGet.releaseConnection();
                            return page;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    Page page = handleResponse(request, charset, httpResponse, task);
                    onSuccess(request);
                    page.setRequest(request);
                    page.putField(BaseConstant.PARAM_DTO_KEY, param);
                    httpRequest.releaseConnection();
                    return page;
                }
            } else {

                logger.warn("code error " + statusCode + "\t" + request.getUrl());
                return null;
            }
        } catch (IOException e) {
            logger.warn("download page " + request.getUrl() + " error", e);
            if (site.getCycleRetryTimes() > 0) {
                return addToCycleRetry(request, site);
            }
            onError(request);
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
            }
        }
    }

    @Override
    public void setThread(int thread) {
        httpClientGenerator.setPoolSize(thread);
    }

    protected boolean statusAccept(Set<Integer> acceptStatCode, int statusCode) {
        return acceptStatCode.contains(statusCode);
    }

    protected HttpRequestBase getHttpRequest(Request request, Site site, Map<String, String> headers) {
        HttpRequestBase httpRequest = getRequestByMethod(request);
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                httpRequest.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        } else if (!CommonMethodUtils.isEmpty(request.getExtra(BaseConstant.POST_HEADER))) {
            @SuppressWarnings("unchecked")
            Map<String, String> headerMap = (Map<String, String>) request.getExtra(BaseConstant.POST_HEADER);
            for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                httpRequest.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        if (site.getHttpProxyPool() != null && site.getHttpProxyPool().isEnable()) {
            HttpHost host = site.getHttpProxyFromPool();
            httpRequest.setConfig(RequestConfig.custom().setProxy(host).build());
            request.putExtra(Request.PROXY, host);
        } else if (site.getHttpProxy() != null) {
            HttpHost host = site.getHttpProxy();
            httpRequest.setConfig(RequestConfig.custom().setProxy(host).build());
            request.putExtra(Request.PROXY, host);
        }
        return httpRequest;
    }

    protected HttpRequestBase getRequestByMethod(Request request) {
        String method = request.getMethod();
        String url = request.getUrl() == null ? null : request.getUrl();
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
            // default get
            return new HttpGet(url);
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
            HttpPost post = new HttpPost(url);
            NameValuePair[] nameValuePair = (NameValuePair[]) request.getExtra(BaseConstant.POST_NAME_VALUE_PAIR);
            if (nameValuePair != null && nameValuePair.length > 0) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                Collections.addAll(list, nameValuePair);
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

    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task)
            throws IOException {
        String content = getContent(charset, httpResponse);
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        return page;
    }

    protected String getContent(String charset, HttpResponse httpResponse) throws IOException {
        if (charset == null) {
            byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
            String htmlCharset = getHtmlCharset(httpResponse, contentBytes);
            if (htmlCharset != null) {
                return new String(contentBytes, htmlCharset);
            } else {
                logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()",
                        Charset.defaultCharset());
                return new String(contentBytes);
            }
        } else {
            return IOUtils.toString(httpResponse.getEntity().getContent(), charset);
        }
    }

    protected String getHtmlCharset(HttpResponse httpResponse, byte[] contentBytes) throws IOException {
        String charset;
        // charset
        // 1、encoding in http header Content-Type
        String value = httpResponse.getEntity().getContentType().getValue();
        charset = UrlUtils.getCharset(value);
        if (StringUtils.isNotBlank(charset)) {
            logger.debug("Auto get charset: {}", charset);
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
        logger.debug("Auto get charset: {}", charset);
        // 3、todo use tools as cpdetector for content decode
        return charset;
    }

    public boolean closeHttpClient(Site site, String String) {
        return false;
    }

    public CreepersParamDTO getParam() {
        return param;
    }

    public SimulateLoginDownloader setParam(CreepersLoginParamDTO param) {
        this.param = param;
        return this;
    }
}
