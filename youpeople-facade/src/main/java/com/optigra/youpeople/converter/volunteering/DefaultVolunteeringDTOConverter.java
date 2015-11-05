package com.optigra.youpeople.converter.volunteering;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.PersonDTO;
import com.optigra.youpeople.dto.VolunteeringDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 15.08.15.
 */
@Component("defaultVolunteeringDTOConverter")
public class DefaultVolunteeringDTOConverter  extends AbstractConverter<Volunteering, VolunteeringDTO> {

    @Resource(name = "lazyOrganizationDTOConverter")
    private Converter<Organization, OrganizationDTO> organizationDTOConverter;

    @Resource(name = "lazyPersonDTOConverter")
    private Converter<LinkedinPerson, PersonDTO> personDTOConverter;

    @Override
    public VolunteeringDTO convert(Volunteering volunteering, VolunteeringDTO volunteeringDTO) {
        volunteeringDTO.setId(volunteering.getId());
        volunteeringDTO.setCause(volunteering.getCause());
        volunteeringDTO.setStartDate(volunteering.getStartDate());
        volunteeringDTO.setEndDate(volunteering.getEndDate());
        volunteeringDTO.setOrganization(organizationDTOConverter.convert(volunteering.getOrganization()));
        volunteeringDTO.setPerson(personDTOConverter.convert(volunteering.getLinkedinPerson()));
        return volunteeringDTO;
    }

    @Override
    public VolunteeringDTO convert(Volunteering volunteering) {
        return convert(volunteering, new VolunteeringDTO());
    }

    @Override
    public List<VolunteeringDTO> convertAll(List<Volunteering> volunteerings) {
        return super.convertAll(volunteerings);
    }
}
