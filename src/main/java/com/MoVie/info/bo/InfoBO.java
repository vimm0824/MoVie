package com.MoVie.info.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoVie.api.BoxOfficeAPI;
import com.MoVie.api.NaverSearchAPI;
import com.MoVie.api.model.DailyBoxOffice;
import com.MoVie.api.model.NaverSearch;
import com.MoVie.info.model.BoxOfficeView;

@Service
public class InfoBO {

	@Autowired
	private BoxOfficeAPI boxOfficeAPI;
	@Autowired
	private NaverSearchAPI naverSearchAPI;
	
	public List<Map<String, Object>> getBoxOfficeView() {
		List<LinkedHashMap<String, String>> boAPI = boxOfficeAPI.getDailyBoxOffice();
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		for (LinkedHashMap<String, String> movie : boAPI) {
			//LinkedHashMap<String, String> map = null;
			Map<String, Object> input = new HashMap<>();
			
			String movieNm = movie.get("movieNm");
			String movieCd = movie.get("movieCd");
			String rank = movie.get("rank");
			input.put("movieNm", movieNm);
			input.put("movieCd", movieCd);
			input.put("rank", rank);
			
			List<LinkedHashMap<String, String>> nsAPI = naverSearchAPI.getSearchMovieInfo(movieNm);
			
			movieNm = "<b>" + movieNm + "</b>";
			for (LinkedHashMap<String, String> item : nsAPI) {
				if (item.get("title").equals(movieNm)) {
					input.put("image", item.get("image"));
					break;
				}
			}
			
			list.add(input);
		}
		return list;
	}
	
}
