package com.optigra.youpeople.converter.uploadedperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import com.optigra.youpeople.dto.UploadedPersonDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 09.10.15.
 */
@Component("defaultUploadedPersonDTOConverter")
public class DefaultUploadedPersonDTOConverter extends AbstractConverter<UploadedPerson, UploadedPersonDTO> {
    @Override
    public UploadedPersonDTO convert(final UploadedPerson uploadedPerson, final UploadedPersonDTO uploadedPersonDTO) {
        uploadedPersonDTO.setId(uploadedPerson.getId());
        uploadedPersonDTO.setFirstName(uploadedPerson.getFirstName());
        uploadedPersonDTO.setLastName(uploadedPerson.getLastName());
        uploadedPersonDTO.setPersonalEmail(uploadedPerson.getPersonalEmail());
        uploadedPersonDTO.setPersonalPhone(uploadedPerson.getPersonalPhone());
        uploadedPersonDTO.setCountry(uploadedPerson.getCountry());
        uploadedPersonDTO.setCity(uploadedPerson.getCity());
        uploadedPersonDTO.setSchool(uploadedPerson.getSchool());
        return uploadedPersonDTO;
    }

    @Override
    public UploadedPersonDTO convert(final UploadedPerson uploadedPerson) {
        return convert(uploadedPerson, new UploadedPersonDTO());
    }
}
