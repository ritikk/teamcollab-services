package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.mappers.OrganizationsMapper;

public class OrganizationsDaoImpl implements OrganizationsDao {

	private OrganizationsMapper mapper;

	public void setMapper(OrganizationsMapper mapper) {
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