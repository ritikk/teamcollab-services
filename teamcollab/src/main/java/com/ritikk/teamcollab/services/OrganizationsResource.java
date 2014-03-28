package com.ritikk.teamcollab.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.ritikk.teamcollab.dao.OrganizationsDao;
import com.ritikk.teamcollab.dao.OrganizationsDaoImpl;
import com.ritikk.teamcollab.domain.Organization;

@Path("/organizations")
public class OrganizationsResource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;
	
	private OrganizationsDao dao;
	
	public OrganizationsResource(){
		this.dao = new OrganizationsDaoImpl();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Organization> getOrganizations() {
		return dao.getAllOrganizations();
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(dao.getAllOrganizations().size());
	}
}
