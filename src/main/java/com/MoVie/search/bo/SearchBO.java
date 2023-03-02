package com.MoVie.search.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.api.BoxOfficeAPI;
import com.MoVie.api.NaverSearchAPI;
import com.MoVie.review.bo.ReviewBO;

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
			String openDt = (String) movie.get("openDt");
			String year = "";
			if (openDt.length() > 0) {
				year = openDt.substring(0, 4);
			}
			String prdtYear = (String) movie.get("prdtYear");
			
			input.put("movieNm", movieNm);
			input.put("movieCd", movieCd);
			input.put("genreAlt", genreAlt);
			input.put("movieNmEn", movieNmEn);
			
			List<LinkedHashMap<String, String>> nsAPI = naverSearchAPI.getSearchMovieInfo(str);
			
			movieNm = movieNm.replace(str, "<b>" + str + "</b>");
			
			for (LinkedHashMap<String, String> item : nsAPI) {
				//movieNm = "<b>" + movieNm + "</b>";
				if (item.get("title").equals(movieNm) && (item.get("pubDate").equals(year) || item.get("pubDate").equals(prdtYear))) {
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
					//input
					break;
				}
			}
			if (input.get("image") == null) {
				input.put("image", "https://3.bp.blogspot.com/-WhBe10rJzG4/U4W-hvWvRCI/AAAAAAAABxg/RyWcixpgr3k/s1600/noimg.jpg");
			}
			list.add(input);
		}
		return list;
	}
	
	/**
	 * 영화 디테일
	 * @param movieCd
	 * @return
	 */
	public Map<String, Object> getDetailMovie(String movieCd) {
		LinkedHashMap<String, Object> result = boxOfficeAPI.getDetailMovie(movieCd);
		
		Map<String, Object> tempMap = new LinkedHashMap<>();
		List<LinkedHashMap<String, Object>> tempList = new ArrayList<>();
		
		String movieNm = (String) result.get("movieNm");
		String openDt = (String) result.get("openDt");
		
		String year = "";
		if (openDt.length() > 0) {
			String day = openDt.substring(6);
			String month = openDt.substring(4, 6);
			year = openDt.substring(0, 4);
			openDt = year + "/" + month + "/" + day;
			result.put("openDt", openDt);
		}
		String prdtYear = (String) result.get("prdtYear");
		
		// map 안에 list안에 map의 값이 필요해서 사용한 코드
		tempList = (List<LinkedHashMap<String, Object>>) result.get("nations");
		tempMap = tempList.get(0);
		result.put("nationNm", tempMap.get("nationNm"));

		tempList = (List<LinkedHashMap<String, Object>>) result.get("genres");
		tempMap = tempList.get(0);
		result.put("genreNm", tempMap.get("genreNm"));
		
		List<LinkedHashMap<String, String>> nsAPI = naverSearchAPI.getSearchMovieInfo(movieNm);
		
		movieNm = "<b>" + movieNm + "</b>";
		
		for (LinkedHashMap<String, String> item : nsAPI) {
			if (item.get("title").equals(movieNm) && (item.get("pubDate").equals(year) || item.get("pubDate").equals(prdtYear))) {
				result.put("image", item.get("image"));
				String director = item.get("director");
				if (director.length() > 0) {
					director = director.substring(0, director.length() - 1);
				}
				result.put("director", director);
				
				String actor = item.get("actor"	);
				if (actor.length() > 0) {
					actor = actor.substring(0, actor.length() - 1);
				}
				result.put("actor", actor);
				break;
			}
		}
		if (result.get("image") == null) {
			result.put("image", "https://2.bp.blogspot.com/-7fdJ0sJ_QrI/U4W-v8caIpI/AAAAAAAABxo/e7_hvfnNVFU/s1600/img.gif");
		}
		
		return result;
	}
}
