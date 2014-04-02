package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Member;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * Concrete implementation for the MembersDao
 * @author ritik
 * @see MembersDao
 */
public class MembersDaoImpl implements MembersDao {

	private TeamCollabMapper mapper;

	/**
	 * This method injects the mapper to use for interacting with the DB
	 * @param mapper TeamCollabMapper
	 * @see TeamCollabMapper
	 */
	public void setMapper(TeamCollabMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Member> getMembersByOrganizationID(int organizationID) {
		return mapper.getMembersByOrganizationID(organizationID);
	}

}
