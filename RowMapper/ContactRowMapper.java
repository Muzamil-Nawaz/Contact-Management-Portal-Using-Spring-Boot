package com.contact.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.contact.model.Contact;
import com.contact.model.User;

public class ContactRowMapper implements RowMapper<Contact> {

	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setContactId(rs.getInt("contactId"));
		contact.setUserId(rs.getInt("userId"));
		contact.setName(rs.getString("name"));
		contact.setEmail(rs.getString("email"));
		contact.setAddress(rs.getString("address"));
		contact.setPhone(rs.getString("phone"));
		contact.setRemark(rs.getString("remark"));
		return contact;
	}

}
