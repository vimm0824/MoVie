package com.MoVie.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.MoVie.api.model.DailyBoxOffice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BoxOfficeAPI {

	public List<LinkedHashMap<String, String>> getDailyBoxOffice() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, -1);
		String date = sdf.format(day.getTime());
		
		String jsonString = "";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);
         
        String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
        String key = "0a3d75ef1d272ded4accb0dcb474129a";
		
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url +"?key=" + key + "&targetDt=" + date).build();
        
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
			jsonString = mapper.writeValueAsString(resultMap.getBody());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        Map<String, Object> map = null;
//		List<DailyBoxOffice> result = new ArrayList<>(); 
		List<LinkedHashMap<String, String>> result = new ArrayList<>(); 
		
		try {
			map = new ObjectMapper().readValue(jsonString, Map.class);
			String mapKey = "";
			for (String menu : map.keySet()) {
				mapKey = menu;
			}
			map = (Map<String, Object>) map.get(mapKey);
			result = (List<LinkedHashMap<String, String>>)map.get("dailyBoxOfficeList");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        return result;
	}
	
	public List<LinkedHashMap<String, Object>> getSearchMovie(String str) {
			
			String jsonString = "";
			
			RestTemplate restTemplate = new RestTemplate();
			
			HttpHeaders header = new HttpHeaders();
	        HttpEntity<?> entity = new HttpEntity<>(header);
	         
	        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
	        String key = "0a3d75ef1d272ded4accb0dcb474129a";
			
	        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url +"?key=" + key + "&movieNm=" + str).build();
	        
	        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
	        
	        ObjectMapper mapper = new ObjectMapper();
	        try {
				jsonString = mapper.writeValueAsString(resultMap.getBody());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	        
	        Map<String, Object> map = null;
			List<LinkedHashMap<String, Object>> result = new ArrayList<>(); 
			
			try {
				map = new ObjectMapper().readValue(jsonString, Map.class);
				String mapKey = "";
				for (String menu : map.keySet()) {
					mapKey = menu;
				}
				map = (Map<String, Object>) map.get(mapKey);
				result = (List<LinkedHashMap<String, Object>>)map.get("movieList");
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
	        return result;
		}
	
	public LinkedHashMap<String, Object> getDetailMovie(String movieCd) {
		String jsonString = "";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);
         
        String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";
        String key = "0a3d75ef1d272ded4accb0dcb474129a";
		
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url +"?key=" + key + "&movieCd=" + movieCd).build();
        
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
        
        ObjectMapper mapper = new ObjectMapper();
        
        try {
			jsonString = mapper.writeValueAsString(resultMap.getBody());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Map<String, Object> map = null;
		Map<String, Object> result = new LinkedHashMap<>();
		
		try {
			map = new ObjectMapper().readValue(jsonString, Map.class);
			String mapKey = "";
			for (String menu : map.keySet()) {
				mapKey = menu;
			}
			map = (Map<String, Object>) map.get(mapKey);
			result = (LinkedHashMap<String, Object>)map.get("movieInfo");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return (LinkedHashMap<String, Object>) result;
	}
}
