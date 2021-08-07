package com.contact.daoImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.contact.RowMapper.UserRowMapper;
import com.contact.dao.UserDao;
import com.contact.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(User user) {
		String query = "insert into user(name,phone,email,address,loginName,password,role,loginStatus) values (?,?,?,?,?,?,?,?)";
		int save = this.jdbcTemplate.update(query, user.getName(), user.getPhone(), user.getEmail(), user.getAddress(),
				user.getLoginName(), user.getPassword(), user.getRole(), user.getLoginStatus());
		System.out.println("UserDao saved query called " + save);
	}

	public void update(User user) {
		String query = "update user set name = ?,phone = ?, email = ?, address = ?, role = ?, loginStatus = ? where userId = ?";
		int update = this.jdbcTemplate.update(query, user.getName(), user.getPhone(), user.getEmail(),
				user.getAddress(), user.getRole(), user.getLoginStatus(), user.getUserId());
		System.out.println("UserDao update query called " + update);
	}

	public void deleteAll(User user) {
		this.delete(user.getUserId());
	}

	public void delete(int userId) {
		String query = "Delete from user where userId = ?";
		this.jdbcTemplate.update(query, userId);

	}

	public User findById(int userId) {
		String query = "select userId,name,phone,email,address,loginName,role,loginStatus from user where userId = ?";
		UserRowMapper mapper = new UserRowMapper();
		User queryForObject = this.jdbcTemplate.queryForObject(query, mapper, userId);
		return queryForObject;
	}

	public List<User> findAll() {
		String query = "select userId,name,phone,email,address,loginName,role,loginStatus from user";
		UserRowMapper mapper = new UserRowMapper();
		List<User> query2 = this.jdbcTemplate.query(query, mapper);
		return query2;
	}

	public List<User> findByProperty(String propName, Object value) {

		String query = "select userId,name,phone,email,address,loginName,role,loginStatus from user where " + propName
				+ " = ?";
		UserRowMapper mapper = new UserRowMapper();
		List<User> query3 = this.jdbcTemplate.query(query, mapper, value);
		return query3;
	}

}
