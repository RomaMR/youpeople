package com.optigra.youpeople.services.statistic;


import com.optigra.youpeople.view.industry.IndustryGroupStatisticView;

import java.util.List;

/**
 * Created by romanmudryi on 02.09.15.
 */
public interface StatisticService {

    List<IndustryGroupStatisticView> findIndustryGroupStatisticByOrganizationId(Long organizationId);

    List<IndustryGroupStatisticView> findIndustryGroupStatisticByGroupIdAndOrganizationId(Long industryGroupId, Long organizationId);

}
