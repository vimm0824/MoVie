package com.MoVie.wish.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.search.bo.SearchBO;
import com.MoVie.wish.dao.WishDAO;
import com.MoVie.wish.model.Wish;

@Service
public class WishBO {

	@Autowired
	private WishDAO wishDAO;
	@Autowired
	private SearchBO searchBO;
	
	public int wishToggle(int userId, int movieCd) {
		if (haveWishByUserIdMovieCd(userId, movieCd)) { // 있으면
			return wishDAO.deleteWishByUserIdMovieCd(userId, movieCd);
		} else {
			return wishDAO.insertWishByUserIdMovieCd(userId, movieCd);
		}
	}
	
	public boolean haveWishByUserIdMovieCd(int userId, int movieCd) {
		return wishDAO.selectWishByUserIdMovieCd(userId, movieCd) > 0 ? true : false;
	}
	
	public int countWishByUserId(int userId) {
		return wishDAO.countWishByUserId(userId);
	}
	
	public int countWishByMovieCd(int movieCd) {
		return wishDAO.countWishByMovieCd(movieCd);
	}
	
	public List<Map<String, Object>> getReviewListByUserId(int userId){
		Map<String, Object> tempMap = new LinkedHashMap<>();
		
		List<Map<String, Object>> tempList = new ArrayList<>();
		
		List<Wish> wishList = wishDAO.selectReviewListByUserId(userId);
		
		for (Wish wish : wishList) {
			tempMap = searchBO.getDetailMovie(String.valueOf(wish.getMovieCd()));
			tempList.add(tempMap);
		}
		
		return tempList;
	}
}
