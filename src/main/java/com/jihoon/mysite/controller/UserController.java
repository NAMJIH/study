package com.jihoon.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jihoon.mysite.service.UserService;
import com.jihoon.mysite.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/loginform")
	public String loginform() {
		
		return "user/loginform";
	}
	@RequestMapping("/user/joinform")
	public String joinform() {
		
		return "user/joinform";
	}
	
	@RequestMapping("/user/updateform")
	public String updateform(HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		
		return "user/updateform";
	}
	
	@RequestMapping("/user/update")
	public String update(@ModelAttribute UserVo userVo) {
		userService.updateUser(userVo);
		return "redirect:/user/updateform";
	}
	
	@RequestMapping("/user/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = userService.login(userVo);
//		System.out.println( authUser );
		if( authUser == null ) {
			return "redirect:/user/loginform";
		}
		
		// 로그인 처리
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/user/join")
	public String join(@ModelAttribute UserVo userVo) {
		userService.UserJoin(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/user/joinsuccess")
	public String joinsuccess() {
		
		return "user/joinsuccess";
	}
	
	
	
}
