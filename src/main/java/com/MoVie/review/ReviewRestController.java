package com.MoVie.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.review.bo.ReviewBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@RestController
public class ReviewRestController {

	@Autowired
	private ReviewBO reviewBO;
	
	@PostMapping("/add_review")
	public Map<String, Object> addReview(
			@RequestParam("movieCd") int movieCd,
			@RequestParam("point") int point,
			@RequestParam(value="review",required=false) String review,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("result", "로그인후에 이용해주세요.");
			return result;
		}
		
		
		int row = reviewBO.addReviewByUserId(userId, movieCd, point, review);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else if (row == 0) {
			result.put("code", 500);
			result.put("result", "이미 작성한 리뷰가 있습니다.");
		} else {
			result.put("code", 500);
			result.put("result", "실패");
		}
		
		return result;
	}
}
