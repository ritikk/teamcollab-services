package com.ritikk.teamcollab.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ritikk.teamcollab.domain.Member;
import com.ritikk.teamcollab.mappers.MembersMapper;

public class MyBatisUserService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MembersMapper mapper = session.getMapper(MembersMapper.class);
			Member member = mapper.loadUserByUsername(username);
			if(member == null)
				throw new UsernameNotFoundException("Username not found");
			
			@SuppressWarnings("deprecation")
			UserDetails user = new User(member.getUsername(), member.getPassword(), true, 
					true, true, true, new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_USER")});
			
			return user;
		} finally {
			session.close();
		}
	}

}