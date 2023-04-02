package com.tdtu.midterm.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtu.midterm.entity.User;
import com.tdtu.midterm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public void register(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setRole(1);
		userRepository.save(user);
	}

}
