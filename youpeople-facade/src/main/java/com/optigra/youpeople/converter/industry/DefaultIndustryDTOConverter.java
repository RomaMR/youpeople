package com.optigra.youpeople.converter.industry;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.industry.industrygroups.IndustryIndustryGroup;
import com.optigra.youpeople.dto.IndustryDTO;
import com.optigra.youpeople.dto.IndustryIndustryGroupDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Component("defaultIndustryDTOConverter")
public class DefaultIndustryDTOConverter extends AbstractConverter<Industry, IndustryDTO> {

    @Resource(name = "defaultIndustryIndustryGroupDTOConverter")
    private Converter<IndustryIndustryGroup, IndustryIndustryGroupDTO> industryIndustryGroupDTOConverter;

    @Override
    public IndustryDTO convert(Industry industry, IndustryDTO industryDTO) {
        industryDTO.setId(industry.getId());
        industryDTO.setName(industry.getName());
        if (industry.getIndustryIndustryGroups() != null) {
            industryDTO.setIndustryIndustryGroups(industryIndustryGroupDTOConverter.convertAll(industry.getIndustryIndustryGroups()));
        }
        return industryDTO;
    }

    @Override
    public IndustryDTO convert(Industry industry) {
        return convert(industry, new IndustryDTO());
    }
}
