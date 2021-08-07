package com.contact.service;

import java.util.List;

import com.contact.Exceptions.UserBlockedExceptions;
import com.contact.model.User;

public interface UserService {
	public static final Integer LOGIN_STATUS_ACTIVE = 1;
	public static final Integer LOGIN_STATUS_BLOCKED = 2;
	public static final Integer ROLE_ADMIN = 1;
	public static final Integer ROLE_USER = 2;

	public void register(User u);

	public User login(String loginName, String password) throws UserBlockedExceptions;

	public List<User> getUserList();

	public void changeLoginStatus(int userId, int loginStatus);
}
