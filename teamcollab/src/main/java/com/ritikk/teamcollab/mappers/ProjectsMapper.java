package com.ritikk.teamcollab.mappers;

import java.util.List;

import com.ritikk.teamcollab.domain.Project;

public interface ProjectsMapper {
	public List<Project> getAllProjects();

	public List<Project> getProjectsByUsername(String username);
	
	public Project getProjectByID(int projectID);
	
	public int insertProject(Project project);
	
	public int updateProject(Project project);

	public void deleteProject(int projectID);
}
