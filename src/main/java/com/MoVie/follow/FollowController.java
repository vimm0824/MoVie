package com.MoVie.follow;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.follow.bo.FollowBO;
import com.MoVie.follow.model.FollowCard;
import com.MoVie.mypage.bo.MypageBO;
import com.MoVie.mypage.model.UserCardView;

@RequestMapping("/follow")
@Controller
public class FollowController {

	@Autowired
	private FollowBO followBO;
	@Autowired
	private MypageBO mypageBO;
	
	@GetMapping("/follow_view")
	public String myFollow(
			@RequestParam(value="userId", required=false) Integer userId,
			HttpSession session,
			Model model
			) {
		if (userId == null) {
			return "redirect:/main";
		}
		Integer sessionId = (Integer)session.getAttribute("userId");
		if (sessionId == null) {
			sessionId = 0; 
		}
		
		UserCardView card = mypageBO.generateCard(userId, sessionId);
		
		List<FollowCard> followingList = followBO.getFollowCardByUserId(userId);
		List<FollowCard> followList = followBO.getFollowCardByFollowId(userId);
		
		model.addAttribute("result", card);
		model.addAttribute("followingList", followingList);
		model.addAttribute("followList", followList);
		model.addAttribute("viewName", "follow/myFollow");
		return "template/layout";
	}
}
