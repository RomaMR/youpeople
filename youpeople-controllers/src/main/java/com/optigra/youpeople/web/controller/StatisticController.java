package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.dto.EmployeeTurnoverStatisticDTO;
import com.optigra.youpeople.dto.IndustryStatisticViewDTO;
import com.optigra.youpeople.dto.TimeAfterCompanyStatisticDTO;
import com.optigra.youpeople.facades.statistic.StatisticFacade;
import com.optigra.youpeople.web.commons.RMPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 02.09.15.
 */
@RestController
@RequestMapping(RMPath.Statistic.ROOT)
public class StatisticController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    @Resource(name = "defaultStatisticFacade")
    private StatisticFacade statisticFacade;

    @RequestMapping(value = "/organization/{organizationId}/industry/groups",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IndustryStatisticViewDTO>> findIndustryGroupStatistic(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("REST request to get industry group statistic, organizationId={}", organizationId);
        List<IndustryStatisticViewDTO> industryStatisticViewDTOs = statisticFacade.findIndustryGroupStatisticByOrganizationId(organizationId);
        LOGGER.info("StatisticController.findIndustryGroupStatistic() finished");
        return new ResponseEntity<>(industryStatisticViewDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/organization/{organizationId}/industry/groups/{industryGroupId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IndustryStatisticViewDTO>> findIndustryGroupStatistic(@PathVariable("industryGroupId") Long industryGroupId,
                                                                                     @PathVariable("organizationId") Long organizationId) {
        LOGGER.info("REST request to get industry group statistic, industryGroupId={}, organizationId={}", industryGroupId, organizationId);
        List<IndustryStatisticViewDTO> industryStatisticViewDTOs = statisticFacade.findIndustryGroupStatisticByGroupIdAndOrganizationId(industryGroupId, organizationId);
        LOGGER.info("StatisticController.findIndustryGroupStatistic() finished");
        return new ResponseEntity<>(industryStatisticViewDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/organization/{organizationId}/time/after",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimeAfterCompanyStatisticDTO>> timeAfterCompanyStatistic(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("REST request to get time after company statistic, organizationId={}", organizationId);
        List<TimeAfterCompanyStatisticDTO> timeAfterCompanyStatisticDTOs = statisticFacade.findTimeAfterCompanyStatistic(organizationId);
        LOGGER.info("StatisticController.timeAfterCompanyStatistic() finished");
        return new ResponseEntity<>(timeAfterCompanyStatisticDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/organization/{organizationId}/time/turnover",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeTurnoverStatisticDTO>> turnoverStatistic(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("REST request to get time turnover statistic, organizationId={}", organizationId);
        List<EmployeeTurnoverStatisticDTO> employeeTurnoverStatisticDTOs = statisticFacade.findEmployeeTurnoverStatistic(organizationId);
        LOGGER.info("StatisticController.turnoverStatistic() finished");
        return new ResponseEntity<>(employeeTurnoverStatisticDTOs, HttpStatus.OK);
    }

}
