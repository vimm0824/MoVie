package com.MoVie.ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.ticket.bo.TicketBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ticket")
@RestController
public class TicketRestController {

	@Autowired
	private TicketBO ticketBO;
	
	// /ticket/ticketing
	@PostMapping("/ticketing")
	public Map<String, Object> ticketing(
			@RequestParam("movieCd") int movieCd,
			@RequestParam("plan") String plan,
			@RequestParam("seatArr[]") List<String> seatArr,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 400);
			result.put("result", "로그인 후 이용해주세요.");
			return result;
		}
		int row = 0;
		for (int i = 0; i < seatArr.size(); i++) {
			String seat = seatArr.get(i);
			int count = ticketBO.addTicket(userId, movieCd, plan, seat);
			row += count;
		}
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "티켓팅 성공");
			result.put("userId", userId);
		} else {
			result.put("code", 500);
			result.put("result", "DB 오류");
		}
		
		return result;
	}
	
	@DeleteMapping("/delete_ticket")
	public Map<String, Object> deleteTicket(
			@RequestParam("ticketId") int id,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 400);
			result.put("result", "로그인 후 이용해주세요.");
			return result;
		}
		
		int row = ticketBO.deleteTicketByIdUserId(id, userId);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "삭제 성공.");
		} else {
			result.put("code", 500);
			result.put("result", "로그인아이디와 티켓 구매자가 다릅니다.");
		}
		
		return result;
	}
}
