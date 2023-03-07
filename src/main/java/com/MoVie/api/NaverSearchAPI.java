package com.MoVie.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NaverSearchAPI {

	public List<LinkedHashMap<String, String>> getSearchMovieInfo(String search) {
		String jsonString = "";
		String clientId = "iq5JX8jZ3Z7TM_NGsJFU";
		String clientSecret = "OHbgiB1VGE";
		
		URI uri= UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/movie.json")
                .queryParam("query",search)
                .encode()
                .build()
                .toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		RequestEntity<Void> requestEntity = RequestEntity.get(uri)
				.header("X-Naver-Client-ID", clientId)
				.header("X-Naver-Client-Secret", clientSecret)
				.build();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
		
		jsonString = responseEntity.getBody();
		
		Map<String, Object> map = null;
		List<LinkedHashMap<String, String>> list = new ArrayList<>();
		
		try {
			map = new ObjectMapper().readValue(jsonString, Map.class);
			list = (List<LinkedHashMap<String, String>>) map.get("items");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
