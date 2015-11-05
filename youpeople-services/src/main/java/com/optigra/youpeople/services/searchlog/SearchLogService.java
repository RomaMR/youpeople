package com.optigra.youpeople.services.searchlog;

import com.optigra.youpeople.domain.searchlog.SearchLog;

import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
public interface SearchLogService {

    List<SearchLog> findByOrganizationId(Long organizationId);

    SearchLog save(SearchLog search);

    void delete(Long id);

    SearchLog findOne(Long id);

    SearchLog saveBySearchQuery(String searchQuery);
}
