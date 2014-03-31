package com.ritikk.teamcollab.mappers;

import com.ritikk.teamcollab.domain.ProjectMembership;

public interface ProjectMembershipsMapper {
	public boolean isUserPermitted(ProjectMembership membership);
}
