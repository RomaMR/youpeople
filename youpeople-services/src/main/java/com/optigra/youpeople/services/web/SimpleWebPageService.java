package com.optigra.youpeople.services.web;

import com.optigra.youpeople.services.web.proxy.ProxyProvider;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service("simpleWebPageService")
public class SimpleWebPageService implements WebPageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleWebPageService.class);
	
	private static final String USER_AGENT = "Googlebot/2.1 (+http://www.googlebot.com/bot.html)";

    private static final String REFERRER = "https://www.google.com";
    
    private static final String ACCEPT_LANGUAGE = "en-us, en;q=0.9";
    
    private static final int RESPONSE_TIMEOUT = 5000;
    
    private boolean proxyEnabled = false;
    
    @Resource(name="gimmeProxyProviderService")
    private ProxyProvider proxyProvider;

	@Override
	public String get(String link) {
		
		// Dirty hack to get rid of HTTPS issues
		link.replaceFirst("https:\\/\\/", "http://");
		
        try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(link);
            
            httpget.setHeader("User-Agent", USER_AGENT);
            httpget.setHeader("Referrer", REFERRER);
            httpget.setHeader("Accept-Language", ACCEPT_LANGUAGE);
            
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            configBuilder.setConnectionRequestTimeout(RESPONSE_TIMEOUT);
            configBuilder.setSocketTimeout(RESPONSE_TIMEOUT);
            if(proxyEnabled){
            	HttpHost proxy = proxyProvider.getProxy();
            	LOGGER.info("Setting proxy to {}", proxy);
            	configBuilder.setProxy(proxy);
            }
            
            RequestConfig config = configBuilder.build();
            httpget.setConfig(config);

            LOGGER.info("Executing request: {}", httpget.getRequestLine());

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new WebException("Unexpected response status: " + status, status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            return responseBody;
		} catch (IOException e) {
			throw new WebException(e);
		} 
	}

	public boolean isProxyEnabled() {
		return proxyEnabled;
	}

	public void setProxyEnabled(boolean proxyEnabled) {
		this.proxyEnabled = proxyEnabled;
	}

	public ProxyProvider getProxyProvider() {
		return proxyProvider;
	}

	public void setProxyProvider(ProxyProvider proxyProvider) {
		this.proxyProvider = proxyProvider;
	}
	
}
