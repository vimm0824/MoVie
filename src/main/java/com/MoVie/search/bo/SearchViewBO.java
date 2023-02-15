package com.MoVie.search.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.review.bo.ReviewBO;

@Service
public class SearchViewBO {

	@Autowired
	private SearchBO searchBO; 
	@Autowired
	private ReviewBO reviewBO;
	
	public List<Map<String, Object>> getSearchView(String search) {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Map<String, Object>> tempList = searchBO.getSearchMovie(search);
		Map<String, Object> tempMap = new HashMap<>();
		
		for (Map<String, Object> movie : tempList) {
			int count = reviewBO.getReviewCountByMovieCdPoint(Integer.valueOf((String)movie.get("movieCd")), 5) +
			reviewBO.getReviewCountByMovieCdPoint(Integer.valueOf((String)movie.get("movieCd")), 4);
			tempMap = movie;
			tempMap.put("pointCount", count);
			result.add(tempMap);
		}
		
		return result;
	}
}
