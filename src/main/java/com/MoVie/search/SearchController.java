package com.MoVie.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.search.bo.SearchBO;

@RequestMapping("/search")
@Controller
public class SearchController {

	@Autowired
	private SearchBO searchBO;
	
	@GetMapping("/movie_view")
	public String searchMovie(
			@RequestParam("title") String title,
			Model model) {
		List<Map<String, Object>> result = searchBO.getSearchMovie(title);
		
		model.addAttribute("result", result);
		model.addAttribute("viewName", "search/searchMovie");
		return "template/layout";
	}
}
