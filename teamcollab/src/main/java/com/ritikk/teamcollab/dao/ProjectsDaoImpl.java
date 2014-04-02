package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Project;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * Concrete implementation of the ProjectsDao interface
 * @author ritik
 * @see ProjectsDao
 */
public class ProjectsDaoImpl implements ProjectsDao {
	private TeamCollabMapper mapper;

	/**
	 * This method injects the mapper to be used to interact with
	 * the data store
	 * @param mapper
	 */
	public void setMapper(TeamCollabMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Project> getAllProjects() {
		return mapper.getAllProjects();
	}

	@Override
	public List<Project> getProjectsByUsername(String username) {
		return mapper.getProjectsByUsername(username);
	}

	@Override
	public Project getProjectByID(int projectID) {
		return mapper.getProjectByID(projectID);
	}

	@Override
	public int insertProject(Project project) {
		mapper.insertProject(project);
		return project.getProjectID();
	}

	@Override
	public int updateProject(Project project) {
		int rows = 0;
		rows = mapper.updateProject(project);
		return rows;
	}

	@Override
	public void deleteProject(int projectID) {
		mapper.deleteProject(projectID);
	}

}
