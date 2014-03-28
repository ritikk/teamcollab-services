package com.ritikk.teamcollab.mappers;

import java.util.List;

import com.ritikk.teamcollab.domain.Story;

public interface StoriesMapper {
	public List<Story> getStoriesByProjectID(int projectID);
}
