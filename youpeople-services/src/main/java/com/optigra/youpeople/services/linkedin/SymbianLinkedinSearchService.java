package com.optigra.youpeople.services.linkedin;

import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.exception.LinkedinSearchException;
import com.optigra.youpeople.services.parsing.ParsingService;
import com.optigra.youpeople.services.web.WebException;
import com.optigra.youpeople.services.web.WebPageService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by romanmudryi on 25.08.15.
 */
@Service("symbianLinkedinSearchService")
public class SymbianLinkedinSearchService implements LinkedinSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SymbianLinkedinSearchService.class);

    private static final String LINKEDIN_URL_PATTERN = "^(http(s)?:\\/\\/)?([\\w]+\\.)?linkedin\\.com\\/((pub|in)\\/[a-zA-Z0-9/\\-]*[a-zA-Z0-9])[/]?(\\?.+)?";

    private static final int LINKEDIN_REGEX_PROFILE_GROUP_NUMBER = 4;
    
    private static final int LINKEDIN_ACCESS_DENIED_HTTP_STATUS = 999;

    private Pattern linkedinUrlPattern = Pattern.compile(LINKEDIN_URL_PATTERN);
    
    @Resource(name="simpleWebPageService")
    private WebPageService webPageService;

    @Resource(name = "defaultParsingService")
    private ParsingService parsingService;

    @Override
    public Optional<LinkedinPerson> getPersonByLink(String link) {
        Matcher profileUrlMatcher = linkedinUrlPattern.matcher(link);
        if (!profileUrlMatcher.find()) {
            throw new LinkedinSearchException("URL not matching pattern of LinkedIn profile URLs:" + link);
        }
        String profileId = profileUrlMatcher.group(LINKEDIN_REGEX_PROFILE_GROUP_NUMBER);

        String html = null;
		try {
			html = webPageService.get(link);
		} catch (WebException we) {
			if(we.getHttpStatus() == LINKEDIN_ACCESS_DENIED_HTTP_STATUS){
				LOGGER.error("Oops! We (or our proxy) got banned by LinkedIn!"); 
			}else{
				LOGGER.error("Failed to get a webpage from address {}", link, we);
			}
			return Optional.empty();
		}
        Document linkedinDocument = Jsoup.parse(html);

        LinkedinPerson person = parsingService.findPerson(profileId, linkedinDocument);
        try {
			List<Job> jobs = parsingService.findJobs(person, linkedinDocument);
			person.setJobs(jobs);
			List<Volunteering> volunteerings = parsingService.findVolunteerings(person, linkedinDocument);
			person.setVolunteerings(volunteerings);
			List<Education> educations = parsingService.findEducations(person, linkedinDocument);
			person.setEducations(educations);
		} catch (ParseException e) {
			throw new LinkedinSearchException(e);
		}
        return Optional.of(person);
    }
}
