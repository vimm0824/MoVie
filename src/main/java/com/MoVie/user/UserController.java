package com.MoVie.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.User;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserBO userBO;
	
	@GetMapping("/sign_in_view")
	public String signIn(Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			return "redirect:/main";
		}
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	@GetMapping("/sign_up_view")
	public String signUp(Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			return "redirect:/main";
		}
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("loginId");
		session.removeAttribute("nickname");
		return "redirect:/main";
	}
	
	@GetMapping("/find_password_view")
	public String findPassword(Model model) {
		model.addAttribute("viewName", "user/findPassword");
		return "template/layout"; 
	}
	
	@GetMapping("/certify_view")
	public String certifyCode(
			@RequestParam("userId") int userId,
			Model model) {
		model.addAttribute("id", userId);
		model.addAttribute("viewName", "user/certifyCode");
		return "template/layout"; 
	}
	
	@GetMapping("/search_user_view")
	public String searchUser(
			@RequestParam(value="nickname", required=false) String nickname,
			Model model) {
		
		List<User> userList = new ArrayList<>();
		if (nickname != null) {
			userList = userBO.getUserListByNickname(nickname);
			model.addAttribute("userList", userList);
		}
		
		model.addAttribute("viewName", "user/searchUser");
		return "template/layout";
	}
	
}
