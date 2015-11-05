package com.optigra.youpeople.converter.linkedinperson.createdperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.dto.createperson.CreatedPersonVolunteeringDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 19.10.15.
 */
@Component("createdPersonVolunteeringDTOToVolunteeringConverter")
public class CreatedPersonVolunteeringDTOToVolunteeringConverter extends AbstractConverter<CreatedPersonVolunteeringDTO, Volunteering> {
    @Override
    public Volunteering convert(final CreatedPersonVolunteeringDTO createdPersonVolunteeringDTO, final Volunteering volunteering) {
        volunteering.setCause(createdPersonVolunteeringDTO.getCause());
        return volunteering;
    }

    @Override
    public Volunteering convert(final CreatedPersonVolunteeringDTO createdPersonVolunteeringDTO) {
        return convert(createdPersonVolunteeringDTO, new Volunteering());
    }
}
