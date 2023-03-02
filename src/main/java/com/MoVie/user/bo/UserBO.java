package com.MoVie.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MoVie.common.FileManagerService;
import com.MoVie.user.dao.UserDAO;
import com.MoVie.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FileManagerService fileManagerService;
	
	public int addUser(String loginId, String password,String email, String name, String nickname) {
		return userDAO.insertUser(loginId, password, email, name, nickname);
	}
	
	public User existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
	
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selecetUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserByLoginIdNameEmail(String loginId, String name, String email) {
		return userDAO.selecetUserByLoginIdNameEmail(loginId, name, email);
	}
	
	public int updateUserByuserId(int userId, String password) {
		return userDAO.updateUserByuserId(userId, password);
	}
	
	public User getUserById(int id) {
		return userDAO.selectetUserById(id);
	}
	
	public void updataeUserById(int id, String nickname, MultipartFile file) {
		User user = getUserById(id);
		String userLoginId = user.getLoginId();
		
		if (nickname.isEmpty()) {
			nickname = user.getNickname();
		}
		
		String profileUrl = null;
		if (file == null) {
			profileUrl = user.getProfileUrl();
		}
		if (file != null) {
			profileUrl = fileManagerService.saveFile(userLoginId, file);
			
			if (profileUrl != null && user.getProfileUrl() != null) {
				fileManagerService.deleteFile(user.getProfileUrl());
			}
		}
		
		userDAO.updataeUserById(id, nickname, profileUrl);
	}
}
