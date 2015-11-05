package com.optigra.youpeople.services.organization;

import com.mysema.query.jpa.impl.JPAQuery;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.organization.QOrganization;
import com.optigra.youpeople.domain.searchlog.QSearchLog;
import com.optigra.youpeople.domain.user.User;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.persistence.repository.organization.OrganizationRepository;
import com.optigra.youpeople.persistence.repository.search.SearchLogRepository;
import com.optigra.youpeople.services.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
@Service("defaultOrganizationService")
public class DefaultOrganizationService implements OrganizationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultOrganizationService.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    @Resource(name = "organizationRepository")
    private OrganizationRepository organizationRepository;
    
    @Resource(name = "searchLogRepository")
    private SearchLogRepository searchLogRepository;

    @Resource(name = "defaultSecurityService")
    private SecurityService securityService;

    @Override
    public Organization save(Organization organization) {
        LOGGER.info("DefaultOrganizationService.save()");
        Organization savedOrganization = organizationRepository.save(organization);
        LOGGER.info("DefaultOrganizationService.save() finished");
        return savedOrganization;
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("DefaultOrganizationService.delete(), id={}", id);
        organizationRepository.delete(id);
        LOGGER.info("DefaultOrganizationService.delete() finished");
    }

    @Override
    public Organization findOne(Long id) {
        LOGGER.info("DefaultOrganizationService.findOne(), id={}", id);
        Organization organization = organizationRepository.findOne(id);
        if (organization == null) {
            LOGGER.error("Organization not found, id={}", id);
            throw new ContentNotFoundException("Organization not found");
        }
        LOGGER.info("DefaultOrganizationService.findOne() finished");
        return organization;
    }

	@Override
	public Organization findByName(String organizationName) {
        LOGGER.info("DefaultOrganizationService.findByName(), organizationName={}", organizationName);
		Iterator<Organization> organizationIterator = organizationRepository
				.findAll(QOrganization.organization.name.equalsIgnoreCase(organizationName))
				.iterator();
        if (!organizationIterator.hasNext()) {
            LOGGER.error("Organization not found, organizationName={}", organizationName);
            throw new ContentNotFoundException("Organization not found");
        }
        LOGGER.info("DefaultOrganizationService.findByName() finished");
        return organizationIterator.next();
	}

    @Override
    public List<String> findPersonsLocalities(Organization organization) {
        LOGGER.info("DefaultOrganizationService.findPersonsLocalities(), organization={}", organization);
        List<String> localities = organizationRepository.findPersonsLocalities(organization);
        LOGGER.info("DefaultOrganizationService.findPersonsLocalities() finished");
        return localities;
    }

    @Override
    public List<Industry> findPersonsIndustries(Organization organization) {
        LOGGER.info("DefaultOrganizationService.findPersonsIndustries(), organization={}", organization);
        List<Industry> industries = organizationRepository.findPersonsIndustries(organization);
        LOGGER.info("DefaultOrganizationService.findPersonsIndustries() finished");
        return industries;
    }

	@Override
	public List<Organization> findAll() {
		LOGGER.info("DefaultOrganizationService.findAll()");
        List<Organization> organizations = organizationRepository.findAll();
        LOGGER.info("DefaultOrganizationService.findAll() finished");
        return organizations;
	}

	@Override
	public List<Organization> findAllSearched() {
		LOGGER.info("DefaultOrganizationService.findAllSearched()");
		QOrganization organization = QOrganization.organization;
		QSearchLog searchLog = QSearchLog.searchLog;
		JPAQuery query = new JPAQuery(entityManager).from(organization, searchLog);
		query.where(organization.name.contains(searchLog.query));
		List<Organization> result = query.distinct().list(organization);
        LOGGER.info("DefaultOrganizationService.findAllSearched() finished");
        return result;
	}

    @Override
    public Organization registration(final Organization organization) {
        LOGGER.info("DefaultOrganizationService.registration()");
        Organization registeredOrganization = organizationRepository.findByName(organization.getName());
        if (registeredOrganization == null) {
            registeredOrganization = organization;
        }
        registeredOrganization = organizationRepository.save(registeredOrganization);
        LOGGER.info("DefaultOrganizationService.registration() finished");
        return registeredOrganization;
    }

    @Transactional
    @Override
    public List<Organization> getUserOrganizations() {
        LOGGER.info("DefaultOrganizationService.getUserOrganizations()");
        User user = securityService.getCurrentUser();
        List<Organization> organizations = new ArrayList<>();
        user.getUserOrganizations().stream().forEach(e -> organizations.add(e.getOrganization()));
        LOGGER.info("DefaultOrganizationService.getUserOrganizations() finished");
        return organizations;
    }
}
