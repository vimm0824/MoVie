package com.MoVie.ticket.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.search.bo.SearchBO;
import com.MoVie.ticket.dao.TicketDAO;
import com.MoVie.ticket.model.Ticket;
import com.MoVie.ticket.model.TicketCard;

@Service
public class TicketBO {

	@Autowired
	private TicketDAO ticketDAO;
	@Autowired
	private SearchBO searchBO;
	
	public int addTicket(int userId, int movieCd, String plan, String seat) {
		return ticketDAO.insertTicket(userId, movieCd, plan, seat);
	}
	
	public List<String> getTicketSeatByMovieCdPlan(int movieCd, String plan){
		List<Ticket> ticketList = ticketDAO.selectTicketByMovieCd(movieCd, plan);
		List<String> seatList = new ArrayList<>();
		for (Ticket ticket : ticketList) {
			seatList.add(ticket.getSeat());
		}
		return seatList;
	}
	
	public int countTicketByUserId(int userId) {
		return ticketDAO.countTicketByUserId(userId);
	}
	
	public List<TicketCard> getTicketCardListByUserId(int userId){
		List<TicketCard> ticketCardList = new ArrayList<>();
		List<Ticket> ticketList = ticketDAO.selectTicketByUserId(userId);
		
		for (Ticket ticket : ticketList) {
			TicketCard ticketCard = new TicketCard();
			
			ticketCard.setTicket(ticket);
			
			Map<String, Object> movie = searchBO.getDetailMovie(Integer.toString(ticket.getMovieCd()));

			ticketCard.setMovieNm((String)movie.get("movieNm"));
			ticketCard.setImage((String)movie.get("image"));
			
			
			ticketCardList.add(ticketCard);
		}
		
		return ticketCardList;
	}
	
	public int deleteTicketByIdUserId(int id, int userId) {
		return ticketDAO.deleteTicketByIdUserId(id, userId);
	}
	
	public int updateTikcetGiftById(int id) {
		return ticketDAO.updateTikcetGiftById(id);
	}
	
	public int updateTikcetGiftFalseById(int id) {
		return ticketDAO.updateTikcetGiftFalseById(id);
	}
	
	public Ticket getTikcetByid(int id) {
		return ticketDAO.selectTicketById(id);
	}
	
	public TicketCard getTicketCardById(int id) {
		TicketCard ticketCard = new TicketCard();
		Ticket ticket = ticketDAO.selectTicketById(id);
		ticketCard.setTicket(ticket);
		Map<String, Object> movie = searchBO.getDetailMovie(Integer.toString(ticket.getMovieCd()));
		
		ticketCard.setMovieNm((String)movie.get("movieNm"));
		ticketCard.setImage((String)movie.get("image"));
		
		return ticketCard;
	}
	
	public int deleteTicket(int ticketId) {
		Ticket ticket = ticketDAO.selectTicketById(ticketId);
		if (ticket == null) {
			return 400;
		} else {
			int userId = ticket.getUserId();
			int movieCd = ticket.getMovieCd();
			String getSeat = ticket.getSeat();
			String plan = ticket.getPlan();
			ticketDAO.deleteTicket(userId, movieCd, getSeat, plan);
			return 1;
		}
	}
	
	
}
