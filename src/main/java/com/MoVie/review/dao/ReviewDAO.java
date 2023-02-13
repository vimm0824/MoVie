package com.MoVie.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MoVie.review.model.Review;

@Repository
public interface ReviewDAO {

	public int insertReviewByUserId(
			@Param("userId") int userId, 
			@Param("movieCd") int movieCd, 
			@Param("point") int point, 
			@Param("review") String review);
	
	public int selectReviewByUserIdMovieCd(
			@Param("userId") int userId,
			@Param("movieCd") int movieCd);
	
	public List<Review> selectReviewListByMovieCd(int movieCd);
}
