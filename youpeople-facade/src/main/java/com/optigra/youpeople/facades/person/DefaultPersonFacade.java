package com.optigra.youpeople.facades.person;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import com.optigra.youpeople.dto.createperson.CreatePersonDTO;
import com.optigra.youpeople.services.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 19.10.15.
 */
@Component("defaultPersonFacade")
public class DefaultPersonFacade implements PersonFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonFacade.class);

    @Resource(name = "defaultPersonService")
    private PersonService personService;

    @Resource(name = "defaultCreatedPersonDTOToUploadedPersonConverter")
    private Converter<CreatePersonDTO, UploadedPerson> createdPersonDTOToUploadedPersonConverter;

    @Resource(name = "defaultCreatedPersonDTOToLinkedinPersonConverter")
    private Converter<CreatePersonDTO, LinkedinPerson> createdPersonDTOToLinkedinPersonConverter;

    @Override
    public void create(final CreatePersonDTO createPersonDTO) {
        LOGGER.info("DefaultPersonFacade.create()");
        UploadedPerson uploadedPerson = createdPersonDTOToUploadedPersonConverter.convert(createPersonDTO);
        LinkedinPerson linkedinPerson = createdPersonDTOToLinkedinPersonConverter.convert(createPersonDTO);
        personService.create(uploadedPerson, linkedinPerson);
        LOGGER.info("DefaultPersonFacade.create() finished");
    }
}
