package com.MoVie.review.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.review.dao.ReviewDAO;
import com.MoVie.review.model.Review;

@Service
public class ReviewBO {

	@Autowired
	private ReviewDAO reviewDAO;
	
	public int addReviewByUserId(int userId, int movieCd, int point, String review) {
		int count = getReviewByUserIdMovieCd(userId, movieCd);
		if (count == 0) {
			return reviewDAO.insertReviewByUserId(userId, movieCd, point, review);
		} else {
			return 0;
		}
	}
	
	public int getReviewByUserIdMovieCd(int userId, int movieCd) {
		return reviewDAO.selectReviewByUserIdMovieCd(userId, movieCd);
	}
	
	public List<Review> getReviewListByMovieCd(int movieCd) {
		return reviewDAO.selectReviewListByMovieCd(movieCd);
	}
	
}
