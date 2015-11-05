package com.optigra.youpeople.services.uploadedperson;

import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by romanmudryi on 10.09.15.
 */
public interface UploadedPersonService {

    String uploadPerson(Long organizationId, MultipartFile multipartFile);

    Page<UploadedPerson> findByOrganizationId(Long organizationId, final Pageable pageable);
}
