package com.optigra.youpeople.persistence.repository.organization;

import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by romanmudryi on 05.08.15.
 */
public interface OrganizationRepository extends JpaRepository<Organization,Long>, QueryDslPredicateExecutor<Organization> {

    Organization findByName(String name);
    
    @Query("FROM Organization org WHERE UPPER(org.name) LIKE UPPER(:nameParam)")
    List<Organization> findByNameCaseInsensitive(@Param("nameParam") String name);
    
    @Query("FROM Organization org WHERE UPPER(org.name) LIKE CONCAT('%', UPPER(:namePart), '%')")
    List<Organization> findByNamePart(@Param("namePart") String namePart);

    @Query("SELECT DISTINCT p.locality FROM LinkedinPerson p WHERE " +
            "((SELECT COUNT(j) FROM Job j WHERE (j.organization =:organization) AND (j.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(v) FROM Volunteering v WHERE (v.organization =:organization) AND (v.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(e) FROM Education e WHERE (e.organization =:organization) AND (e.linkedinPerson = p)) > 0)")
    List<String> findPersonsLocalities(@Param("organization") Organization organization);

    @Query("SELECT DISTINCT p.industry FROM LinkedinPerson p WHERE " +
            "((SELECT COUNT(j) FROM Job j WHERE (j.organization =:organization) AND (j.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(v) FROM Volunteering v WHERE (v.organization =:organization) AND (v.linkedinPerson = p)) > 0) OR " +
            "((SELECT COUNT(e) FROM Education e WHERE (e.organization =:organization) AND (e.linkedinPerson = p)) > 0)")
    List<Industry> findPersonsIndustries(@Param("organization") Organization organization);

}
