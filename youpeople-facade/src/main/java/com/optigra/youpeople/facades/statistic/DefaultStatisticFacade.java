package com.optigra.youpeople.facades.statistic;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.dto.EmployeeTurnoverStatisticDTO;
import com.optigra.youpeople.dto.IndustryStatisticViewDTO;
import com.optigra.youpeople.dto.TimeAfterCompanyStatisticDTO;
import com.optigra.youpeople.persistence.repository.linkedinperson.LinkedinPersonRepository;
import com.optigra.youpeople.services.linkedinperson.LinkedinPersonService;
import com.optigra.youpeople.services.statistic.StatisticService;
import com.optigra.youpeople.view.industry.IndustryGroupStatisticView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by romanmudryi on 02.09.15.
 */
@Component("defaultStatisticFacade")
public class DefaultStatisticFacade implements StatisticFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultStatisticFacade.class);

    private Map<String, String> timeAfterCompany;

    private Map<String, String> timeTurnover;

    @Resource(name = "linkedinPersonRepository")//TODO remove it after presentation
    private LinkedinPersonRepository personRepository;

    @Resource(name = "defaultStatisticService")
    private StatisticService statisticService;

    @Resource(name = "defaultLinkedinPersonService")
    private LinkedinPersonService personService;

    @Resource(name = "defaultIndustryStatisticViewConverter")
    private Converter<IndustryGroupStatisticView, IndustryStatisticViewDTO> industryStatisticViewConverter;

    @Override
    public List<IndustryStatisticViewDTO> findIndustryGroupStatisticByOrganizationId(Long organizationId) {
        LOGGER.info("DefaultStatisticFacade.findIndustryGroupStatisticByOrganizationId(), organizationId={}", organizationId);
        List<IndustryGroupStatisticView> industryStatisticViews = statisticService.findIndustryGroupStatisticByOrganizationId(organizationId);
        List<IndustryStatisticViewDTO> industryStatisticViewDTOs = industryStatisticViewConverter.convertAll(industryStatisticViews);
        LOGGER.info("DefaultStatisticFacade.findIndustryGroupStatistic() finished");
        return industryStatisticViewDTOs;
    }

    @Override
    public List<IndustryStatisticViewDTO> findIndustryGroupStatisticByGroupIdAndOrganizationId(Long industryGroupId, Long organizationId) {
        LOGGER.info("DefaultStatisticFacade.findIndustryGroupStatisticByGroupIdAndOrganizationId(), industryGroupId={}, organizationId={}", industryGroupId, organizationId);
        List<IndustryGroupStatisticView> industryStatisticViews = statisticService.findIndustryGroupStatisticByGroupIdAndOrganizationId(industryGroupId, organizationId);
        List<IndustryStatisticViewDTO> industryStatisticViewDTOs = industryStatisticViewConverter.convertAll(industryStatisticViews);
        LOGGER.info("DefaultStatisticFacade.findIndustryGroupStatistic() finished");
        return industryStatisticViewDTOs;
    }

    @Override
    public List<TimeAfterCompanyStatisticDTO> findTimeAfterCompanyStatistic(Long organizationId) {
        LOGGER.info("DefaultStatisticFacade.findTimeAfterCompanyStatistic(), organizationId={}", organizationId);
        List<TimeAfterCompanyStatisticDTO> timeAfterCompanyStatisticDTOs = new ArrayList<>();
        for (Map.Entry<String, String> entry : timeAfterCompany.entrySet()) {
            TimeAfterCompanyStatisticDTO timeAfterCompanyStatisticDTO = new TimeAfterCompanyStatisticDTO();
            timeAfterCompanyStatisticDTO.setName(entry.getKey());
            Long count = personService.countPersonByOrganizationAndWorkStatus(organizationId, entry.getValue());
            timeAfterCompanyStatisticDTO.setCount(count);
            timeAfterCompanyStatisticDTO.setWorkStatus(entry.getValue());
            timeAfterCompanyStatisticDTOs.add(timeAfterCompanyStatisticDTO);
        }
        LOGGER.info("DefaultStatisticFacade.findTimeAfterCompanyStatistic() finished");
        return timeAfterCompanyStatisticDTOs;
    }

    @Override
    public List<EmployeeTurnoverStatisticDTO> findEmployeeTurnoverStatistic(Long organizationId) {
        LOGGER.info("DefaultStatisticFacade.findEmployeeTurnoverStatistic(), organizationId={}", organizationId);
        List<EmployeeTurnoverStatisticDTO> employeeTurnoverStatisticDTOs = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, String> entry : timeTurnover.entrySet()) {
            EmployeeTurnoverStatisticDTO employeeTurnoverStatisticDTO = new EmployeeTurnoverStatisticDTO();
            employeeTurnoverStatisticDTO.setYear(entry.getKey());
            employeeTurnoverStatisticDTO.setWorkStatus(entry.getValue());
            Long count = personService.countPersonByOrganizationAndWorkStatus(organizationId, entry.getValue());
            employeeTurnoverStatisticDTO.setCount(count);
            Long fired = personService.countPersonByOrganizationAndWorkStatus(organizationId, "<" + entry.getValue());
            employeeTurnoverStatisticDTO.setFired(fired);
            Long hired = personService.countPersonByOrganizationAndWorkStatus(organizationId, ">" + entry.getValue());
            employeeTurnoverStatisticDTO.setHired(hired);
            BigDecimal turnover = BigDecimal.valueOf(fired);
            if (i == 0) {
                BigDecimal divider = new BigDecimal((count + count + hired - fired) / 2);
                if (!(divider.compareTo(BigDecimal.ZERO) == 0)) {
                    turnover = turnover.divide(new BigDecimal((count + count + hired - fired) / 2), 2, BigDecimal.ROUND_HALF_UP);
                }
            } else {
                BigDecimal divider = new BigDecimal((count + employeeTurnoverStatisticDTOs.get(i - 1).getCount()) / 2);
                if (!(divider.compareTo(BigDecimal.ZERO) == 0)) {
                    turnover = turnover.divide(divider, 2, BigDecimal.ROUND_HALF_UP);
                }
            }
            employeeTurnoverStatisticDTO.setTurnover(turnover);
            employeeTurnoverStatisticDTOs.add(employeeTurnoverStatisticDTO);
            i++;
        }
        LOGGER.info("DefaultStatisticFacade.findEmployeeTurnoverStatistic() finished");
        return employeeTurnoverStatisticDTOs;
    }

    @PostConstruct
    public void init() {
        Map<String, String> timeAfterCompany = new LinkedHashMap<>();
        timeAfterCompany.put("Active", ">");
        timeAfterCompany.put("0-1", "<0-1");
        timeAfterCompany.put("1-2", "<1-2");
        timeAfterCompany.put("2-4", "<2-4");
        timeAfterCompany.put("4-6", "<4-6");
        timeAfterCompany.put("6-10", "<6-10");
        timeAfterCompany.put(">10", "<10-100");
        this.timeAfterCompany = timeAfterCompany;
        Map<String, String> timeTurnover = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - (i + 1));
            timeTurnover.put(year, i + "-" + (i + 1));
        }
        this.timeTurnover = timeTurnover;
    }

}
