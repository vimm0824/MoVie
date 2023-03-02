package com.MoVie.gift;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.gift.bo.GiftBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/gift")
@RestController
public class GiftRestController {

	@Autowired
	private GiftBO giftBO;
	
	@PostMapping("/ticket")
	public Map<String, Object> giftTicket(
			@RequestParam("receiverId") int receiverId,
			@RequestParam("ticketId") int ticketId,
			@RequestParam(value="message", required=false) String message,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 400);
			result.put("result", "로그인 후 이용해주세요");
			return result;
		}
		
		int row = giftBO.insertGift(userId, receiverId, ticketId, message);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("result", "전송 가능한 티켓이 없습니다.");
		}
		
		return result;
	}
	
	@GetMapping("/delete_gift")
	public Map<String, Object> deleteGift(
			@RequestParam("id") int id,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 400);
			result.put("result", "로그인 후 이용해주세요.");
			return result;
		}
		
		int row = giftBO.deleteGiftByIdReceiverId(id, userId);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "거절");
		} else {
			result.put("code", 500);
			result.put("result", "거절 실패");
		}
		
		return result;
	}
	
}
