package com.optigra.youpeople.services.statistic;

import com.optigra.youpeople.persistence.repository.industry.statistic.IndustryGroupStatisticViewRepository;
import com.optigra.youpeople.view.industry.IndustryGroupStatisticView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 02.09.15.
 */
@Service("defaultStatisticService")
public class DefaultStatisticService implements StatisticService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultStatisticService.class);

    @Resource(name = "industryGroupStatisticViewRepository")
    private IndustryGroupStatisticViewRepository industryGroupStatisticViewRepository;

    @Override
    public List<IndustryGroupStatisticView> findIndustryGroupStatisticByOrganizationId(Long organizationId) {
        LOGGER.info("DefaultStatisticService.findIndustryGroupStatisticByOrganizationId(), organizationId={}", organizationId);
        List<IndustryGroupStatisticView> industryStatisticViews = industryGroupStatisticViewRepository
                .findMainIndustryGroupStatisticByOrganizationId(organizationId);
        LOGGER.info("DefaultStatisticService.findIndustryGroupStatistic() finished");
        return industryStatisticViews;
    }

    @Override
    public List<IndustryGroupStatisticView> findIndustryGroupStatisticByGroupIdAndOrganizationId(Long industryGroupId, Long organizationId) {
        LOGGER.info("DefaultStatisticService.findIndustryGroupStatisticByGroupIdAndOrganizationId(), industryGroupId={}, organizationId={}", industryGroupId, organizationId);
        List<IndustryGroupStatisticView> industryStatisticViews = industryGroupStatisticViewRepository
                .findMainIndustryStatisticByGroupIdAndOrganizationId(industryGroupId, organizationId);
        LOGGER.info("DefaultStatisticService.findIndustryGroupStatistic() finished");
        return industryStatisticViews;
    }

}
