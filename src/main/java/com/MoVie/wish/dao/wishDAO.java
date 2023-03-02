package com.MoVie.wish.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WishDAO {

	public int selectWishByUserIdMovieCd(
			@Param("userId") int userId,
			@Param("movieCd") int movieCd);
	
	public int deleteWishByUserIdMovieCd(
			@Param("userId") int userId,
			@Param("movieCd") int movieCd);
	
	public int insertWishByUserIdMovieCd(
			@Param("userId") int userId,
			@Param("movieCd") int movieCd);
	
	public int countWishByUserId(int userId);
	
	public int countWishByMovieCd(int movieCd);
}
