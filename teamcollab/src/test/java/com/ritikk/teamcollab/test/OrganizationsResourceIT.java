package com.ritikk.teamcollab.test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.junit.Assert;
import org.junit.Test;

import com.ritikk.teamcollab.domain.Organization;

/**
 * This class performs integration tests for Organizations Integration tests are
 * performed against a jetty instance
 * 
 * @author ritik
 * 
 */
public class OrganizationsResourceIT {

	/**
	 * Base path for the web api
	 */
	private static final String WEBAPI_PATH = "http://localhost:8080/webapi";

	/**
	 * This is a helper method to retrieve a client target with 
	 * a particular user's credentials
	 * @param username String
	 * @return WebTarget
	 */
	private WebTarget getNewTargetForUser(String username) {
		HttpBasicAuthFilter filter = new HttpBasicAuthFilter(username,
				"cybage@123");

		Client client = ClientBuilder.newClient();
		client.register(filter);
		return client.target(OrganizationsResourceIT.WEBAPI_PATH);
	}

	@Test
	public void testGetAllOrganizationsWithAccess() {
		GenericType<List<Organization>> orgsType = new GenericType<List<Organization>>() {
		};
		List<Organization> orgs = getNewTargetForUser("alladmin")
				.path("organizations").request().get(orgsType);
		Assert.assertNotNull(orgs);
		Assert.assertNotEquals(0, orgs.size());
	}

	@Test(expected=javax.ws.rs.ForbiddenException.class)
	public void testGetAllOrganizationsWithoutAccess() {
		ClientResponse response = getNewTargetForUser("projectadmin")
				.path("organizations").request().get(ClientResponse.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(403, response.getStatus());
	}
	
	@Test
	public void testGetSingleOrganizationWithAccess() {
		GenericType<Organization> orgType = new GenericType<Organization>(){};
		Organization org = getNewTargetForUser("orgadmin")
				.path("organizations").path("1").request().get(orgType);
		Assert.assertNotNull(org);
		Assert.assertNotEquals(0, org.getOrganizationId());
		Assert.assertNotEquals(0, org.getName().length());
	}
	
	@Test(expected=javax.ws.rs.ForbiddenException.class)
	public void testGetSingleOrganizationWithoutAccess() {
		ClientResponse response = getNewTargetForUser("orgadmin")
				.path("organizations").path("2").request().get(ClientResponse.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(403, response.getStatus());
	}

}
