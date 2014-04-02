package com.ritikk.teamcollab.dao;

import com.ritikk.teamcollab.domain.ProjectMembership;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * This class allows data access to Project Memberships
 * @author ritik
 *
 */
public class ProjectMembershipsDaoImpl implements ProjectMembershipsDao {

	private TeamCollabMapper mapper;
	
	/**
	 * This method injects the mapper to be used for data access
	 * @param mapper ProjectMembershipsMapper instance to use
	 * @see ProjectMembershipsMapper
	 */
	public void setMapper(TeamCollabMapper mapper){
		this.mapper = mapper;
	}
	
	/**
	 * This method checks if a user has permission to access
	 * a particular organization, project or is a SUPER ADMIN
	 */
	@Override
	public boolean isUserPermitted(ProjectMembership membership) {
		return mapper.isUserPermitted(membership);
	}

}
