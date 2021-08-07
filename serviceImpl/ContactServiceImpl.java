package com.contact.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.contact.RowMapper.ContactRowMapper;
import com.contact.dao.ContactDao;
import com.contact.model.Contact;
import com.contact.service.ContactService;
import com.contact.util.StringUtil;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Contact contact) {
		contactDao.save(contact);

	}

	public void update(Contact contact) {
		contactDao.update(contact);

	}

	public void delete(int contactId) {
		contactDao.delete(contactId);

	}

	public void deleteAll(Integer[] contactIds) {
		String ids = StringUtil.toCommaSeparatedString(contactIds);
		String query = "delete from contacts where contactId IN (" + ids + ")";
		this.jdbcTemplate.update(query);
	}

	public List<Contact> findUserContact(int userId) {
		List<Contact> findByProperty = contactDao.findByProperty("userId", userId);
		return findByProperty;
	}

	public List<Contact> findUserContact(int userId, String txt) {
		String query = "select contactId,userId,name,phone,email,address,remark from contacts where userId = ? AND (name LIKE '%"
				+ txt + "%' OR address LIKE '%" + txt + "%' OR phone LIKE '%" + txt + "%' OR remark LIKE '%" + txt
				+ "%')";

		return this.jdbcTemplate.query(query, new ContactRowMapper(), userId);

	}

	public Contact findById(int contactId) {
		Contact findById = contactDao.findById(contactId);
		return findById;
	}

}
