package com.optigra.youpeople.facades.uploadedperson;

import com.optigra.youpeople.dto.UploadedPersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by romanmudryi on 03.10.15.
 */
public interface UploadedPersonFacade {

    String uploadPerson(Long organizationId, MultipartFile multipartFile);

    Page<UploadedPersonDTO> searchByOrganizationId(Long organizationId, Pageable pageable);
}
