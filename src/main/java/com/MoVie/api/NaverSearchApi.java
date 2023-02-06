package com.MoVie.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.devtools.restart.server.DefaultSourceDirectoryUrlFilter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.MoVie.api.model.NaverSearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class NaverSearchApi {

	@GetMapping("/search_api")
	public String searchApi(
			@RequestParam("search") String search,
			Model model
			) {
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
		List<NaverSearch> list = new ArrayList<>();
		try {
			map = new ObjectMapper().readValue(jsonString, Map.class);
			list = (List<NaverSearch>) map.get("items");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
		model.addAttribute("result", list);
		//return jsonString;
		return "test/searchTest";
	}
}
