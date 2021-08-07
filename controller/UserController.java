package com.contact.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contact.command.LoginCommand;
import com.contact.command.UserCommand;
import com.contact.model.User;
import com.contact.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" })
	public String LoginHome(Model model) {
		model.addAttribute("command", new LoginCommand());
		return "index";
	}

	@RequestMapping(value = { "/admin/users" })
	public String getUserList(Model model) {
		model.addAttribute("users", userService.getUserList());
		return "users";
	}

	@RequestMapping(value = { "/register" })
	public String Registration(Model model) {
		model.addAttribute("command", new UserCommand());
		return "reg_form";
	}

	@RequestMapping(value = { "/user/dashboard" })
	public String userDashboard() {
		return "dashboard_user";
	}

	@RequestMapping(value = { "/admin/dashboard" })
	public String adminDashboard() {
		return "dashboard_admin";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String handleLogin(@ModelAttribute("command") LoginCommand command, Model model, HttpSession httpSession) {
		try {
			User userLogin = userService.login(command.getLoginName(), command.getPassword());
			if (userLogin == null) {
				model.addAttribute("err", "Login Failed, Enter valid credetials");
				return "index";
			} else {
				if (userLogin.getRole() == UserService.ROLE_ADMIN) {
					addUserInSession(userLogin, httpSession);
					httpSession.setAttribute("admindash", userLogin);
					System.out.println(userLogin);
					return "redirect:admin/dashboard";
				} else if (userLogin.getRole() == UserService.ROLE_USER) {
					addUserInSession(userLogin, httpSession);
					httpSession.setAttribute("userdash", userLogin);
					System.out.println(userLogin);
					return "redirect:user/dashboard";
				} else {
					model.addAttribute("err", "Invalid User Role");
					return "index";
				}

			}

		} catch (Exception ex) {
			model.addAttribute("err", ex.getMessage());
			return "index";
		}

	}

	@RequestMapping(value = { "/logout" })
	public String logout(HttpSession httpSession) {
		System.out.println("Logout Works " + httpSession.getAttribute("userdash"));

		httpSession.invalidate();
		return "redirect:login?act=lo";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String Registration(@ModelAttribute("command") UserCommand command, Model model) {
		try {
			User user = command.getUser();
			System.out.println(user);
			user.setRole(UserService.ROLE_USER);
			user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
			userService.register(user);
			return "redirect:login?act=reg";
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("err", "This Login Name is already registered try with another..");
			return "reg_form";
		}

	}

	private void addUserInSession(User u, HttpSession httpSession) {
		httpSession.setAttribute("user", u);
		httpSession.setAttribute("userId", u.getUserId());
		httpSession.setAttribute("role", u.getRole());
	}
}
