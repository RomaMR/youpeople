package com.optigra.youpeople.persistence.repository.industry.statistic;

import com.optigra.youpeople.view.industry.IndustryGroupStatisticView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by romanmudryi on 01.09.15.
 */
public interface IndustryGroupStatisticViewRepository extends JpaRepository<IndustryGroupStatisticView, Long> {

        @Query(value = "SELECT * FROM industry_by_group_statistic (?1)", nativeQuery = true)
        List<IndustryGroupStatisticView> findByGroupId(Long id);

        @Query(value = "SELECT * FROM industry_group_by_organization_statistic (?1)", nativeQuery = true)
        List<IndustryGroupStatisticView> findIndustryGroupStatisticByOrganizationId(Long organizationId);

        @Query(value = "SELECT * FROM industry_by_group_and_organization_statistic (?1, ?2)", nativeQuery = true)
        List<IndustryGroupStatisticView> findIndustryStatisticByGroupIdAndOrganizationId(Long groupId, Long organizationId);

        @Query(value = "SELECT * FROM industry_main_group_by_organization_statistic (?1)", nativeQuery = true)
        List<IndustryGroupStatisticView> findMainIndustryGroupStatisticByOrganizationId(Long organizationId);

        @Query(value = "SELECT * FROM industry_by_main_group_and_organization_statistic (?1, ?2)", nativeQuery = true)
        List<IndustryGroupStatisticView> findMainIndustryStatisticByGroupIdAndOrganizationId(Long groupId, Long organizationId);
}
