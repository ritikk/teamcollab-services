package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Organization;

/**
 * This interface defines interactions allowed with the Organization resource
 * @author ritik
 * @see Organization
 */
public interface OrganizationsDao{
	/**
	 * This method retrieves a list of all organizations
	 * @return List<Organization>
	 */
	public List<Organization> getAllOrganizations();
	
	/**
	 * This method retrieves a single organization
	 * @param organizationID int
	 * @return Organization
	 */
	public Organization getOrganizationByID(int organizationID);

	/**
	 * This method inserts a new organization
	 * @param organization Organization
	 * @return int ID of the newly inserted organization
	 */
	public int insertOrganization(Organization organization);

	/**
	 * This method updates and existing organization
	 * @param organization Organization
	 * @return int No. of rows updated
	 */
	public int updateOrganization(Organization organization);

	/**
	 * This method deletes an organization
	 * @param organizationID int 
	 */
	public void deleteOrganization(int organizationID);
	
}