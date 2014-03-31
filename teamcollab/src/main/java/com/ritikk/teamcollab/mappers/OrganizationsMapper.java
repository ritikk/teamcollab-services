package com.ritikk.teamcollab.mappers;

import java.util.List;

import com.ritikk.teamcollab.domain.Organization;


public interface OrganizationsMapper {
	public List<Organization> getAllOrganizations();
	
	public Organization getOrganizationByID(int organizationID);
	
	public int insertOrganization(Organization organization);

	public int updateOrganization(Organization organization);

	public void deleteOrganization(int organizationID);
}