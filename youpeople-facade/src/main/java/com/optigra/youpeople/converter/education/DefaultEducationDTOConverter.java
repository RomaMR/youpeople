package com.optigra.youpeople.converter.education;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.dto.EducationDTO;
import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.PersonDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 15.08.15.
 */
@Component("defaultEducationDTOConverter")
public class DefaultEducationDTOConverter  extends AbstractConverter<Education, EducationDTO> {

    @Resource(name = "lazyOrganizationDTOConverter")
    private Converter<Organization, OrganizationDTO> organizationDTOConverter;

    @Resource(name = "lazyPersonDTOConverter")
    private Converter<LinkedinPerson, PersonDTO> personDTOConverter;

    @Override
    public EducationDTO convert(Education education, EducationDTO educationDTO) {
        educationDTO.setId(education.getId());
        educationDTO.setDegree(education.getDegree());
        educationDTO.setFieldOfStudy(education.getFieldOfStudy());
        educationDTO.setStartDate(education.getStartDate());
        educationDTO.setEndDate(education.getEndDate());
        educationDTO.setOrganization(organizationDTOConverter.convert(education.getOrganization()));
        educationDTO.setPerson(personDTOConverter.convert(education.getLinkedinPerson()));
        return educationDTO;
    }

    @Override
    public EducationDTO convert(Education education) {
        return convert(education, new EducationDTO());
    }

    @Override
    public List<EducationDTO> convertAll(List<Education> educations) {
        return super.convertAll(educations);
    }
}
