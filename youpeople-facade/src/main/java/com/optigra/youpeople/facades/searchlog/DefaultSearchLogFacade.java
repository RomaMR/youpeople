package com.optigra.youpeople.facades.searchlog;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.searchlog.SearchLog;
import com.optigra.youpeople.dto.SearchLogDTO;
import com.optigra.youpeople.services.searchlog.SearchLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultSearchLogFacade")
public class DefaultSearchLogFacade implements SearchLogFacade{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSearchLogFacade.class);

    @Resource(name = "defaultSearchLogService")
    private SearchLogService searchLogService;

    @Resource(name = "defaultSearchLogDTOConverter")
    private Converter<SearchLog, SearchLogDTO> converter;

    @Override
    public List<SearchLogDTO> findByOrganizationId(Long organizationId) {
        LOGGER.info("DefaultSearchLogFacade.findByOrganizationId(), organizationId={}", organizationId);
        List<SearchLog> searchLog = searchLogService.findByOrganizationId(organizationId);
        List<SearchLogDTO> searchLogDTO = converter.convertAll(searchLog);
        LOGGER.info("DefaultSearchLogFacade.findByOrganizationId() finished");
        return searchLogDTO;
    }

}
