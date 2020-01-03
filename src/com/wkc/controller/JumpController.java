package com.wkc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JumpController {
	
	
	//退出系统，返回到登录界面
	@RequestMapping("/Exit")
	public ModelAndView Exit(HttpSession session) {
		Integer i =(Integer) session.getAttribute("count");
		session.setAttribute("count", 0);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forward:index.jsp");
		return mav;
	}
	
	@RequestMapping("/JumpToAddManager")
	public ModelAndView JumpToAddManager() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AddManager");
		return mav;		
	}
	
	@RequestMapping("/JumpToAddArea")
	public ModelAndView JumpToAddArea() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AddArea");
		return mav;		
	}
	
	
	@RequestMapping("/welcomeJsp")
	public ModelAndView JumpToWelcome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Welcome");
		return mav;
		
	}
	

}
