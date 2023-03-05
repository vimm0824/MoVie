package com.MoVie.follow.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.follow.dao.FollowDAO;
import com.MoVie.follow.model.Follow;
import com.MoVie.follow.model.FollowCard;
import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.User;

@Service
public class FollowBO {

	@Autowired
	private FollowDAO followDAO;
	@Autowired
	private UserBO userBO;
	
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
	
	//팔로우
	public List<Follow> getFollowListByid(int id) {
		return followDAO.selectFollowListByid(id);
	}
	
	// 팔로잉
	public List<Follow> getFollowListFollowingByid(int id) {
		return followDAO.selectFollowListFollowingByid(id);
	}
	
	public List<FollowCard> getFollowCardByUserId(int userId) {
		// 팔로우 내가 당한거
		// 팔로잉 내가 한거
		List<FollowCard> cardList = new ArrayList<>();
		List<Follow> userList = getFollowListFollowingByid(userId);
		
		//팔로잉
		for (Follow f : userList) {
			FollowCard card = new FollowCard();
			card.setFollow(f);
			
			User user = userBO.getUserById(f.getFollowId());
			card.setUser(user);
			
			cardList.add(card);
		}
		
		return cardList;
	}
	
	//팔로우
	public List<FollowCard> getFollowCardByFollowId(int followId) {
		List<FollowCard> cardList = new ArrayList<>();
		List<Follow> userList = getFollowListByid(followId);
		
		for (Follow f : userList) {
			FollowCard card = new FollowCard();
			card.setFollow(f);
			
			User user = userBO.getUserById(f.getUserId());
			card.setUser(user);
			
			cardList.add(card);
		}
		
		return cardList;
	}
}
