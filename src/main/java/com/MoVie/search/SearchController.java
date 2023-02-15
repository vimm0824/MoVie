package com.MoVie.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.review.bo.ReviewBO;
import com.MoVie.review.model.ReviewView;
import com.MoVie.search.bo.SearchBO;
import com.MoVie.search.bo.SearchViewBO;

@RequestMapping("/search")
@Controller
public class SearchController {

	@Autowired
	private SearchBO searchBO;
	@Autowired
	private ReviewBO reviewBO;
	@Autowired
	private SearchViewBO searchViewBO;
	
	@GetMapping("/movie_view")
	public String searchMovie(
			@RequestParam(value="search", required=false) String search,
			Model model) {
		// 검색어에 아무것도 입력하지 않으면 박스오피스 1위가 나오게 할것
		
		List<Map<String, Object>> result = new ArrayList<>();
		
		if (search == "") {
			return "redirect:/main";
		} else {
//			result = searchBO.getSearchMovie(search);
			result = searchViewBO.getSearchView(search);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("viewName", "search/searchMovie");
		return "template/layout";
	}
	
	@GetMapping("/detail_movie_view")
	public String detailMovie(
			@RequestParam("movieCd") String movieCd,
			Model model
			) {
		
		Map<String, Object> result = searchBO.getDetailMovie(movieCd);
		int pointCount = reviewBO.getReviewCountByMovieCdPoint(Integer.valueOf((String)result.get("movieCd")), 5) +
				reviewBO.getReviewCountByMovieCdPoint(Integer.valueOf((String)result.get("movieCd")), 4);
		List<ReviewView> reviewList = reviewBO.getReviewViewListByMovieCd(Integer.parseInt(movieCd));
		
		model.addAttribute("result", result);
		model.addAttribute("pointCount", pointCount);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("viewName", "search/detailMovie");
		return "template/layout";
	}
}
