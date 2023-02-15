package com.MoVie.review.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.review.dao.ReviewDAO;
import com.MoVie.review.model.Review;
import com.MoVie.review.model.ReviewView;
import com.MoVie.search.bo.SearchBO;
import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.User;

@Service
public class ReviewBO {

	@Autowired
	private ReviewDAO reviewDAO;
	@Autowired
	private UserBO userBO;
	@Autowired
	private SearchBO searchBO;
	
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
	
	public int getCountReviewByUserId(int userId) {
		List<Review> reviewList = reviewDAO.selectReviewListByUserId(userId); 
		return reviewList.size();
	}
	
	public List<Map<String, Object>> getReviewListByUserId(int userId) {
		
		Map<String, Object> tempMap = new LinkedHashMap<>();
		
		List<Map<String, Object>> tempList = new ArrayList<>();
		
		List<Review> reviewList = reviewDAO.selectReviewListByUserId(userId);
		
		for (Review review : reviewList) {
			tempMap = searchBO.getDetailMovie(String.valueOf(review.getMovieCd()));
			tempMap.put("point", review.getPoint());
			tempList.add(tempMap);
		}
		
		return tempList;
	}
	
	public List<Map<String, Object>> getReviewListByUserIdOrderByPoint(int userId) {
		
		Map<String, Object> tempMap = new LinkedHashMap<>();
		List<Map<String, Object>> tempList = new ArrayList<>();
		
		List<Review> reviewList = reviewDAO.selectReviewListByUserIdOrderByPoint(userId);
		
		for (Review review : reviewList) {
			tempMap = searchBO.getDetailMovie(String.valueOf(review.getMovieCd()));
			tempMap.put("point", review.getPoint());
			tempList.add(tempMap);
		}
		
		return tempList;
	}
	
	public List<Map<String, Object>> getReviewListsByUserIdPoint (int userId, int point) {
		Map<String, Object> tempMap = new LinkedHashMap<>();
		List<Map<String, Object>> tempList = new ArrayList<>();
		
		List<Review> reviewList = reviewDAO.selectReviewListByUserIdPoint(userId, point);
		
		for (Review review : reviewList) {
			tempMap = searchBO.getDetailMovie(String.valueOf(review.getMovieCd()));
			tempMap.put("point", review.getPoint());
			tempList.add(tempMap);
		}
		
		return tempList;
	}
	
	public List<ReviewView> getReviewViewListByMovieCd(int movieCd) {
		List<Review> items = reviewDAO.selectReviewListByMovieCd(movieCd);
		List<ReviewView> result = new ArrayList<>();
		
		for (Review item : items) {
			ReviewView review = new ReviewView();
			review.setReview(item);
			
			User user = userBO.getUserById(item.getUserId());
			//review.setNickname(user.getNickname());
			review.setUser(user);
			
			result.add(review);
		}
		
		return result;
	}
	
	public int getReviewCountByMovieCdPoint(int movieCd, int point) {
		return reviewDAO.selectReviewCountByMovieCdPoint(movieCd, point);
	}
	
}
