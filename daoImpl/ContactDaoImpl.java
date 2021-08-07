package com.contact.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.contact.RowMapper.ContactRowMapper;
import com.contact.RowMapper.UserRowMapper;
import com.contact.dao.ContactDao;
import com.contact.model.Contact;
import com.contact.model.User;

@Repository
public class ContactDaoImpl implements ContactDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Contact value) {
		String query = "insert into contacts(userId,name,phone,email,address,remark) values (?,?,?,?,?,?)";
		int save = this.jdbcTemplate.update(query, value.getUserId(), value.getName(), value.getPhone(),
				value.getEmail(), value.getAddress(), value.getRemark());
		System.out.println("ContactDao saved query called " + save);

	}

	public void update(Contact value) {
		String query = "update contacts set name = ?,phone = ?, email = ?, address = ?, remark = ? where contactId = ?";
		int update = this.jdbcTemplate.update(query, value.getName(), value.getPhone(), value.getEmail(),
				value.getAddress(), value.getRemark(), value.getContactId());
		System.out.println("ContactDao update query called " + update);
	}

	public void deleteAll(Contact value) {
		this.delete(value.getContactId());

	}

	public void delete(int contactId) {
		String query = "Delete from contacts where contactId = ?";
		this.jdbcTemplate.update(query, contactId);
	}

	public Contact findById(int contactId) {
		String query = "select contactId,userId,name,phone,email,address,remark from contacts where contactId = ?";
		ContactRowMapper mapper = new ContactRowMapper();
		Contact queryForObject = this.jdbcTemplate.queryForObject(query, mapper, contactId);
		return queryForObject;

	}

	public List<Contact> findAll() {
		String query = "select contactId,userId,name,phone,email,address,remark from contacts";
		ContactRowMapper contactRowMapper = new ContactRowMapper();
		List<Contact> query2 = this.jdbcTemplate.query(query, contactRowMapper);
		return query2;
	}

	public List<Contact> findByProperty(String propName, Object value) {
		String query = "select contactId,userId,name,phone,email,address,remark from contacts where " + propName
				+ " = ?";
		ContactRowMapper contactRowMapper = new ContactRowMapper();
		List<Contact> query3 = this.jdbcTemplate.query(query, contactRowMapper, value);
		return query3;
	}

}
