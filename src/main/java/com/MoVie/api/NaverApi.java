package com.MoVie.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NaverApi {

	@GetMapping("/search")
	public String searchApi(
			@RequestParam("str") String str,
			Model model) {
		String clientId = "iq5JX8jZ3Z7TM_NGsJFU";
		String clientSecret = "OHbgiB1VGE";
		
		String text = "";
		String apiUrl = "";
		try {
			text = URLEncoder.encode(str, "UTF-8");
			apiUrl = "https://openapi.naver.com/v1/search/movie.json" + text;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	URL url = new URL(apiUrl);
		
		return "test/searchTest";
	}
	
	@ResponseBody
	public String movieApi() {
		String jsonString = "";
		
		
		
		return jsonString;
	}
}
