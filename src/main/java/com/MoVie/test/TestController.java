package com.MoVie.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestController {

//	@GetMapping("/daily")
//	public String daily(Model model) {
//		String key = "0a3d75ef1d272ded4accb0dcb474129a";
//		String date = "20230101";
//		String result = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
//		+ key + "&targetDt=" + date;
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = restTemplate.getForEntity(result, String.class);
//		
//		
//		//model.addAttribute("result", );
//		return "test/test";
//	}
}
