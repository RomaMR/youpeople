package com.optigra.youpeople.web.pagination;

import com.optigra.youpeople.web.pagination.order.OrderEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

import java.net.URISyntaxException;

/**
 * Created by romanmudryi on 19.08.15.
 */
public class PaginationUtil {

    public static final int DEFAULT_OFFSET = 1;

    public static final int MIN_OFFSET = 1;

    public static final int DEFAULT_LIMIT = 10;

    public static final int MAX_LIMIT = 20;

    public PaginationUtil(Enum[] permittedValues) {
        this.permittedValues = permittedValues;
    }

    private Enum[] permittedValues;

    public Pageable generatePageRequest(Integer offset, Integer limit, String order) {

        Sort sort = validate(order);

        if (offset == null || offset < MIN_OFFSET) {
            offset = DEFAULT_OFFSET;
        }
        if (limit == null || limit > MAX_LIMIT) {
            limit = DEFAULT_LIMIT;
        }
        return new PageRequest(offset - 1, limit, sort);
    }

    public <T> HttpHeaders generatePaginationHttpHeaders(final Page<T> page) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        headers.add("X-Page", String.valueOf(page.getNumber()));
        headers.add("X-Limit", String.valueOf(page.getNumberOfElements()));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count, X-Page, X-Limit");
        return headers; //todo throw
    }

    private Sort validate(String order) {
        Sort sortOrder = null;
        if (StringUtils.isNotBlank(order)) {
            Sort.Direction sortDirection;
            String sortField;
            if(order.startsWith("-")){
                sortDirection = Sort.Direction.DESC;
                sortField = order.substring(1);
            } else {
                sortDirection = Sort.Direction.ASC;
                sortField = order;
            }
            for (Enum element : permittedValues){
                if (((OrderEnum) element).getValue().equals(sortField)) {
                    sortOrder = new Sort(new Sort.Order(sortDirection, sortField));
                }
            }
        }
        return sortOrder;
    }
}