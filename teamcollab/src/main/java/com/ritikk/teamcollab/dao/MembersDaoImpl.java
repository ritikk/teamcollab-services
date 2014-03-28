package com.ritikk.teamcollab.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ritikk.teamcollab.domain.Member;
import com.ritikk.teamcollab.mappers.MembersMapper;

public class MembersDaoImpl implements MembersDao {

	@Override
	public List<Member> getMembersByOrganizationID(int organizationID) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MembersMapper mapper = session.getMapper(MembersMapper.class);
			return mapper.getMembersByOrganizationID(organizationID);
		} finally {
			session.close();
		}
	}

}
