package com.optigra.youpeople.services.web.proxy;

import org.apache.http.HttpHost;

public interface ProxyProvider {
	
	HttpHost getProxy();
	
}
