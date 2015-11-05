package com.optigra.youpeople.services.uploadedperson;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.uploadedperson.Gender;
import com.optigra.youpeople.domain.uploadedperson.QUploadedPerson;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.exception.PersonUploadException;
import com.optigra.youpeople.persistence.repository.organization.OrganizationRepository;
import com.optigra.youpeople.persistence.repository.uploadedperson.UploadedPersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by romanmudryi on 10.09.15.
 */
@Service("defaultUploadedPersonService")
public class DefaultUploadedPersonService implements UploadedPersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUploadedPersonService.class);

    @Resource(name = "uploadedPersonRepository")
    private UploadedPersonRepository uploadedPersonRepository;

    @Resource(name = "organizationRepository")
    private OrganizationRepository organizationRepository;

    @Override
    public String uploadPerson(Long organizationId, MultipartFile multipartFile) {
        LOGGER.info("DefaultUploadedPersonService.uploadPerson(), organizationId={}", organizationId);
        int count = 0;
        Organization organization = organizationRepository.findOne(organizationId);
        if (organization == null) {
            LOGGER.error("Organization not found, organizationId={}", organizationId);
            throw new ContentNotFoundException("Organization not found");
        }
        try (InputStream is = multipartFile.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                UploadedPerson uploadedPerson;
                Row row = sheet.getRow(i);
                if ((row.getCell(1) == null) || (row.getCell(2) == null)) {
                    continue;
                }
                String firstName = row.getCell(1).getStringCellValue();
                String lastName = row.getCell(2).getStringCellValue();
                if (StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName)) {
                    continue;
                } else {
                    uploadedPerson = uploadedPersonRepository.findByFirstNameAndLastName(firstName, lastName);
                    if (uploadedPerson == null) {
                        uploadedPerson = new UploadedPerson();
                        uploadedPerson.setFirstName(firstName);
                        uploadedPerson.setLastName(lastName);
                    }
                    uploadedPerson.setOrganization(organization);
                }
                if (row.getCell(3) != null && "female".equalsIgnoreCase(row.getCell(3).getStringCellValue())) {
                    uploadedPerson.setGender(Gender.Female);
                } else {
                    uploadedPerson.setGender(Gender.Male);
                }
                Cell cell;
                cell = row.getCell(4);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setPersonalEmail(cell.getStringCellValue());
                }
                cell = row.getCell(5);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setWorkEmail(cell.getStringCellValue());
                }
                cell = row.getCell(6);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setPersonalPhone(cell.getStringCellValue());
                }
                cell = row.getCell(7);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setWorkPhone(cell.getStringCellValue());
                }
                cell = row.getCell(8);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setPosition(cell.getStringCellValue());
                }
                Date date;
                date = getDateFromCell(row.getCell(9));
                uploadedPerson.setStartDate(date);
                date = getDateFromCell(row.getCell(10));
                uploadedPerson.setEndDate(date);
                cell = row.getCell(11);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setSchool(cell.getStringCellValue());
                }
                cell = row.getCell(12);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setUniversity(cell.getStringCellValue());
                }
                cell = row.getCell(13);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setCountry(cell.getStringCellValue());
                }
                cell = row.getCell(14);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setCity(cell.getStringCellValue());
                }
                cell = row.getCell(15);
                if (cell != null && StringUtils.isNotBlank(cell.getStringCellValue())) {
                    uploadedPerson.setAddress(cell.getStringCellValue());
                }
                uploadedPersonRepository.save(uploadedPerson);
                count++;
            }

        } catch (IOException e) {
            LOGGER.error("DefaultUploadedPersonService.uploadPerson(), ", e);
            throw new PersonUploadException("Uploading failed!");
        } catch (InvalidFormatException e) {
            LOGGER.error("DefaultUploadedPersonService.uploadPerson(), Invalid Format");
            throw new PersonUploadException("Uploading failed! Invalid Format");
        }
        LOGGER.info("DefaultUploadedPersonService.uploadPerson() finished");
        return new StringBuilder().append(count).append(" people were uploaded").toString();
    }

    @Override
    public Page<UploadedPerson> findByOrganizationId(final Long organizationId, final Pageable pageable) {
        LOGGER.info("DefaultUploadedPersonService.findByOrganizationId(), organizationId={}", organizationId);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QUploadedPerson uploadedPerson = QUploadedPerson.uploadedPerson;
        booleanBuilder.and(uploadedPerson.organization.id.eq(organizationId));
        Predicate predicate = booleanBuilder.getValue();
        Page<UploadedPerson> uploadedPersons = uploadedPersonRepository.findAll(predicate, pageable);
        LOGGER.info("DefaultUploadedPersonService.findByOrganizationId() finished");
        return uploadedPersons;
    }

    private Date getDateFromCell(Cell cell) {
        String format = "MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(cell.getStringCellValue());
        } catch (Exception e) {
            LOGGER.error("DefaultUploadedPersonService.uploadPerson()", e);
        }
        return date;
    }

}
