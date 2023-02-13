package com.MoVie.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.mypage.bo.MypageBO;
import com.MoVie.mypage.model.UserCardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mypage")
@Controller
public class MypageController {
	
	@Autowired
	private MypageBO mypageBO;

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
		
		model.addAttribute("result", card);
		model.addAttribute("viewName", "mypage/mypage");
		return "template/layout";
	}
}
