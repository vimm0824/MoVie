package com.MoVie.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MoVie.follow.model.Follow;

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
	
	public List<Follow> selectFollowListFollowingByid(int userId);
	
	public List<Follow> selectFollowListByid(int id);
}
