package com.curewell.invoicemanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curewell.invoicemanagement.entities.User;
import com.curewell.invoicemanagement.entities.repository.UserDAO;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserDAO userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user == null)
		{
			throw new  UsernameNotFoundException("User Not Found In Database..for user:"+username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getRoles());
	}

}
