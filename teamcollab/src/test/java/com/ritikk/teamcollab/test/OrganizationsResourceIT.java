package com.ritikk.teamcollab.test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ritikk.teamcollab.domain.Organization;

public class OrganizationsResourceIT {
	WebTarget target;

	@Before
	public void init() throws Exception {
		HttpBasicAuthFilter filter = new HttpBasicAuthFilter("alladmin",
				"cybage@123");

		Client client = ClientBuilder.newClient();
		client.register(filter);
		target = client.target("http://localhost:8080/webapi");

	}

	@Test
	public void test() {
		GenericType<List<Organization>> orgsType = new GenericType<List<Organization>>() {
		};
		List<Organization> orgs = target.path("organizations").request()
				.get(orgsType);
		Assert.assertNotNull(orgs);
		Assert.assertNotEquals(0, orgs.size());
	}

}
