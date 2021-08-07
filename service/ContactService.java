package com.contact.service;

import java.util.List;

import com.contact.model.Contact;

public interface ContactService {
	public void save(Contact contact);

	public void update(Contact contact);

	public void delete(int contactId);

	public void deleteAll(Integer[] contactIds);

	public List<Contact> findUserContact(int userId);

	public List<Contact> findUserContact(int userId, String txt);

	public Contact findById(int contactId);
}
