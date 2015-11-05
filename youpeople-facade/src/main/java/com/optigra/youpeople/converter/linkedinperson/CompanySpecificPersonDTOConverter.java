package com.optigra.youpeople.converter.linkedinperson;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.dto.PersonDTO;
import org.springframework.stereotype.Component;

@Component("companySpecificPersonDTOConverter")
public class CompanySpecificPersonDTOConverter extends DefaultPersonDTOConverter implements Converter<LinkedinPerson, PersonDTO> {

	@Override
	public PersonDTO convert(LinkedinPerson person, PersonDTO personDTO) {
		PersonDTO result = super.convert(person, personDTO);
		return result;
	}
	

}
