package com.ritikk.teamcollab.dao;

import java.util.List;

import com.ritikk.teamcollab.domain.Member;

/**
 * This interface defines interactions allowed with the Members resource
 * @author ritik
 *
 */
public interface MembersDao {
	/**
	 * This method retrieves a list of members for an organization
	 * @param organizationID int
	 * @return List<Member>
	 * @see Member
	 */
	public List<Member> getMembersByOrganizationID(int organizationID);
}
