package com.MoVie.wish.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.wish.dao.WishDAO;

@Service
public class WishBO {

	@Autowired
	private WishDAO wishDAO;
	
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
}
