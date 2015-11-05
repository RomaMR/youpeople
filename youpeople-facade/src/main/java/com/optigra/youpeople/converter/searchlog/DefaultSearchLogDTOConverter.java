package com.optigra.youpeople.converter.searchlog;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.searchlog.SearchLog;
import com.optigra.youpeople.dto.SearchLogDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultSearchLogDTOConverter")
public class DefaultSearchLogDTOConverter extends AbstractConverter<SearchLog, SearchLogDTO> {
    @Override
    public SearchLogDTO convert(SearchLog searchLog, SearchLogDTO searchDTO) {
        searchDTO.setId(searchLog.getId());
        searchDTO.setQuery(searchLog.getQuery());
        searchDTO.setSearchTimestamp(searchLog.getSearchTimestamp());
        return searchDTO;
    }

    @Override
    public SearchLogDTO convert(SearchLog searchLog) {
        return convert(searchLog, new SearchLogDTO());
    }
}
