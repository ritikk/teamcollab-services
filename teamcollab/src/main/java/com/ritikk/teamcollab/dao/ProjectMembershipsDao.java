package com.ritikk.teamcollab.dao;

import com.ritikk.teamcollab.domain.ProjectMembership;

public interface ProjectMembershipsDao {
	public boolean isUserPermitted(ProjectMembership membership);
}
