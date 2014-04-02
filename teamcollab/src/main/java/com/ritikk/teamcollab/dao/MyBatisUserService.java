package com.ritikk.teamcollab.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ritikk.teamcollab.domain.Member;
import com.ritikk.teamcollab.mappers.TeamCollabMapper;

/**
 * This service is used for authentication of the current user
 * against the credentials stored in the data store
 * @author ritik
 * @see UserDetailsService
 */
public class MyBatisUserService implements UserDetailsService {

	private TeamCollabMapper mapper;

	/**
	 * This method injects the mapper to use for interacting with
	 * the data store
	 * @param mapper
	 * @see TeamCollabMapper
	 */
	public void setMapper(TeamCollabMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * This method retrieves user details for a specified username
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		Member member = mapper.loadUserByUsername(username);
		if (member == null)
			throw new UsernameNotFoundException("Username not found");

		@SuppressWarnings("deprecation")
		UserDetails user = new User(
				member.getUsername(),
				member.getPassword(),
				true,
				true,
				true,
				true,
				new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") });

		return user;

	}

}