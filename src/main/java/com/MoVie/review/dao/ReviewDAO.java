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
	
	public List<Review> selectReviewListByUserId(int userId);
	
	public List<Review> selectReviewListByUserIdOrderByPoint(int userId);

	public List<Review> selectReviewListByUserIdPoint(
			@Param("userId") int userId,
			@Param("point") int point);
	
	public int selectReviewCountByMovieCdPoint(
			@Param("movieCd") int movieCd,
			@Param("point") int point);
	
	public void deleteReviewByUserIdByMovieCd(
			@Param("userId") int userId,
			@Param("movieCd") int movieCd);
	
	public void updateReviewByUserIdMovieCd(
			@Param("userId") int userId, 
			@Param("movieCd") int movieCd, 
			@Param("point") int point, 
			@Param("review") String review);
}
