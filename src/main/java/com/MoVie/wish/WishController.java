package com.MoVie.wish;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.mypage.bo.MypageBO;
import com.MoVie.mypage.model.UserCardView;
import com.MoVie.wish.bo.WishBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/wish")
@Controller
public class WishController {

	@Autowired
	private WishBO wishBO;
	@Autowired
	private MypageBO mypageBO;
	
	@GetMapping("/wish_movie_view")
	public String wishMoview(
			@RequestParam(value="userId", required=false) Integer userId,
			HttpSession session,
			Model model) {
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		Integer sessionId = (Integer)session.getAttribute("userId");
		if (sessionId == null) {
			sessionId = 0; 
		}
		
		UserCardView card = mypageBO.generateCard(userId, sessionId);
		
		List<Map<String, Object>> wishMovieList = wishBO.getReviewListByUserId(userId);
		
		model.addAttribute("result", card);
		model.addAttribute("wishMovieList", wishMovieList);
		model.addAttribute("viewName", "wish/wishMovie");
		return "template/layout";
	}
}
