package com.MoVie.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mypage")
@Controller
public class MypageController {

	@GetMapping("/mypage_view")
	public String myPage(Model model) {
		model.addAttribute("viewName", "mypage/mypage");
		return "template/layout";
	}
}
