package com.MoVie.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.user.dao.UserDAO;
import com.MoVie.user.model.User;

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
	
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selecetUserByLoginIdPassword(loginId, password);
	}
}
