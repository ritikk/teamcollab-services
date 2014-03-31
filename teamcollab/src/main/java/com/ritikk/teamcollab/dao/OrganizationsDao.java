package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Organization;

public interface OrganizationsDao{
	public List<Organization> getAllOrganizations();
	
	public Organization getOrganizationByID(int organizationID);

	public int insertOrganization(Organization organization);

	public int updateOrganization(Organization organization);

	public void deleteOrganization(int organizationID);
	
}