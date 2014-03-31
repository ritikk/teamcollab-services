package com.ritikk.teamcollab.mappers;

import java.util.List;

import com.ritikk.teamcollab.domain.Member;

public interface MembersMapper {
	public List<Member> getMembersByOrganizationID(int organizationID);
	public Member loadUserByUsername(String username);
}
