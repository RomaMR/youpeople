package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.OrganizationPersonsPropertiesDTO;
import com.optigra.youpeople.facades.organization.OrganizationFacade;
import com.optigra.youpeople.web.commons.RMPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
@RestController
@RequestMapping(RMPath.Organization.ROOT)
public class OrganizationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Resource(name = "defaultOrganizationFacade")
    private OrganizationFacade organizationFacade;

    @RequestMapping(value = "{organizationId}/persons/properties", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrganizationPersonsPropertiesDTO> findPersonsProperties(@PathVariable("organizationId") final Long organizationId){
        LOGGER.info("OrganizationController.getPersonsProperties(), organizationId={}", organizationId);

        OrganizationPersonsPropertiesDTO organizationPersonsPropertiesDTO = organizationFacade.findPersonsProperties(organizationId);

        LOGGER.info("OrganizationController.getPersonsProperties() finished");
        return new ResponseEntity<>(organizationPersonsPropertiesDTO, HttpStatus.OK);
    }
    
    @RequestMapping(value = RMPath.Organization.NAMES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrganizationDTO>> getUserOrganizations(){
        LOGGER.info("OrganizationController.getUserOrganizations()");

        List<OrganizationDTO> organizationDTOs = organizationFacade.getUserOrganizations();

        LOGGER.info("OrganizationController.getUserOrganizations() finished");
        return new ResponseEntity<>(organizationDTOs, HttpStatus.OK);
    }

}
