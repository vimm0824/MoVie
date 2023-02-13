package com.MoVie.mypage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.follow.bo.FollowBO;
import com.MoVie.mypage.model.UserCardView;
import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class MypageBO {

	@Autowired
	private UserBO userBO;
	@Autowired
	private FollowBO followBO;
	
	public UserCardView generateCard(
			int pageUserId, int userId) {
//		HttpSession session
//		Integer userId = (Integer)session.getAttribute("userId");
//		if (userId == null) {
//			userId = 0;
//		}
		
		UserCardView card = new UserCardView();
		
		User user = userBO.getUserById(pageUserId);
		card.setUser(user);
		
		//followCount
		card.setFollowCount(followBO.countFollowByFollowId(pageUserId));
		//followingCount
		card.setFollowingCount(followBO.countFollowByUserId(pageUserId));
		
		boolean filedFollow = followBO.haveFollowByUserIdFollowId(userId, user.getId());
		card.setFiledFollow(filedFollow);
		
		return card;
	}
}
