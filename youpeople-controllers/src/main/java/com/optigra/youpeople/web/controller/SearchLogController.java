package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.dto.SearchLogDTO;
import com.optigra.youpeople.facades.searchlog.SearchLogFacade;
import com.optigra.youpeople.web.commons.RMPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
@RestController
@RequestMapping(RMPath.SearchLog.ROOT)
public class SearchLogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchLogController.class);

    @Resource(name = "defaultSearchLogFacade")
    private SearchLogFacade searchLogFacade;

    @RequestMapping(value = "organization/{organizationId}/log", method = RequestMethod.GET)
    public ResponseEntity<List<SearchLogDTO>> searchByOrganizationId(@PathVariable("organizationId") final Long organizationId) {
        LOGGER.info("SearchLogController.searchByOrganizationId(), organizationId={}", organizationId);
        List<SearchLogDTO> searchLogDTO = searchLogFacade.findByOrganizationId(organizationId);
        LOGGER.info("PersonController.searchByOrganizationId() finished");
        return new ResponseEntity<>(searchLogDTO, HttpStatus.OK);
    }

}
