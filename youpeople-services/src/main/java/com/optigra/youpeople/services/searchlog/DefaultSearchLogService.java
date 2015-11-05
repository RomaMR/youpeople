package com.optigra.youpeople.services.searchlog;

import com.optigra.youpeople.domain.searchlog.SearchLog;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.persistence.repository.search.SearchLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
@Service("defaultSearchLogService")
public class DefaultSearchLogService implements SearchLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSearchLogService.class);

    @Resource(name = "searchLogRepository")
    private SearchLogRepository searchLogRepository;

    @Override
    public SearchLog findOne(Long id) {
        LOGGER.info("DefaultSearchLogService.findByQuery(), id={}", id);
        SearchLog searchLog = searchLogRepository.findOne(id);
        if (searchLog == null) {
            LOGGER.error("SearchLog not found, id={}", id);
            throw new ContentNotFoundException("SearchLog not found");
        }
        LOGGER.info("DefaultSearchLogService.findByQuery() finished");
        return searchLog;
    }

    @Override
    public List<SearchLog> findByOrganizationId(Long organizationId) {
        LOGGER.info("DefaultSearchLogService.findByOrganizationId(), organizationId={}", organizationId);
        List<SearchLog> searchLogs = searchLogRepository.findFirst1ByOrhanizationIdOrderBySearchTimestampDesc(organizationId);
        if (searchLogs.size() == 0) {
            LOGGER.error("SearchLogs not found, organizationId={}", organizationId);
            throw new ContentNotFoundException("SearchLog not found");
        }
        LOGGER.info("DefaultSearchLogService.findByOrganizationId() finished");
        return searchLogs;
    }

    @Override
    public SearchLog save(SearchLog searchLog) {
        LOGGER.info("DefaultSearchLogService.save()");
        SearchLog savedSearchLog = searchLogRepository.save(searchLog);
        LOGGER.info("DefaultSearchLogService.save() finished");
        return savedSearchLog;
    }

    @Override
    public SearchLog saveBySearchQuery(String searchQuery) {
        LOGGER.info("DefaultSearchLogService.saveBySearchQuery(), searchQuery={}", searchQuery);
        SearchLog searchLog = new SearchLog();
        searchLog.setQuery(searchQuery);
        searchLog.setSearchTimestamp(new Date());
        LOGGER.info("DefaultSearchLogService.saveBySearchQuery() finished");
        return searchLogRepository.save(searchLog);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("DefaultSearchLogService.delete(), id={}", id);
        searchLogRepository.delete(id);
        LOGGER.info("DefaultSearchLogService.delete() finished");
    }
}
