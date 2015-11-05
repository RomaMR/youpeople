package com.optigra.youpeople.persistence.repository.linkedinperson;

import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;


/**
 * Created by romanmudryi on 05.08.15.
 */
public interface LinkedinPersonRepository extends JpaRepository<LinkedinPerson, Long>, QueryDslPredicateExecutor<LinkedinPerson> {

    LinkedinPerson findByLinkedinProfileId(String linkedinProfileId);

    @Query("SELECT p FROM LinkedinPerson p WHERE " +
            "((SELECT COUNT(j) FROM Job j WHERE (j.organization =:organization) AND (j.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(v) FROM Volunteering v WHERE (v.organization =:organization) AND (v.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(e) FROM Education e WHERE (e.organization =:organization) AND (e.linkedinPerson = p)) > 0)")
    Page<LinkedinPerson> findByOrganization(Pageable pageable, @Param("organization") Organization organization);

    @Query("SELECT p FROM LinkedinPerson p WHERE p.industry LIKE :industry AND p.locality LIKE :locality AND (" +
            "((SELECT COUNT(j) FROM Job j WHERE (j.organization =:organization) AND (j.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(v) FROM Volunteering v WHERE (v.organization =:organization) AND (v.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(e) FROM Education e WHERE (e.organization =:organization) AND (e.linkedinPerson = p)) > 0))")
    Page<LinkedinPerson> findByOrganizationAndIndustryAndLocality(Pageable pageable, @Param("organization") Organization organization, @Param("industry")
                                                            String industry, @Param("locality") String locality);

    @Query("SELECT p FROM LinkedinPerson p WHERE p.industry LIKE :industry AND p.locality LIKE :locality AND (" +
            "((SELECT COUNT(j) FROM Job j WHERE (j.organization =:organization) AND (j.linkedinPerson = p) AND (j.endDate IS NULL)) > 0) OR " +
            "((SELECT COUNT(v) FROM Volunteering v WHERE (v.organization =:organization) AND (v.linkedinPerson = p) AND (v.endDate IS NULL)) > 0) OR " +
            "((SELECT COUNT(e) FROM Education e WHERE (e.organization =:organization) AND (e.linkedinPerson = p) AND (e.endDate IS NULL)) > 0))")
    Page<LinkedinPerson> findCurrentlyWorkingByOrganizationAndIndustryAndLocality(Pageable pageable, @Param("organization") Organization organization,
                                                                          @Param("industry") String industry, @Param("locality") String locality);

}
