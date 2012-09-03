package com.anders.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anders.crm.service.UserService;
import com.anders.crm.vo.GetPasswordVO;

/**
 * 
 * @author Anders Zhu
 * 
 */
@Controller
public class SecurityController extends BaseController {

	@Autowired
	private UserService userService;

	private final static String LOGIN_FAILED = "1";

	@RequestMapping(value = "/login.do", method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request) {
		String error = request.getParameter("error");
		ModelAndView modelAndView = new ModelAndView("login");
		if (LOGIN_FAILED.equals(error)) {
			modelAndView.addObject("errorMsg", getRbms().getMessage("login.error_msg", null, request.getLocale()));
		}
		return modelAndView;
	}

	@RequestMapping(value = "/logout.do", method = { RequestMethod.GET })
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("logout");
		List<String> list = new ArrayList<String>();
		list.add("zhuzhen");
		list.add("guolili");
		modelAndView.addObject("list", list);
		modelAndView.addObject("name", "My First Spring Mvc");
		return modelAndView;
	}

	@RequestMapping(value = "/register.do", method = { RequestMethod.GET })
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("register");
		List<String> list = new ArrayList<String>();
		list.add("zhuzhen");
		list.add("guolili");
		modelAndView.addObject("list", list);
		modelAndView.addObject("name", "My First Spring Mvc");
		return modelAndView;
	}

	@RequestMapping(value = "/get_password.do", method = { RequestMethod.GET })
	public ModelAndView getPassword() {
		ModelAndView modelAndView = new ModelAndView("get_password");
		List<String> list = new ArrayList<String>();
		list.add("zhuzhen");
		list.add("guolili");
		modelAndView.addObject("list", list);
		modelAndView.addObject("name", "My First Spring Mvc");
		return modelAndView;
	}

	@RequestMapping(value = "/get_password.do", method = { RequestMethod.POST })
	// public ModelAndView getPassword(@ModelAttribute("getPasswordVO") GetPasswordVO getPasswordVO) {
	// public ModelAndView getPassword(@ModelAttribute GetPasswordVO getPasswordVO) {
	public ModelAndView getPassword(GetPasswordVO getPasswordVO) {
		ModelAndView modelAndView = new ModelAndView("get_password");

		userService.updatePasswordToDefault(getPasswordVO.getUsername());

		return modelAndView;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}