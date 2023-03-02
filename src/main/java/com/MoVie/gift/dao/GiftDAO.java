package com.MoVie.gift.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MoVie.gift.model.Gift;

@Repository
public interface GiftDAO {

	public int insertGift(
			@Param("userId") int userId,
			@Param("receiverId") int receiverId,
			@Param("ticketId") int ticketId,
			@Param("message") String message);
	
	public List<Gift> selectGiftListByReceiverId(int receiverId);
	
	public Gift selectGiftByIdReceiverId(
			@Param("id") int id,
			@Param("receiverId") int receiverId);
	
	public void deleteGiftByIdReceiverId(
			@Param("id") int id,
			@Param("receiverId") int receiverId);
}
