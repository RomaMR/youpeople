package com.optigra.youpeople.services.parsing.google;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// TODO remove this class
@Deprecated
public class SymbianGoogleParser implements GoogleParser {
	
	private static final String DIRECT_URL_REGEX = 	"/url\\?q=([^&]+[^&/])&??";
	private static final Pattern DIRECT_URL_PATTERN = Pattern.compile(DIRECT_URL_REGEX);
	private static final int DIRECT_URL_MATCHER_GROUP = 1;

	@Override
	public List<String> parseSearchResultLinks(String htmlPage) {
		Document googleDocument = Jsoup.parse(htmlPage);
		Elements elements = googleDocument.select(".web_result > div > a");
		Stream<String> rawUrls = elements.stream().map(element -> element.attr("href"));
		Stream<String> cleanUrls = rawUrls.map(url -> cleanUrl(url));
		return cleanUrls.collect(Collectors.toList());
	}
	
	private String cleanUrl(String url){
		Matcher matcher = DIRECT_URL_PATTERN.matcher(url);
		matcher.find();
		String result = matcher.group(DIRECT_URL_MATCHER_GROUP);
		return result;
	}

}
