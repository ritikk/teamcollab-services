package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Story;

public interface StoriesDao {
	public List<Story> getStoriesByProjectID(int projectID);
}
