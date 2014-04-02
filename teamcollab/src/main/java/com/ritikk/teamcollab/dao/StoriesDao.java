package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Story;

/**
 * This interface defines interactions allowed with the Story resource
 * @author ritik
 * @see Story
 */
public interface StoriesDao {
	/**
	 * This method retrieves a list of all stories for a project
	 * @param projectID int 
	 * @return List<Story>
	 */
	public List<Story> getStoriesByProjectID(int projectID);
}
