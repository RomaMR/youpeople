package com.optigra.youpeople.persistence.repository.search;

import com.optigra.youpeople.domain.searchlog.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by romanmudryi on 05.08.15.
 */
public interface SearchLogRepository extends JpaRepository<SearchLog, Long>, QueryDslPredicateExecutor<SearchLog> {

    @Query("SELECT s FROM SearchLog s WHERE s.query = (SELECT o.name FROM Organization o WHERE o.id =:organizationId)" +
            "ORDER BY s.searchTimestamp DESC")
    List<SearchLog> findFirst1ByOrhanizationIdOrderBySearchTimestampDesc(@Param("organizationId") Long organizationId);

}
