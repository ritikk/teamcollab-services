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

import com.ritikk.teamcollab.dao.ProjectMembershipsDaoImpl;
import com.ritikk.teamcollab.dao.ProjectsDao;
import com.ritikk.teamcollab.domain.Project;
import com.ritikk.teamcollab.domain.ProjectMembership;

/**
 * This service allows clients to interact with the Projects resource. It
 * supports retrieving, adding, updating and deleting projects.
 * 
 * Access is limited to Super Admins, Members from the Organization with
 * Organization level access, Members with Project level access.
 * 
 * @author ritik
 * 
 */
@Path("/projects")
public class ProjectsResource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	@Context
	SecurityContext securityContext;

	@Autowired
	private ProjectsDao dao;
	@Autowired
	private ProjectMembershipsDaoImpl membershipsDao;

	public ProjectsResource() {

	}

	/**
	 * This method retrieves a list of all projects accessible to the user.
	 * 
	 * Method GET 
	 * Path webapi/projects
	 * 
	 * Logged in user must be a SUPER ADMIN, Org Level Member or Project Level
	 * Member to view Projects.
	 * 
	 * @return List<Project> This returns a list of all projects accessible to
	 *         the user.
	 * @see Project
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Project> getProjects() {
		String name = securityContext.getUserPrincipal().getName();

		List<Project> projects = dao.getProjectsByUsername(name);
		if (projects == null || projects.size() == 0) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		return projects;
	}

	/**
	 * This method retrieves a single Project by the supplied project ID. 
	 * Logged in user must have Project level rights, Org level rights or 
	 * must be a SUPER ADMIN to call this method.
	 * 
	 * Method GET 
	 * Path webapi/projects/{projectID}
	 * 
	 * @param projectID The project ID is passed as a Path parameter
	 * @return Project returns the project if found
	 * @see Project
	 */
	@GET
	@Path("{projectID}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Project getProjectByID(@PathParam("projectID") int projectID) {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, projectID, name, false);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		return dao.getProjectByID(projectID);
	}

	/**
	 * This method returns the count of all projects. 
	 * Logged in user must have Project level rights, Org level rights or 
	 * must be a SUPER ADMIN to call this method.
	 * 
	 * Method GET
	 * Path webapi/projects/count
	 * 
	 * @return String This returns the count of all projects.
	 */
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		String name = securityContext.getUserPrincipal().getName();

		List<Project> projects = dao.getProjectsByUsername(name);
		if (projects == null || projects.size() == 0) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}
		
		return String.valueOf(projects.size());
	}

	/**
	 * This method inserts a new Project into the database. 
	 * Logged in user must be a SUPER ADMIN, or Org Level User to call this method.
	 * 
	 * Method PUT
	 * Path webapi/projects
	 * 
	 * @param project The project to add as XML
	 * @return Response A 201 HTTP CREATED response is received with 
	 * the path of the created resource.
	 * @throws URISyntaxException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putProject(JAXBElement<Project> project)
			throws URISyntaxException {
		String name = securityContext.getUserPrincipal().getName();
		Project proj = project.getValue();

		ProjectMembership m = new ProjectMembership(proj.getOrganizationID(),
				0, name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		Response res;
		int result = dao.insertProject(proj);
		res = Response.created(new URI(uriInfo.getPath() + result)).build();
		return res;
	}

	/**
	 * This method updates an existing Project. 
	 * Logged in user must be a SUPER ADMIN or have Organization/Project Level 
	 * Write access for the respective organization/project to call this method.
	 * 
	 * Method POST
	 * Path webapi/projects
	 * 
	 * @param project The project to update as XML
	 * @return Response A 200 HTTP OK response is received upon success
	 * @throws URISyntaxException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postProject(JAXBElement<Project> project)
			throws URISyntaxException {

		Project proj = project.getValue();

		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(proj.getOrganizationID(),
				proj.getProjectID(), name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		int result = dao.updateProject(proj);

		return Response.ok(result).build();
	}

	/**
	 * This method deletes a project.
	 * Logged in user must be a SUPER ADMIN or have Organization/Project
	 * Level Write access to call this method.
	 * 
	 * Method DELETE
	 * Path webapi/projects/{projectID}
	 * 
	 * @param projectID int The ID of the project to delete
	 * @return Response A 200 HTTP OK response is received upon succees
	 */
	@DELETE
	@Path("{projectID}")
	public Response deleteOrganization(@PathParam("projectID") int projectID) {
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, projectID, name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}

		dao.deleteProject(projectID);

		return Response.ok().build();
	}
}
