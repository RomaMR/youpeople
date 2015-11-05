package com.optigra.youpeople.converter.industry.group;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.industry.group.IndustryGroup;
import com.optigra.youpeople.dto.IndustryGroupDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Component("defaultIndustryGroupDTOConverter")
public class DefaultIndustryGroupDTOConverter extends AbstractConverter<IndustryGroup, IndustryGroupDTO> {
    @Override
    public IndustryGroupDTO convert(IndustryGroup industryGroup, IndustryGroupDTO industryGroupDTO) {
        industryGroupDTO.setId(industryGroup.getId());
        industryGroupDTO.setName(industryGroup.getName());
        return industryGroupDTO;
    }

    @Override
    public IndustryGroupDTO convert(IndustryGroup industryGroup) {
        return convert(industryGroup, new IndustryGroupDTO());
    }
}
