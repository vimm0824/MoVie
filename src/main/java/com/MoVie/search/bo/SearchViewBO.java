package com.MoVie.search.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.review.bo.ReviewBO;
import com.MoVie.wish.bo.WishBO;

@Service
public class SearchViewBO {

	@Autowired
	private SearchBO searchBO; 
	@Autowired
	private ReviewBO reviewBO;
	@Autowired
	private WishBO wishBO;
	
	public List<Map<String, Object>> getSearchView(String search) {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Map<String, Object>> tempList = searchBO.getSearchMovie(search);
		Map<String, Object> tempMap = new HashMap<>();
		
		for (Map<String, Object> movie : tempList) {
			int pointCount = reviewBO.getReviewCountByMovieCdPoint(Integer.valueOf((String)movie.get("movieCd")), 5) +
					reviewBO.getReviewCountByMovieCdPoint(Integer.valueOf((String)movie.get("movieCd")), 4);
			tempMap = movie;
			tempMap.put("pointCount", pointCount);
			int wishCount = wishBO.countWishByMovieCd(Integer.valueOf((String)movie.get("movieCd")));
			tempMap.put("wishCount", wishCount);
			result.add(tempMap);
		}
		
		return result;
	}
}
