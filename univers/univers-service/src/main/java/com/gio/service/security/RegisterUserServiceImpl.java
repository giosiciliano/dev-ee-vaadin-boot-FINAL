package com.gio.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gio.model.entity.User;
import com.gio.repository.security.UserRepository;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired // specified as bean in SecurityConfig class so already in AppContext
	private BCryptPasswordEncoder pwEncoder;
	
	@Override
	public void save(String username, String password) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(pwEncoder.encode(password));
		
		userRepo.save(user);
		
	}

}
