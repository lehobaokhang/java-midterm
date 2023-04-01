package com.tdtu.midterm.service;

import com.tdtu.midterm.entity.User;

public interface UserService {
	public User findUserByUsername(String username);
	
	public void register(User user);
}
