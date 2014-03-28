package com.ritikk.teamcollab.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.ritikk.teamcollab.domain.Project;
import com.ritikk.teamcollab.mappers.ProjectsMapper;

public class ProjectsDaoImpl implements ProjectsDao {
	
	@Override
	public List<Project> getAllProjects() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ProjectsMapper mapper = session.getMapper(ProjectsMapper.class);
			return mapper.getAllProjects();
		} finally {
			session.close();
		}
	}

}
