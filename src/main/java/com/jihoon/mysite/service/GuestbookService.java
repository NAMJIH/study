package com.jihoon.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jihoon.mysite.dao.GuestbookDao;
import com.jihoon.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao gd;
	
	
	public List<GuestbookVo> getGuestbookList() {
		List<GuestbookVo> list = gd.getList();
		
		return list;
	}
	public void GuestbookDelete(GuestbookVo gv){
		 gd.delete(gv);
		 
	}
	public void GuestbookAdd(GuestbookVo gv) {
		gd.insert(gv);
	}
}
