package com.MoVie.search.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.common.BoxOfficeAPI;
import com.MoVie.common.NaverSearchAPI;

@Service
public class SearchBO {

	@Autowired
	private BoxOfficeAPI boxOfficeAPI;
	
	@Autowired
	private NaverSearchAPI naverSearchAPI;
	
	public List<Map<String, Object>> getSearchMovie(String str) {
		List<LinkedHashMap<String, Object>> boAPI = boxOfficeAPI.getSearchMovie(str);
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		for (LinkedHashMap<String, Object> movie : boAPI) {
			Map<String, Object> input = new HashMap<>();
			
			String movieNm = (String) movie.get("movieNm");
			String movieCd = (String) movie.get("movieCd");	
			String movieNmEn = (String) movie.get("movieNmEn");
			String genreAlt = (String) movie.get("genreAlt");
			String prdtYear = (String) movie.get("prdtYear");
			input.put("movieNm", movieNm);
			input.put("movieCd", movieCd);
			input.put("genreAlt", genreAlt);
			input.put("movieNmEn", movieNmEn);
			
			List<LinkedHashMap<String, String>> nsAPI = naverSearchAPI.getSearchMovieInfo(str);
			
			movieNm = movieNm.replace(str, "<b>" + str + "</b>");
			
			for (LinkedHashMap<String, String> item : nsAPI) {
				//movieNm = "<b>" + movieNm + "</b>";
				if (item.get("title").equals(movieNm) && item.get("pubDate").equals(prdtYear)) {
					input.put("image", item.get("image"));
					
					String director = item.get("director");
					if (director.length() > 0) {
						director = director.substring(0, director.length() - 1);
					}
					input.put("director", director);
					
					String actor = item.get("actor");
					if (actor.length() > 0) {
						actor = actor.substring(0, actor.length() - 1);
					}
					input.put("actor", actor);
					break;
				}
			}
			if (input.get("image") != null) {
				list.add(input);
			}
		}
		return list;
	}
	
	public Map<String, Object> getDetailMovie(String movieCd) {
		LinkedHashMap<String, Object> result = boxOfficeAPI.getDetailMovie(movieCd);
		
		Map<String, Object> tempMap = new LinkedHashMap<>();
		List<LinkedHashMap<String, Object>> tempList = new ArrayList<>();
		//Map<String, Object> result = new LinkedHashMap<>();
		String movieNm = (String) result.get("movieNm");
		String prdtYear = (String) result.get("prdtYear");
		
		tempList = (List<LinkedHashMap<String, Object>>) result.get("nations");
		tempMap = tempList.get(0);
		result.put("nationNm", tempMap.get("nationNm"));

		tempList = (List<LinkedHashMap<String, Object>>) result.get("genres");
		tempMap = tempList.get(0);
		result.put("genreNm", tempMap.get("genreNm"));
		
		
		
		List<LinkedHashMap<String, String>> nsAPI = naverSearchAPI.getSearchMovieInfo(movieNm);
		
		movieNm = "<b>" + movieNm + "</b>";
		
		for (LinkedHashMap<String, String> item : nsAPI) {
			if (item.get("title").equals(movieNm) && item.get("pubDate").equals(prdtYear)) {
				result.put("image", item.get("image"));
				
				String director = item.get("director");
				if (director.length() > 0) {
					director = director.substring(0, director.length() - 1);
				}
				result.put("director", director);
				
				String actor = item.get("actor");
				if (actor.length() > 0) {
					actor = actor.substring(0, actor.length() - 1);
				}
				result.put("actor", actor);
				break;
			}
		}
		return result;
	}
}
