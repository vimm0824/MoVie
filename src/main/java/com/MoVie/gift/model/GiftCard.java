package com.MoVie.gift.model;

import com.MoVie.ticket.model.TicketCard;

public class GiftCard {

	private Gift gift;
	//보낸사람
	private String userNickename;
	//받은사람
	private String receiverNickename;
	private TicketCard ticketCard;
	//
	public Gift getGift() {
		return gift;
	}
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	public String getUserNickename() {
		return userNickename;
	}
	public void setUserNickename(String userNickename) {
		this.userNickename = userNickename;
	}
	public String getReceiverNickename() {
		return receiverNickename;
	}
	public void setReceiverNickename(String receiverNickename) {
		this.receiverNickename = receiverNickename;
	}
	public TicketCard getTicketCard() {
		return ticketCard;
	}
	public void setTicketCard(TicketCard ticketCard) {
		this.ticketCard = ticketCard;
	}
	
}
