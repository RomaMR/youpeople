package com.optigra.youpeople.persistence.repository.industry;

import com.optigra.youpeople.domain.industry.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by romanmudryi on 01.09.15.
 */
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    Industry findByName(String name);

}
