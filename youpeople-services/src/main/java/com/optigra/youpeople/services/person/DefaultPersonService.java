package com.optigra.youpeople.services.person;

import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.person.Person;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import com.optigra.youpeople.persistence.repository.linkedinperson.LinkedinPersonRepository;
import com.optigra.youpeople.persistence.repository.person.PersonRepository;
import com.optigra.youpeople.persistence.repository.uploadedperson.UploadedPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 18.10.15.
 */
@Service("defaultPersonService")
public class DefaultPersonService implements PersonService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonService.class);

    @Resource(name = "personRepository")
    private PersonRepository personRepository;

    @Resource(name = "uploadedPersonRepository")
    private UploadedPersonRepository uploadedPersonRepository;

    @Resource(name = "linkedinPersonRepository")
    private LinkedinPersonRepository linkedinPersonRepository;

    @Override
    public void create(final UploadedPerson uploadedPerson, final LinkedinPerson linkedinPerson) {
        LOGGER.info("DefaultPersonFacade.create()");
        LinkedinPerson cratedLinkedinPerson = linkedinPersonRepository.findByLinkedinProfileId(linkedinPerson.getLinkedinProfileId());
        UploadedPerson createdUploadedPerson = uploadedPersonRepository.findByPersonalEmail(uploadedPerson.getPersonalEmail());
        Person person = null;
        if ((cratedLinkedinPerson != null) && (createdUploadedPerson != null)) {
            person= personRepository.findByUploadedPersonAndLinkedinPerson(createdUploadedPerson, cratedLinkedinPerson);
        }
        if (person == null) {
            person = new Person();
            person.setLinkedinPerson(linkedinPerson);
            person.setUploadedPerson(uploadedPerson);
            personRepository.save(person);
        }
        LOGGER.info("DefaultPersonFacade.create() finished");
    }
}
