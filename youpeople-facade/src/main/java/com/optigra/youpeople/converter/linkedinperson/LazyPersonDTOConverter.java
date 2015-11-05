package com.optigra.youpeople.converter.linkedinperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.dto.IndustryDTO;
import com.optigra.youpeople.dto.PersonDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 13.08.15.
 */
@Component("lazyPersonDTOConverter")
public class LazyPersonDTOConverter extends AbstractConverter<LinkedinPerson, PersonDTO> {

    @Resource(name = "lazyIndustryDTOConverter")
    private Converter<Industry, IndustryDTO> industryDTOConverter;

    @Override
    public PersonDTO convert(LinkedinPerson person, PersonDTO personDTO) {
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setPersonalEmail(person.getPersonalEmail());
        personDTO.setWorkEmail(person.getWorkEmail());
        personDTO.setLinkedinProfileId(person.getLinkedinProfileId());
        personDTO.setLocality(person.getLocality());
        personDTO.setNumberOfConnections(person.getNumberOfConnections());
        personDTO.setProfilePicture(person.getProfilePicture());
        if (person.getIndustry() != null) {
            personDTO.setIndustry(industryDTOConverter.convert(person.getIndustry()));
        }
        return personDTO;
    }

    @Override
    public PersonDTO convert(LinkedinPerson person) {
        return convert(person, new PersonDTO());
    }
}
