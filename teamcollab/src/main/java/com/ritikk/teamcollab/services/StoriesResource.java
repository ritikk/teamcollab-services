package com.ritikk.teamcollab.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ritikk.teamcollab.dao.StoriesDao;
import com.ritikk.teamcollab.dao.StoriesDaoImpl;
import com.ritikk.teamcollab.domain.Story;

@Path("/stories")
public class StoriesResource {

	private StoriesDao dao;
	
	public StoriesResource(){
		this.dao = new StoriesDaoImpl();
	}
	
	@GET
	@Path("{projectID}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Story> getStoriesByProject(@PathParam("projectID")int projectID){
		return dao.getStoriesByProjectID(projectID);
	}
}
