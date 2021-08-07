package com.contact.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.contact.Exceptions.UserBlockedExceptions;
import com.contact.RowMapper.UserRowMapper;
import com.contact.dao.UserDao;
import com.contact.model.User;
import com.contact.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void register(User u) {
		userDao.save(u);
	}

	public User login(String loginName, String password) throws UserBlockedExceptions {
		String query = "select userId,name,phone,email,address,role,loginStatus,loginName from user where loginName='"
				+ loginName + "' AND password='" + password + "'";
		// System.out.println("Login ...");
		/*
		 * Map m = new HashMap(); m.put("ln", loginName); m.put("pw", password);
		 */ try {

			User u = this.jdbcTemplate.queryForObject(query, new UserRowMapper());
			if (u.getLoginStatus() == (UserService.LOGIN_STATUS_BLOCKED)) {
				throw new UserBlockedExceptions("You are Blocked Contact Admin");
			} else {
				return u;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List<User> getUserList() {
		List<User> findByProperty = this.userDao.findByProperty("role", UserService.ROLE_USER);
		return findByProperty;
	}

	public void changeLoginStatus(int userId, int loginStatus) {
		// TODO Auto-generated method stub

	}

}
