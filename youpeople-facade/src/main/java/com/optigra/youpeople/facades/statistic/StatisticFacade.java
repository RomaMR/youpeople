package com.optigra.youpeople.facades.statistic;

import com.optigra.youpeople.dto.EmployeeTurnoverStatisticDTO;
import com.optigra.youpeople.dto.IndustryStatisticViewDTO;
import com.optigra.youpeople.dto.TimeAfterCompanyStatisticDTO;

import java.util.List;

/**
 * Created by romanmudryi on 02.09.15.
 */
public interface StatisticFacade {

    List<IndustryStatisticViewDTO> findIndustryGroupStatisticByOrganizationId(Long organizationId);

    List<IndustryStatisticViewDTO> findIndustryGroupStatisticByGroupIdAndOrganizationId(Long industryGroupId, Long organizationId);

    List<TimeAfterCompanyStatisticDTO> findTimeAfterCompanyStatistic(Long organizationId);

    List<EmployeeTurnoverStatisticDTO> findEmployeeTurnoverStatistic(Long organizationId);

}
