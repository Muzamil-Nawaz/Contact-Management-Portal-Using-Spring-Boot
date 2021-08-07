package com.contact.dao;

import java.util.List;

import com.contact.model.User;

public interface UserDao {

	public void save(User user);

	public void update(User user);

	public void deleteAll(User user);

	public void delete(int userId);

	public User findById(int userId);

	public List<User> findAll();

	public List<User> findByProperty(String propName, Object value);

}
