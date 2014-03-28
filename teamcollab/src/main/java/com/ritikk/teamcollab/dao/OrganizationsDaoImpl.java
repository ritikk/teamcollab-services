package com.ritikk.teamcollab.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ritikk.teamcollab.domain.Organization;
import com.ritikk.teamcollab.mappers.OrganizationsMapper;

public class OrganizationsDaoImpl implements OrganizationsDao {

	public OrganizationsDaoImpl(){}
	
	@Override
	public List<Organization> getAllOrganizations() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			OrganizationsMapper mapper = session.getMapper(OrganizationsMapper.class);
			return mapper.getAllOrganizations();
		} finally {
			session.close();
		}
	}
	
}