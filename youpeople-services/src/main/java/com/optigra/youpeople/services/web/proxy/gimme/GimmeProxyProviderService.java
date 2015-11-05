package com.optigra.youpeople.services.web.proxy.gimme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


import org.apache.http.HttpHost;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optigra.youpeople.services.web.proxy.ProxyProvider;
import com.optigra.youpeople.services.web.proxy.ProxyProviderException;

/**
 * @see <a href="http://gimmeproxy.com">GimmeProxy web page</a>
 * @author odisseus
 *
 */
@Service("gimmeProxyProviderService")
public class GimmeProxyProviderService implements ProxyProvider {
	
	private String userId = UUID.randomUUID().toString();
	
	private static final String BASE_URL = "http://gimmeproxy.com/api/get/{userId}/?timeout={timeout}";
	
	private static final int PROXY_RECOVERY_TIMEOUT = 600;
	
	private GimmeProxyDTO gimmeProxy() throws JsonParseException, JsonMappingException, IOException{
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> types = new ArrayList<>(jsonConverter.getSupportedMediaTypes());
		types.add(MediaType.TEXT_HTML);
		jsonConverter.setSupportedMediaTypes(types);
		restTemplate.setMessageConverters(Arrays.asList(jsonConverter));
		GimmeProxyDTO gimmeProxy = 
				restTemplate.getForObject(BASE_URL, GimmeProxyDTO.class, userId, PROXY_RECOVERY_TIMEOUT);
		return gimmeProxy;
	}
	
	private HttpHost toHttpHost(GimmeProxyDTO gimmeProxy) throws UnknownHostException{
		String ipAndPort = gimmeProxy.getIp();
		int separatorPosition = ipAndPort.indexOf(':');
		String ip = ipAndPort.substring(0, separatorPosition);
		String port = ipAndPort.substring(separatorPosition + 1);
		
		return new HttpHost(InetAddress.getByName(ip), Integer.valueOf(port), gimmeProxy.getType());
	}

	@Override
	public HttpHost getProxy() {
		try {
			return toHttpHost(gimmeProxy());
		} catch (IOException e) {
			throw new ProxyProviderException(e);
		}
	}

}
