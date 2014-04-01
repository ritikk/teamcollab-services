package com.ritikk.teamcollab.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import com.ritikk.teamcollab.dao.OrganizationsDao;
import com.ritikk.teamcollab.dao.ProjectMembershipsDaoImpl;
import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.domain.ProjectMembership;

@Path("/organizations")
public class OrganizationsResource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	@Context
	SecurityContext securityContext;

	@Autowired
	private ProjectMembershipsDaoImpl membershipsDao;
	@Autowired
	private OrganizationsDao dao;

	public OrganizationsResource() {
		
	}

	// GET
	// webapi/organizations

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Organization> getOrganizations() {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, 0, name, false);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		return dao.getAllOrganizations();
	}

	// GET
	// webapi/organizations/count

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, 0, name, false);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}
		return String.valueOf(dao.getAllOrganizations().size());
	}

	// GET
	// webapi/organizations/1

	@GET
	@Path("{organizationID}")
	@Produces(MediaType.APPLICATION_XML)
	public Organization getOrganization(
			@PathParam("organizationID") int organizationID) {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, 0, name, false);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		Organization org = dao.getOrganizationByID(organizationID);

		if (org == null) {
			Response notFound = Response.status(Response.Status.NOT_FOUND)
					.entity("Organization not found").build();
			throw new WebApplicationException(notFound);
		}

		return dao.getOrganizationByID(organizationID);
	}

	// PUT
	// webapi/organizations

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putOrganization(JAXBElement<Organization> organization)
			throws URISyntaxException {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, 0, name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		Organization org = organization.getValue();
		Response res;
		int result = dao.insertOrganization(org);
		res = Response.created(new URI(uriInfo.getPath() + result)).build();
		return res;
	}

	// POST
	// webapi/organizations/1

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postOrganization(JAXBElement<Organization> organization)
			throws URISyntaxException {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, 0, name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		Organization org = organization.getValue();
		
		int result = dao.updateOrganization(org);
		
		return Response.ok(result).build();
	}
	
	// DELETE
	// webapi/organizations/
	
	@DELETE
	@Path("{organizationID}")
	public Response deleteOrganization(@PathParam("organizationID") int organizationID) {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, 0, name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}
		
		dao.deleteOrganization(organizationID);
		
		return Response.ok().build();
	}
}
