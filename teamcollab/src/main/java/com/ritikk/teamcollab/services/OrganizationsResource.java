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

/**
 * This service allows clients to interact with the Organizations resource. It
 * supports retrieving, adding, updating and deleting organizations.
 * 
 * Access is limited to Super Admins and Members from the Organization with
 * Organization level access.
 * 
 * @author ritik
 * 
 */
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

	/**
	 * This method retrieves a list of all organizations. 
	 * Method GET 
	 * Path webapi/organizations
	 * 
	 * Logged in user must be a SUPER ADMIN to see all organizations.
	 * 
	 * @return List<Organization> This returns a list of all organizations.
	 * @see Organization
	 */
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

	/**
	 * This method returns the count of all organizations. 
	 * Logged in user must be a SUPER ADMIN to call this method.
	 * 
	 * Method GET
	 * Path webapi/organizations/count
	 * 
	 * @return String This returns the count of all organizations.
	 */
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

	/**
	 * This method retrieves a single Organization by the supplied organization ID. 
	 * Logged in user must have Organization level rights or must be a SUPER ADMIN
	 * to call this method.
	 * 
	 * Method GET
	 * Path webapi/organizations/{organizationID}
	 * 
	 * @param organizationID The organization ID is passed as a Path parameter 
	 * @return Organization returns the organization if found
	 * @see Organization
	 */
	@GET
	@Path("{organizationID}")
	@Produces(MediaType.APPLICATION_XML)
	public Organization getOrganization(
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

		Organization org = dao.getOrganizationByID(organizationID);

		if (org == null) {
			Response notFound = Response.status(Response.Status.NOT_FOUND)
					.entity("Organization not found").build();
			throw new WebApplicationException(notFound);
		}

		return dao.getOrganizationByID(organizationID);
	}


	/**
	 * This method inserts a new Organization into the database. 
	 * Logged in user must be a SUPER ADMIN to call this method.
	 * 
	 * Method PUT
	 * Path webapi/organizations
	 * 
	 * @param organization The organization to add as XML
	 * @return Response A 201 HTTP CREATED response is received with 
	 * the path of the created resource.
	 * @throws URISyntaxException
	 */
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
	
	/**
	 * This method updates an existing Organization. 
	 * Logged in user must be a SUPER ADMIN or have Organization Level Write access
	 * for the respective organization to call this method.
	 * 
	 * Method POST
	 * Path webapi/organizations
	 * 
	 * @param organization The organization to update as XML
	 * @return Response A 200 HTTP OK response is received upon success
	 * @throws URISyntaxException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postOrganization(JAXBElement<Organization> organization)
			throws URISyntaxException {

		Organization org = organization.getValue();

		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(org.getOrganizationId(), 0,
				name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		int result = dao.updateOrganization(org);

		return Response.ok(result).build();
	}

	/**
	 * This method deletes an organization.
	 * Logged in user must be a SUPER ADMIN or have Organization Level Write access
	 * for the respective organization to call this method.
	 * 
	 * Method DELETE
	 * Path webapi/organizations/{organizationID}
	 * 
	 * @param organizationID int The ID of the organization to delete
	 * @return Response A 200 HTTP OK response is received upon succees
	 */
	@DELETE
	@Path("{organizationID}")
	public Response deleteOrganization(
			@PathParam("organizationID") int organizationID) {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(organizationID, 0, name,
				true);
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
