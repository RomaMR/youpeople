package com.optigra.youpeople.converter.uploadedperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import com.optigra.youpeople.dto.createperson.CreatePersonDTO;
import com.optigra.youpeople.persistence.repository.organization.OrganizationRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 18.10.15.
 */
@Component("defaultCreatedPersonDTOToUploadedPersonConverter")
public class DefaultCreatedPersonDTOToUploadedPersonConverter extends AbstractConverter<CreatePersonDTO, UploadedPerson> {

    @Resource(name = "organizationRepository")
    private OrganizationRepository organizationRepository;
    @Override
    public UploadedPerson convert(final CreatePersonDTO createPersonDTO, final UploadedPerson uploadedPerson) {
        uploadedPerson.setFirstName(createPersonDTO.getFirstName());
        uploadedPerson.setLastName(createPersonDTO.getLastName());
        uploadedPerson.setWorkEmail(createPersonDTO.getWorkEmail());
        uploadedPerson.setPersonalEmail(createPersonDTO.getPersonalEmail());
        uploadedPerson.setCountry(createPersonDTO.getLocality());
        if (createPersonDTO.getOrganizationId() != null) {
            Organization organization = organizationRepository.findOne(createPersonDTO.getOrganizationId());
            uploadedPerson.setOrganization(organization);
        }
        return uploadedPerson;
    }

    @Override
    public UploadedPerson convert(final CreatePersonDTO createPersonDTO) {
        return convert(createPersonDTO, new UploadedPerson());
    }
}
