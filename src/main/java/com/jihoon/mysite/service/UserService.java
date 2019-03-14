package com.jihoon.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jihoon.mysite.dao.UserDao;
import com.jihoon.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public void UserJoin(UserVo userVo) {
		userDao.join(userVo);
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.login(userVo);
	}
	
	public UserVo updateform(@RequestParam("no") long no, UserVo userVo) {
		return userDao.getByNo(no);
	}
	public UserVo getUser(long no) {
		UserVo userVo = userDao.getByNo(no);
		return userVo;
		
	}
	public void updateUser(UserVo userVo) {
		userDao.update(userVo);
	}
}

