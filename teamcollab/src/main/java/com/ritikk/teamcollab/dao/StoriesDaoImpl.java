package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Story;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * Concrete implementation of the StoriesDao interface
 * @author ritik
 * @see StoriesDao
 */
public class StoriesDaoImpl implements StoriesDao {

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
	public List<Story> getStoriesByProjectID(int projectID) {
		return mapper.getStoriesByProjectID(projectID);
	}

}
