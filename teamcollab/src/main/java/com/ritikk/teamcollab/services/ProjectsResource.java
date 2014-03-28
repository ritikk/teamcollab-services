package com.ritikk.teamcollab.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.ritikk.teamcollab.dao.ProjectsDao;
import com.ritikk.teamcollab.dao.ProjectsDaoImpl;
import com.ritikk.teamcollab.domain.Project;

@Path("/projects")
public class ProjectsResource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;
	
	private ProjectsDao dao;
	
	public ProjectsResource(){
		this.dao = new ProjectsDaoImpl();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Project> getProjects() {
		return dao.getAllProjects();
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(dao.getAllProjects().size());
	}
}
