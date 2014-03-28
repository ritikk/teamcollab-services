package com.ritikk.teamcollab.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ritikk.teamcollab.dao.MembersDao;
import com.ritikk.teamcollab.dao.MembersDaoImpl;
import com.ritikk.teamcollab.domain.Member;

@Path("/members")
public class MembersResource {
	
	private MembersDao dao;
	
	public MembersResource(){
		this.dao = new MembersDaoImpl();
	}

	@GET
	@Path("{organizationID}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Member> getOrganizationMembers(@PathParam("organizationID") int organizationID){
		return dao.getMembersByOrganizationID(organizationID);
	}
}
