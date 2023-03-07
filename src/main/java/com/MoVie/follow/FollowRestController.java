package com.MoVie.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.follow.bo.FollowBO;

@RequestMapping("/follow")
@RestController
public class FollowRestController {

	@Autowired
	private FollowBO followBO;
	
	@GetMapping("/{followId}")
	public Map<String, Object> follow(
			@PathVariable int followId,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 500);
			result.put("result", "로그인 후 이용해주세요.");
			return result;
		}
		if (userId == followId) {
			result.put("code", 500);
			result.put("result", "본인은 팔로우 할 수 없습니다.");
			return result;
		}
		
		int row = followBO.followToggle(userId, followId);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "팔로우 성공");
		} else {
			result.put("code", 500);
			result.put("result", "팔로우 실패!!!");
		}
		
		return result;
	}
}
