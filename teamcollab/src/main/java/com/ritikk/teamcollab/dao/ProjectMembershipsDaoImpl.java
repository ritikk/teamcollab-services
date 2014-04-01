package com.ritikk.teamcollab.dao;

import org.apache.ibatis.session.SqlSession;
import com.ritikk.teamcollab.domain.ProjectMembership;
import com.ritikk.teamcollab.mappers.ProjectMembershipsMapper;

public class ProjectMembershipsDaoImpl implements ProjectMembershipsDao {

	@Override
	public boolean isUserPermitted(ProjectMembership membership) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProjectMembershipsMapper mapper = session.getMapper(ProjectMembershipsMapper.class);
			return mapper.isUserPermitted(membership);
		} finally {
			session.close();
		}
	}

}
