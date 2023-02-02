package com.MoVie.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@GetMapping("/daily")
	public String daily(
			@RequestParam("date") int date,
			Model model) {
		//List<DailyBoxOffice> list = dailyBoxOfficeBO.getDailyBoxOffice(date);
		
		//model.addAttribute("result", );
		return "test/test";
	}
}
