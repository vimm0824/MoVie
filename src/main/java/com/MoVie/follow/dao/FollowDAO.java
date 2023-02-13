package com.MoVie.follow.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowDAO {

	public int selectFollowByUserIdFollowId(
			@Param("userId") int userId,
			@Param("followId") int followId);
	
	public int deleteFollowByUserIdFollowId(
			@Param("userId") int userId,
			@Param("followId") int followId);
	
	public int insertFollowByUserIdFollowId(
			@Param("userId") int userId,
			@Param("followId") int followId); 
	
	public int countFollowByUserId(int userId);
	public int countFollowByFollowId(int followId);
}
