package com.ritikk.teamcollab.dao;

import com.ritikk.teamcollab.domain.ProjectMembership;

/**
 * This interface defined methods that allow access to Project Memberships
 * @author ritik
 *
 */
public interface ProjectMembershipsDao {
	/**
	 * This method should check if a user is permitted to access resources of an 
	 * Organization, Project or is a SUPER ADMIN
	 * @param membership ProjectMembership object with details of resource to access
	 * @return boolean True if access is allowed
	 */
	public boolean isUserPermitted(ProjectMembership membership);
}
