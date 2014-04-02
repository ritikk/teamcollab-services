package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * Concrete implementation of the OrganizationsDao
 * @author ritik
 * @see OrganizationsDao
 */
public class OrganizationsDaoImpl implements OrganizationsDao {

	private TeamCollabMapper mapper;

	/**
	 * This method injects the mapper to use to interact with 
	 * the data store
	 * @param mapper
	 */
	public void setMapper(TeamCollabMapper mapper) {
		this.mapper = mapper;
	}

	public OrganizationsDaoImpl() {
	}

	@Override
	public List<Organization> getAllOrganizations() {
		return mapper.getAllOrganizations();
	}

	@Override
	public Organization getOrganizationByID(int organizationID) {
		return mapper.getOrganizationByID(organizationID);
	}

	@Override
	public int insertOrganization(Organization organization) {
		mapper.insertOrganization(organization);
		return organization.getOrganizationId();
	}

	@Override
	public int updateOrganization(Organization organization) {
		int rows = 0;
		rows = mapper.updateOrganization(organization);
		return rows;
	}

	@Override
	public void deleteOrganization(int organizationID) {
		mapper.deleteOrganization(organizationID);
	}

}