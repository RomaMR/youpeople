package com.optigra.youpeople.converter.industry;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.dto.IndustryDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Component("lazyIndustryDTOConverter")
public class LazyIndustryDTOConverter extends AbstractConverter<Industry, IndustryDTO> {

    @Override
    public IndustryDTO convert(Industry industry, IndustryDTO industryDTO) {
        industryDTO.setId(industry.getId());
        industryDTO.setName(industry.getName());
        return industryDTO;
    }

    @Override
    public IndustryDTO convert(Industry industry) {
        return convert(industry, new IndustryDTO());
    }
}
