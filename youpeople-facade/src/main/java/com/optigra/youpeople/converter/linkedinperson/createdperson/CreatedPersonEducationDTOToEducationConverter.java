package com.optigra.youpeople.converter.linkedinperson.createdperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.dto.createperson.CreatedPersonEducationDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 19.10.15.
 */
@Component("createdPersonEducationDTOToEducationConverter")
public class CreatedPersonEducationDTOToEducationConverter extends AbstractConverter<CreatedPersonEducationDTO, Education> {

    @Override
    public Education convert(final CreatedPersonEducationDTO createdPersonEducationDTO, final Education education) {
        education.setFieldOfStudy(createdPersonEducationDTO.getFieldOfStudy());
        education.setDegree(createdPersonEducationDTO.getDegree());
        return education;
    }

    @Override
    public Education convert(final CreatedPersonEducationDTO createdPersonEducationDTO) {
        return convert(createdPersonEducationDTO, new Education());
    }
}
