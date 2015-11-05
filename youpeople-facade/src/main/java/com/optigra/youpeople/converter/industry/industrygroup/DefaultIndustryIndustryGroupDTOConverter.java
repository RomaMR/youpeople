package com.optigra.youpeople.converter.industry.industrygroup;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.industry.group.IndustryGroup;
import com.optigra.youpeople.domain.industry.industrygroups.IndustryIndustryGroup;
import com.optigra.youpeople.dto.IndustryGroupDTO;
import com.optigra.youpeople.dto.IndustryIndustryGroupDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Component("defaultIndustryIndustryGroupDTOConverter")
public class DefaultIndustryIndustryGroupDTOConverter extends AbstractConverter<IndustryIndustryGroup, IndustryIndustryGroupDTO> {

    @Resource(name = "defaultIndustryGroupDTOConverter")
    private Converter<IndustryGroup, IndustryGroupDTO> industryGroupDTOConverter;

    @Override
    public IndustryIndustryGroupDTO convert(IndustryIndustryGroup industryIndustryGroup, IndustryIndustryGroupDTO industryIndustryGroupDTO) {
        industryIndustryGroupDTO.setId(industryIndustryGroup.getId());
        industryIndustryGroupDTO.setMain(industryIndustryGroup.getMain());
        if (industryIndustryGroup.getIndustryGroup() != null) {
            industryIndustryGroupDTO.setIndustryGroup(industryGroupDTOConverter.convert(industryIndustryGroup.getIndustryGroup()));
        }
        return industryIndustryGroupDTO;
    }

    @Override
    public IndustryIndustryGroupDTO convert(IndustryIndustryGroup industryIndustryGroup) {
        return convert(industryIndustryGroup, new IndustryIndustryGroupDTO());
    }
}
