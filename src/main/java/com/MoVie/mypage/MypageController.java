package com.MoVie.mypage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.mypage.bo.MypageBO;
import com.MoVie.mypage.model.UserCardView;
import com.MoVie.review.bo.ReviewBO;

@RequestMapping("/mypage")
@Controller
public class MypageController {
	
	@Autowired
	private MypageBO mypageBO;
	@Autowired
	private ReviewBO reviewBO;
	
	@GetMapping("/mypage_view")
	public String myPage(
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
		
		List<Map<String, Object>> reviewMovie = reviewBO.getReviewListByUserId(userId);
		
		model.addAttribute("result", card);
		model.addAttribute("reviewList", reviewMovie);
		model.addAttribute("viewName", "mypage/mypage");
		return "template/layout";
	}
	
	@GetMapping("/desc")
	public String desc(
			@RequestParam("userId") int userId,
			Model model
			) {
		List<Map<String, Object>> reviewMovie = reviewBO.getReviewListByUserId(userId);
		model.addAttribute("reviewList", reviewMovie);
		return "mypage/mypageDesc";
	}
	
	
	@GetMapping("/point")
	public String point(
			@RequestParam("userId") int userId,
			Model model
			) {
		
		List<Map<String, Object>> pointFive = reviewBO.getReviewListsByUserIdPoint(userId, 5);
		List<Map<String, Object>> pointFour = reviewBO.getReviewListsByUserIdPoint(userId, 4);
		List<Map<String, Object>> pointThree = reviewBO.getReviewListsByUserIdPoint(userId, 3);
		List<Map<String, Object>> pointTwo = reviewBO.getReviewListsByUserIdPoint(userId, 2);
		List<Map<String, Object>> pointOne = reviewBO.getReviewListsByUserIdPoint(userId, 1);
		
		List<List<Map<String, Object>>> result = new ArrayList<>();
		result.add(pointFive);
		result.add(pointFour);
		result.add(pointThree);
		result.add(pointTwo);
		result.add(pointOne);
		
		model.addAttribute("result", result);
		return "mypage/mypagePoint";
	}
	
}
