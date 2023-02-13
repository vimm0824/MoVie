package com.MoVie.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.review.bo.ReviewBO;
import com.MoVie.review.model.Review;
import com.MoVie.search.bo.SearchBO;

@RequestMapping("/search")
@Controller
public class SearchController {

	@Autowired
	private SearchBO searchBO;
	@Autowired
	private ReviewBO reviewBO;
	
	@GetMapping("/movie_view")
	public String searchMovie(
			@RequestParam(value="search", required=false) String search,
			Model model) {
		// 검색어에 아무것도 입력하지 않으면 박스오피스 1위가 나오게 할것
		
		if (search == "") {
			search = "라라랜드";
		}
		
		List<Map<String, Object>> result = searchBO.getSearchMovie(search);
		
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
		List<Review> reviewList = reviewBO.getReviewListByMovieCd(Integer.parseInt(movieCd));
		
		model.addAttribute("result", result);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("viewName", "search/detailMovie");
		return "template/layout";
	}
}
