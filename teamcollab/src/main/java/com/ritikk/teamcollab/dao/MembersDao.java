package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Member;

public interface MembersDao {
	public List<Member> getMembersByOrganizationID(int organizationID);
}
