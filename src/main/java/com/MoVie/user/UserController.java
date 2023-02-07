package com.MoVie.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/sign_in_view")
	public String signIn(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	@GetMapping("/sign_up_view")
	public String signUp(Model model) {
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
}
