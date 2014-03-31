package com.ritikk.teamcollab.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.mappers.OrganizationsMapper;

public class OrganizationsDaoImpl implements OrganizationsDao {

	public OrganizationsDaoImpl() {
	}

	@Override
	public List<Organization> getAllOrganizations() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			OrganizationsMapper mapper = session
					.getMapper(OrganizationsMapper.class);
			return mapper.getAllOrganizations();
		} finally {
			session.close();
		}
	}

	@Override
	public Organization getOrganizationByID(int organizationID) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			OrganizationsMapper mapper = session
					.getMapper(OrganizationsMapper.class);
			return mapper.getOrganizationByID(organizationID);
		} finally {
			session.close();
		}
	}

	@Override
	public int insertOrganization(Organization organization) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			OrganizationsMapper mapper = session
					.getMapper(OrganizationsMapper.class);
			mapper.insertOrganization(organization);
			session.commit();
		} finally {
			session.close();
		}
		return organization.getOrganizationId();
	}

	@Override
	public int updateOrganization(Organization organization) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		int rows = 0;
		try {
			OrganizationsMapper mapper = session
					.getMapper(OrganizationsMapper.class);
			rows = mapper.updateOrganization(organization);
			session.commit();
		} finally {
			session.close();
		}
		return rows;
	}

	@Override
	public void deleteOrganization(int organizationID) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			OrganizationsMapper mapper = session
					.getMapper(OrganizationsMapper.class);
			mapper.deleteOrganization(organizationID);
			session.commit();
		} finally {
			session.close();
		}
	}

}