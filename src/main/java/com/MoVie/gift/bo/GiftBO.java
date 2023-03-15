package com.MoVie.gift.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.gift.dao.GiftDAO;
import com.MoVie.gift.model.Gift;
import com.MoVie.gift.model.GiftCard;
import com.MoVie.ticket.bo.TicketBO;
import com.MoVie.ticket.model.TicketCard;
import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.User;

@Service
public class GiftBO {

	@Autowired
	private GiftDAO giftDAO;
	@Autowired
	private TicketBO ticketBO;
	@Autowired
	private UserBO userBO;
	
	public int insertGift(int userId, int receiverId, int ticketId, String message) {
		int code = ticketBO.updateTikcetGiftById(ticketId);
		int row = 0;
		if (code == 1) {
			giftDAO.insertGift(userId, receiverId, ticketId, message);
			row = 1;
		}
		return row;
	}
	
	public List<Gift> getGiftListByReceiverId(int receiverId) {
		return giftDAO.selectGiftListByReceiverId(receiverId);
	}
	
	public List<GiftCard> getGiftCardListByReceiverId(int receiverId) {
		List<GiftCard> result = new ArrayList<>();
		
		List<Gift> giftList = getGiftListByReceiverId(receiverId);
		
		for (Gift gift : giftList) {
			GiftCard giftCard = new GiftCard();
			giftCard.setGift(gift);
			
			TicketCard ticketCard = ticketBO.getTicketCardById(gift.getTicketId());
			giftCard.setTicketCard(ticketCard);
			
			//받은 사람
			User receive = userBO.getUserById(receiverId);
			giftCard.setReceiverNickename(receive.getNickname());
			
			//보낸사람
			User send = userBO.getUserById(ticketCard.getTicket().getUserId());
			giftCard.setUserNickename(send.getNickname());
			
			result.add(giftCard);
		}
		
		return result;
	}
	
	public Gift getGiftByIdReceiverId(int id, int receiverId) {
		return giftDAO.selectGiftByIdReceiverId(id, receiverId);
	}
	
	public int deleteGiftByIdReceiverId(int id, int receiverId) {
		Gift gift = getGiftByIdReceiverId(id, receiverId);
		if (gift == null) {
			return 0;
		} else {
			giftDAO.deleteGiftByIdReceiverId(id, receiverId);
			return ticketBO.updateTikcetGiftFalseById(gift.getTicketId());
		}
	}
	
}
