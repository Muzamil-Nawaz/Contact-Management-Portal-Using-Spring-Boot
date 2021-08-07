package com.contact.dao;

import java.util.List;

import com.contact.model.Contact;

public interface ContactDao {
	public void save(Contact value);

	public void update(Contact value);

	public void deleteAll(Contact value);

	public void delete(int contactId);

	public Contact findById(int contactId);

	public List<Contact> findAll();

	public List<Contact> findByProperty(String propName, Object value);

}
