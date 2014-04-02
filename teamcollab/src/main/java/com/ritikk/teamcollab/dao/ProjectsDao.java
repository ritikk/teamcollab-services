package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Project;

/**
 * This interface defines the interactions allowed with the Project resource
 * @author ritik
 * @see Project
 */
public interface ProjectsDao {
	/**
	 * This method retrieves a list of all projects
	 * @return List<Project>
	 */
	public List<Project> getAllProjects();

	/**
	 * This method retrieves a list of projects accessible to a user
	 * @param username String
	 * @return List<Project>
	 */
	public List<Project> getProjectsByUsername(String username);

	/**
	 * This method retrieves a single project
	 * @param projectID int
	 * @return Project
	 */
	public Project getProjectByID(int projectID);

	/**
	 * This method inserts a new Project
	 * @param project Project
	 * @return int ID of newly inserted project
	 */
	public int insertProject(Project project);
	
	/**
	 * This method updates an existing project
	 * @param project Project
	 * @return int No. of rows updated
	 */
	public int updateProject(Project project);

	/**
	 * This method deletes a project
	 * @param projectID
	 */
	public void deleteProject(int projectID);
}
