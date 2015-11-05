package com.optigra.youpeople.services.web.proxy;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpHost;
import org.springframework.stereotype.Component;

@Component("defaultProxyFactory")
public class DefaultProxyFactory implements ProxyProvider{
	
	@Resource(name="proxyList")
	private List<HttpHost> proxyList;

	@Override
	public HttpHost getProxy() {
		return proxyList.get(0);
	}

	public List<HttpHost> getProxyList() {
		return proxyList;
	}

	public void setProxyList(List<HttpHost> proxyList) {
		this.proxyList = Collections.unmodifiableList(proxyList);
	}
	
	

}
