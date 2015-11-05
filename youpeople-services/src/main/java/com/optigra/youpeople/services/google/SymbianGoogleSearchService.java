package com.optigra.youpeople.services.google;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Google search service that pletends to be a Symbian mobile phone
 * in order to receive simple versions of webpages.
 * 
 * @author odisseus
 *
 */
@Service("symbianGoogleSearchService")
public class SymbianGoogleSearchService implements GoogleSearchService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SymbianGoogleSearchService.class);
	
	private static final String BASE_URL = "https://www.google.com/search?q=";
	private static final String USER_AGENT = "Nokia5250/10.0.011 (SymbianOS/9.4; U; Series60/5.0 Mozilla/5.0; Profile/MIDP-2.1 Configuration/CLDC-1.1 )";
	private static final String REFERRER = "https://www.google.com";
	private static final int RESPONSE_TIMEOUT = 50000; //set 0 for infinite timeout
	private static final int RESULTS_PER_PAGE = 10;
	
	private static final int REQUESTS_COUNT_LIMIT = 15;
	private static final int REQUESTS_INTERVAL = 3000;
	
	private static final String DIRECT_URL_REGEX = 	"/url\\?q=([^&]+[^&/])&??";
	private static final Pattern DIRECT_URL_PATTERN = Pattern.compile(DIRECT_URL_REGEX);
	private static final int DIRECT_URL_MATCHER_GROUP = 1;

	@Override
	public List<String> getSearchResultLinks(String query, int offset, int limit) {
		LOGGER.info("SymbianGoogleSearchService.getSearchResultLinks(), query={}", query);
		int actualOffset = leastOffset(offset);
		int toSkip = offset - actualOffset;
		List<String> searchResultLinks = getSearchResultLinkStream(query, actualOffset)
				.skip(toSkip).limit(limit)
				.collect(Collectors.toList());
		LOGGER.info("SymbianGoogleSearchService.getSearchResultLinks() finished");
		return searchResultLinks;
	}
	
	/**
	 * Returns a potentially infinite stream of search results, starting with a given offset.
	 * Offset must be a multiple of RESULTS_PER_PAGE
	 * @param query
	 * @param offset
	 * @return
	 */
	private Stream<String> getSearchResultLinkStream(String query, Integer offset) {
			Stream<Integer> streamOfOffsets = Stream.iterate(offset, ofs -> ofs+Integer.valueOf(RESULTS_PER_PAGE));
			Stream<Stream<String>> streamOfStreams = streamOfOffsets
					.map(ofs -> getSearchResultPageStream(query, ofs))
					.limit(REQUESTS_COUNT_LIMIT);
			return streamOfStreams.flatMap(Function.identity());
	}
	
	/**
	 * Returns a stream containing search results from a single page.
	 * @param query
	 * @param offset
	 * @return
	 */
	private Stream<String> getSearchResultPageStream(String query, int offset) {
		String url = null;
		try {
			
			// TODO replace this with an external timer
			try {
				Thread.sleep(REQUESTS_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			url = prepareSearchUrl(query, offset);
			LOGGER.info("Requesting {}", url);
			Document document = Jsoup.connect(url).followRedirects(true).userAgent(USER_AGENT).referrer(REFERRER)
					.timeout(RESPONSE_TIMEOUT).get();
			LOGGER.info("Request succeded: {}", url);
			Stream<String> searchResultLinks = parseSearchResultLinks(document);
			return searchResultLinks;
		} catch (HttpStatusException hse) {
			LOGGER.error("Request failed with status {}: {}", hse.getStatusCode(), url, hse);
			return Stream.empty();
		} catch (IOException e) {
			throw new GoogleSearchException("Google search failed", e);
		}
	}
	
	private String prepareSearchUrl(String query, int offset) throws UnsupportedEncodingException{
		String encodedQuery = URLEncoder.encode(query, "UTF-8");
		String result = BASE_URL + encodedQuery + "&start=" + offset;
		return result;
	}
	
	private Stream<String> parseSearchResultLinks(Document googleDocument) {
		Elements elements = googleDocument.select(".web_result > div > a");
		Stream<String> rawUrls = elements.stream().map(element -> element.attr("href"));
		Stream<String> cleanUrls = rawUrls.map(url -> cleanUrl(url));
		return cleanUrls;
	}
	
	private String cleanUrl(String url){
		Matcher matcher = DIRECT_URL_PATTERN.matcher(url);
		matcher.find();
		String result = matcher.group(DIRECT_URL_MATCHER_GROUP);
		return result;
	}
	
	private int leastOffset(int requestedOffset){
		return (requestedOffset / RESULTS_PER_PAGE) * RESULTS_PER_PAGE;
	}
	

}
