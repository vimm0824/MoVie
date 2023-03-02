package com.MoVie.ticket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MoVie.ticket.model.Ticket;

@Repository
public interface TicketDAO {

	public int insertTicket(
			@Param("userId") int userId, 
			@Param("movieCd") int movieCd, 
			@Param("plan") String plan, 
			@Param("seat") String seat);
	
	public List<Ticket> selectTicketByMovieCd(
			@Param("movieCd") int movieCd,
			@Param("plan") String plan);
	
	public int countTicketByUserId(int userId);
	
	public List<Ticket> selectTicketByUserId(int userId);
	
	public int deleteTicketByIdUserId(
			@Param("id") int id,
			@Param("userId") int userId);
	
	public Ticket selectTicketById(int id);
	
	public void deleteTicket(@Param("userId") int userId,
			@Param("movieCd") int movieCd,
			@Param("seat") String seat,
			@Param("plan") String plan);
	
	public int updateTikcetGiftById(int id);
	
	public int updateTikcetGiftFalseById(int id);
	
	public Ticket selectTicket(@Param("userId") int userId,
			@Param("movieCd") int movieCd,
			@Param("seat") String seat,
			@Param("plan") String plan);
}
