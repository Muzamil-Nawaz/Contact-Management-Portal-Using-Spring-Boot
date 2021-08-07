package com.contact.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact.model.Contact;
import com.contact.service.ContactService;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;

	@RequestMapping(value = { "/user/contact_form" })
	public String showContact(Model model) {
		Contact contact = new Contact();
		model.addAttribute("command", contact);
		return "contact_form";
	}

	@RequestMapping(value = { "/user/save_contact" })
	public String SaveAndUpdateContact(@ModelAttribute("command") Contact contact, Model model,
			HttpSession httpSession) {
		Integer contactId = (Integer) httpSession.getAttribute("aContactId");
		if (contactId == null) {
			try {
				int userId = (Integer) httpSession.getAttribute("userId");
				System.out.println("UserId " + userId);
				contact.setUserId(userId);
				contactService.save(contact);
				return "redirect:cList?act=sv";
			} catch (Exception ex) {
				ex.printStackTrace();
				model.addAttribute("err", "Failed To Save Contact");
				return "contact_form";
			}
		} else {
			try {
				contact.setContactId(contactId);
				contactService.update(contact);
				return "redirect:cList?act=ed";
			} catch (Exception ex) {
				ex.printStackTrace();
				model.addAttribute("err", "Failed To Update Contact");
				return "contact_form";
			}
		}
	}

	@RequestMapping(value = "/user/cList")
	public String contactList(Model model, HttpSession httpSession) {
		int userId = (Integer) httpSession.getAttribute("userId");
		model.addAttribute("contactList", contactService.findUserContact(userId));
		return "cList";
	}

	@RequestMapping(value = "/user/del_contact")
	public String deleteContact(@RequestParam("cid") Integer contactId) {
		contactService.delete(contactId);
		return "redirect:cList?act=del";
	}

	@RequestMapping(value = "/user/bulk_delete")
	public String bulkdeleteContact(@RequestParam("cid") Integer[] contactIds) {
		contactService.deleteAll(contactIds);
		return "redirect:cList?act=delall";
	}

	@RequestMapping(value = { "/user/edit_contact" })
	public String editContact(Model model, HttpSession httpSession, @RequestParam("cid") Integer contactId) {
		httpSession.setAttribute("aContactId", contactId);
		Contact contact = contactService.findById(contactId);
		model.addAttribute("command", contact);
		return "contact_form";
	}

	@RequestMapping(value = { "/user/contact_search" })
	public String Search(Model model, HttpSession session, @RequestParam("freeText") String freeText) {
		int userId = (Integer) session.getAttribute("userId");
		model.addAttribute("contactList", contactService.findUserContact(userId, freeText));
		return "cList";
	}
}
