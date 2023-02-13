package com.MoVie.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.follow.dao.FollowDAO;

@Service
public class FollowBO {

	@Autowired
	private FollowDAO followDAO;
	
	public int followToggle(int userId, int followId) {
		if (haveFollowByUserIdFollowId(userId, followId)) {
			return followDAO.deleteFollowByUserIdFollowId(userId, followId);
		} else {
			return followDAO.insertFollowByUserIdFollowId(userId, followId);
		}
	}
	
	public boolean haveFollowByUserIdFollowId(int userId, int followId) {
		return followDAO.selectFollowByUserIdFollowId(userId, followId) > 0 ? true : false;
	}
	
	public int countFollowByUserId(int userId) {
		return followDAO.countFollowByUserId(userId);
	}
	public int countFollowByFollowId(int userId) {
		return followDAO.countFollowByFollowId(userId);
	}
}
