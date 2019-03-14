package com.jihoon.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jihoon.mysite.dao.GuestbookDao;
import com.jihoon.mysite.service.GuestbookService;
import com.jihoon.mysite.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookService gs;
	
	@RequestMapping("/guestbook/list")
	public String list(Model model) {
		
		List<GuestbookVo> list = gs.getGuestbookList();
		model.addAttribute("list", list);
		
		return "guestbook/list";
	}
	@RequestMapping("/guestbook/deleteform")
	public String deleteform(@RequestParam("no") long no, Model model){
		model.addAttribute("no" , no);
		return "guestbook/deleteform";
	}
	@RequestMapping("/guestbook/delete")
	public String delete(@ModelAttribute GuestbookVo gv) {
		gs.GuestbookDelete(gv);
		return "redirect:/guestbook/list";
	}
	@RequestMapping("/guestbook/add")
	public String add(@ModelAttribute GuestbookVo gv) {
		gs.GuestbookAdd(gv);
		return "redirect:/guestbook/list";
	}
}	
