package com.optigra.youpeople.facades.searchlog;

import com.optigra.youpeople.dto.SearchLogDTO;

import java.util.List;

/**
 * Created by romanmudryi on 07.08.15.
 */
public interface SearchLogFacade {
    List<SearchLogDTO> findByOrganizationId(Long query);
}
