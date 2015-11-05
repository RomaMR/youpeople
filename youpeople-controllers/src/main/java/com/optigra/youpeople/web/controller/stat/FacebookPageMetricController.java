package com.optigra.youpeople.web.controller.stat;

import com.optigra.youpeople.domain.facebook.insights.InsightAggregationPeriod;
import com.optigra.youpeople.dto.stat.DataSetDTO;
import com.optigra.youpeople.facades.statistic.facebook.FacebookPageMetricsFacade;
import com.optigra.youpeople.web.commons.RMPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 
 * @author odisseus
 *
 */
@Controller
@RequestMapping(value = RMPath.FacebookPageMetric.ROOT)
public class FacebookPageMetricController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookPageMetricController.class);
	
	@Resource(name = "defaultFacebookPageMetricsFacade")
	private FacebookPageMetricsFacade facebookPageMetricsFacade;
	
	/**
	 * POST instead of GET due to Facebook access token passed as parameter.
	 * @param pageName e.g. "OptigraSoftwareTeam"
	 * @param metricName e.g. "page_fans"
	 * @param since UNIX time in milliseconds
	 * @param until UNIX time in milliseconds
	 * @param facebookAuthenticationToken
	 * @return
	 */
	@RequestMapping(value = "{pageName}/{metricName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataSetDTO<Long, Integer>> fetchPageMetric(
    		@PathVariable("pageName") final String pageName,
			@PathVariable("metricName") final String metricName,
			@RequestParam(value = "since", required = false) final Long since,
			@RequestParam(value = "until", required = false) final Long until,
			@RequestParam(value = "period", required = false) final InsightAggregationPeriod period,
    		@RequestParam("facebookAuthenticationToken") final String facebookAuthenticationToken){
        LOGGER.info("FacebookPageMetricController.fetchPageMetric(), pageName={}, metricName={}", pageName, metricName);
        
        DataSetDTO<Long, Integer> metric = facebookPageMetricsFacade.getTimeSeriesMetric(
        		pageName, metricName, facebookAuthenticationToken, since, until, period);

        LOGGER.info("FacebookPageMetricController.fetchPageMetric() finished");
        return new ResponseEntity<>(metric, HttpStatus.OK);
    }


}
