package com.MoVie.gift;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.gift.bo.GiftBO;
import com.MoVie.gift.model.GiftCard;
import com.MoVie.mypage.bo.MypageBO;
import com.MoVie.mypage.model.UserCardView;
import com.MoVie.ticket.bo.TicketBO;
import com.MoVie.ticket.model.TicketCard;

@RequestMapping("/gift")
@Controller
public class GiftController {

	@Autowired
	private GiftBO giftBO;
	@Autowired
	private MypageBO mypageBO;
	@Autowired
	private TicketBO ticketBO;
	
	@GetMapping("/gift_ticket_view")
	public String giftTicket(
			@RequestParam(value="id", required=false) Integer id,
			@RequestParam(value="userId", required=false) Integer userId,
			HttpSession session,
			Model model) {

		TicketCard ticketCard = ticketBO.getTicketCardById(id);
		
		model.addAttribute("ticketCard", ticketCard);
		model.addAttribute("viewName", "gift/giftTicket");
		return "template/layout";
	}
	
	
	
	@GetMapping("/gift_box_view")
	public String giftBox(
			@RequestParam(value="userId", required=false) Integer userId,
			HttpSession session,
			Model model
			) {
		
		int sessionId = 0;
		if ((Integer) session.getAttribute("userId") == null) {
			return "redirect:/user/sign_in_view";
		} else {
			sessionId = (Integer) session.getAttribute("userId");
		}
		if (userId == null) {
			return "redirect:/main";
		}
		if (userId != sessionId) {
			return "redirect:/main";
		}
		
		UserCardView card = mypageBO.generateCard(userId, sessionId);
		
		List<GiftCard> giftCardList = giftBO.getGiftCardListByReceiverId(userId);
		
		model.addAttribute("result", card);
		model.addAttribute("giftCardList", giftCardList);
		model.addAttribute("viewName", "gift/giftBox");
		return "template/layout";
	}
}
