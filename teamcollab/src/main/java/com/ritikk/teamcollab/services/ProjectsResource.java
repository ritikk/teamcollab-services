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
	
	public ProjectsResource(){
		
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Project> getProjects() {
		String name = securityContext.getUserPrincipal().getName();
		
		List<Project> projects = dao.getProjectsByUsername(name);
		if(projects == null || projects.size()==0) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}
		
		return projects;
	}

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

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(dao.getAllProjects().size());
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putProject(JAXBElement<Project> project)
			throws URISyntaxException {
		String name = securityContext.getUserPrincipal().getName();
		Project proj = project.getValue();
		
		ProjectMembership m = new ProjectMembership(proj.getOrganizationID(), 0, name, true);
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
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postProject(JAXBElement<Project> project)
			throws URISyntaxException {
		
		Project proj = project.getValue();
		
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(proj.getOrganizationID(), proj.getProjectID(), name, true);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}
		
		int result = dao.updateProject(proj);
		
		return Response.ok(result).build();
	}
	
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
