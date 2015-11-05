package com.optigra.youpeople.converter.statistic;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.view.industry.IndustryGroupStatisticView;
import com.optigra.youpeople.dto.IndustryStatisticViewDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by romanmudryi on 03.09.15.
 */
@Component("defaultIndustryStatisticViewConverter")
public class DefaultIndustryStatisticViewConverter extends AbstractConverter<IndustryGroupStatisticView, IndustryStatisticViewDTO> {
    @Override
    public IndustryStatisticViewDTO convert(IndustryGroupStatisticView industryStatisticView, IndustryStatisticViewDTO industryStatisticViewDTO) {
        industryStatisticViewDTO.setId(industryStatisticView.getId());
        industryStatisticViewDTO.setName(industryStatisticView.getName());
        industryStatisticViewDTO.setCount(industryStatisticView.getCount());
        return industryStatisticViewDTO;
    }

    @Override
    public IndustryStatisticViewDTO convert(IndustryGroupStatisticView industryStatisticView) {
        return convert(industryStatisticView, new IndustryStatisticViewDTO());
    }

    @Override
    public List<IndustryStatisticViewDTO> convertAll(List<IndustryGroupStatisticView> industryStatisticViews) {
        return super.convertAll(industryStatisticViews);
    }

}
