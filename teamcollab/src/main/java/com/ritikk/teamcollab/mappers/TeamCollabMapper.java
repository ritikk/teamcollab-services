package com.ritikk.teamcollab.mappers;

import java.util.List;

import com.ritikk.teamcollab.domain.Member;
import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.domain.Project;
import com.ritikk.teamcollab.domain.ProjectMembership;
import com.ritikk.teamcollab.domain.Story;

/**
 * This interface defines all database interactions allowed
 * @author ritik
 * 
 */
public interface TeamCollabMapper {
	public List<Organization> getAllOrganizations();
	
	public Organization getOrganizationByID(int organizationID);
	
	public int insertOrganization(Organization organization);

	public int updateOrganization(Organization organization);

	public void deleteOrganization(int organizationID);
	
	/**
	 * This method should check if a user is permitted to access resources of an 
	 * Organization, Project or is a SUPER ADMIN
	 * @param membership ProjectMembership object with details of resource to access
	 * @return boolean True if access is allowed
	 */
	public boolean isUserPermitted(ProjectMembership membership);
	
	public List<Project> getAllProjects();

	public List<Project> getProjectsByUsername(String username);
	
	public Project getProjectByID(int projectID);
	
	public int insertProject(Project project);
	
	public int updateProject(Project project);

	public void deleteProject(int projectID);
	
	public List<Member> getMembersByOrganizationID(int organizationID);
	public Member loadUserByUsername(String username);
	
	public List<Story> getStoriesByProjectID(int projectID);
}