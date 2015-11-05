package com.optigra.youpeople.facades.uploadedperson;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import com.optigra.youpeople.dto.UploadedPersonDTO;
import com.optigra.youpeople.services.uploadedperson.UploadedPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 03.10.15.
 */
@Component("defaultUploadedPersonFacade")
public class DefaultUploadedPersonFacade implements UploadedPersonFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUploadedPersonFacade.class);

    @Resource(name = "defaultUploadedPersonService")
    private UploadedPersonService uploadedPersonService;

    @Resource(name = "defaultUploadedPersonDTOConverter")
    private Converter<UploadedPerson, UploadedPersonDTO> uploadedPersonDTOConverter;

    @Override
    public String uploadPerson(Long organizationId, MultipartFile multipartFile) {
        LOGGER.info("DefaultLinkedinPersonFacade.getByOrganizationId(), organizationId={}", organizationId);
        String message = uploadedPersonService.uploadPerson(organizationId, multipartFile);
        LOGGER.info("DefaultLinkedinPersonFacade.getByOrganizationId() finished");
        return message;
    }

    @Override
    public Page<UploadedPersonDTO> searchByOrganizationId(final Long organizationId, Pageable pageable) {
        LOGGER.info("DefaultLinkedinPersonFacade.searchByOrganizationId(), organizationId={}", organizationId);
        Page<UploadedPerson> page = uploadedPersonService.findByOrganizationId(organizationId, pageable);
        LOGGER.info("DefaultLinkedinPersonFacade.searchByOrganizationId() finished");
        return new PageImpl<>(uploadedPersonDTOConverter.convertAll(page.getContent()), pageable, page.getTotalElements());
    }
}
