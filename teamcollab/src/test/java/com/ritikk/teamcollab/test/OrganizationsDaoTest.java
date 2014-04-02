package com.ritikk.teamcollab.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.ritikk.teamcollab.dao.OrganizationsDao;
import com.ritikk.teamcollab.dao.OrganizationsDaoImpl;
import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * This class unit tests the OrganizationsDao
 * Mapper is mocked using Mockito
 * @author ritik
 *
 */
public class OrganizationsDaoTest {

	private OrganizationsDao dao;

	@Mock
	private TeamCollabMapper mapper;

	/**
	 * This method sets up the unit test and the mock object
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		@SuppressWarnings("unchecked")
		List<Organization> mockedList = mock(List.class);
		when(mockedList.get(0)).thenReturn(new Organization(1, "Cybage"));
		when(mockedList.size()).thenReturn(1);
		when(mapper.getAllOrganizations()).thenReturn(mockedList);
		when(mapper.getOrganizationByID(1)).thenReturn(
				new Organization(1, "Cybage"));
		when(mapper.insertOrganization(any(Organization.class))).thenReturn(2);
		when(mapper.updateOrganization(any(Organization.class))).thenReturn(1);
		
		OrganizationsDaoImpl impl = new OrganizationsDaoImpl();
		impl.setMapper(mapper);
		dao = impl;
	}

	@Test
	public void testGetAllOrganizations() {
		List<Organization> orgs = dao.getAllOrganizations();
		assertNotNull(orgs);
		assertEquals(1, orgs.size());
		assertEquals("Cybage", orgs.get(0).getName());
	}

	@Test
	public void testGetOrganizationById1() {
		Organization org = dao.getOrganizationByID(dao.getAllOrganizations().get(0).getOrganizationId());
		assertNotNull(org);
		assertEquals(1, org.getOrganizationId());
		assertEquals("Cybage", org.getName());
	}
	
	@Test
	public void testInsertOrganization() {
		Organization org = new Organization(0, "Evolving Sols");
		dao.insertOrganization(org);
		verify(mapper).insertOrganization(org);
	}
	
	@Test
	public void testUpdateOrganization() {
		Organization org = dao.getOrganizationByID(1);
		org.setName("Cybage Software");
		int result = dao.updateOrganization(org);
		assertNotEquals(0, result);		
	}
	
	@Test
	public void testDeleteOrganization() {
		Organization org = dao.getOrganizationByID(1);
		dao.deleteOrganization(org.getOrganizationId());
		verify(mapper).deleteOrganization(org.getOrganizationId());
	}
}
