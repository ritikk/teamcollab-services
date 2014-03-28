package com.ritikk.teamcollab.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ritikk.teamcollab.domain.Story;
import com.ritikk.teamcollab.mappers.StoriesMapper;

public class StoriesDaoImpl implements StoriesDao {

	@Override
	public List<Story> getStoriesByProjectID(int projectID) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			StoriesMapper mapper = session.getMapper(StoriesMapper.class);
			return mapper.getStoriesByProjectID(projectID);
		} finally {
			session.close();
		}
	}

}
