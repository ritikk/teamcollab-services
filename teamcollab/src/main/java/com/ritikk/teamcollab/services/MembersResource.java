package com.ritikk.teamcollab.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ritikk.teamcollab.dao.MembersDao;
import com.ritikk.teamcollab.dao.ProjectMembershipsDaoImpl;
import com.ritikk.teamcollab.domain.Member;
import com.ritikk.teamcollab.domain.ProjectMembership;

/**
 * This service allows clients to interact with the Members resource. It
 * supports retrieving, adding, updating and deleting members.
 * 
 * Access is limited to Super Admins and Members from the Organization with
 * Organization level access.
 * @author ritik
 *
 */
@Path("/members")
public class MembersResource {

	@Autowired
	private ProjectMembershipsDaoImpl membershipsDao;
	@Autowired
	private MembersDao dao;

	@Context
	SecurityContext securityContext;

	public MembersResource() {

	}

	/**
	 * This method retrieves a list of all members for an organization
	 * @param organizationID int 
	 * @return List<Member>
	 * @see Member
	 */
	@GET
	@Path("{organizationID}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Member> getOrganizationMembers(
			@PathParam("organizationID") int organizationID) {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(organizationID, 0, name,
				false);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		return dao.getMembersByOrganizationID(organizationID);
	}
}
