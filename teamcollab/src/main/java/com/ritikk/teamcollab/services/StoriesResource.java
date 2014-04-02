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

import com.ritikk.teamcollab.dao.ProjectMembershipsDaoImpl;
import com.ritikk.teamcollab.dao.StoriesDao;
import com.ritikk.teamcollab.domain.ProjectMembership;
import com.ritikk.teamcollab.domain.Story;

/**
 * This service allows interaction with the Story resource.
 *  
 * @author ritik
 *
 */
@Path("/stories")
public class StoriesResource {

	@Autowired
	private StoriesDao dao;
	@Autowired
	private ProjectMembershipsDaoImpl membershipsDao;
	
	@Context
	SecurityContext securityContext;
	
	public StoriesResource(){
		
	}
	
	/**
	 * This method retrieves a list of all stories for a particular project
	 * @param projectID int
	 * @return List<Story>
	 * @see Story
	 */
	@GET
	@Path("{projectID}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Story> getStoriesByProject(@PathParam("projectID")int projectID){
		String name = securityContext.getUserPrincipal().getName();
		ProjectMembership m = new ProjectMembership(0, projectID, name, false);
		if (!membershipsDao.isUserPermitted(m)) {
			// Forbidden
			Response denied = Response.status(Response.Status.FORBIDDEN)
					.entity("Permission Denied").build();
			throw new WebApplicationException(denied);
		}
		
		return dao.getStoriesByProjectID(projectID);
	}
}
