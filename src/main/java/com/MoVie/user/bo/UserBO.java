package com.MoVie.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.user.dao.UserDAO;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId, String password, String name, String nickname) {
		return userDAO.insertUser(loginId, password, name, nickname);
	}
	
	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
}
