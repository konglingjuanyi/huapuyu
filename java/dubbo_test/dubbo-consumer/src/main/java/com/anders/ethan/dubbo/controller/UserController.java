package com.anders.ethan.dubbo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anders.ethan.dubbo.service.UserService;

@Controller
@RequestMapping("/dubbotest")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public String get(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		System.out.println(userService.sayFuck("laosan"));

		return "";
	}

}
