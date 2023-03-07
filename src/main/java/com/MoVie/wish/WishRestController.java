package com.MoVie.wish;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.wish.bo.WishBO;

@RequestMapping("/wish")
@RestController
public class WishRestController {

	@Autowired
	private WishBO wishBO;
	
	@GetMapping("/wish")
	public Map<String, Object> wish(
			@RequestParam("movieCd") int movieCd,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 400);
			result.put("result", "로그인 후 사용해주세요.");
			return result;
		}
		
		int row = wishBO.wishToggle(userId, movieCd);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "보고싶어요!");
		} else {
			result.put("code", 500);
			result.put("result", "controller error");
		}
		
		return result;
	}
}
