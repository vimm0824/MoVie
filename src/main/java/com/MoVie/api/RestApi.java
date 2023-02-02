package com.MoVie.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.MoVie.api.model.DailyBoxOffice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RestApi {

	@GetMapping("/get")
	public String callApi(
			@RequestParam("date") String date,
			Model model
			) {
		Map<String, Object> result = new HashMap<>();

		String jsonString = "";
		
		
		try {
	          RestTemplate restTemplate = new RestTemplate();
	          
	          HttpHeaders header = new HttpHeaders();
	          HttpEntity<?> entity = new HttpEntity<>(header);
	          String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
	          String key = "0a3d75ef1d272ded4accb0dcb474129a";
	          //String date = "20230120";

	          UriComponents uri = UriComponentsBuilder.fromHttpUrl(url +"?key=" + key + "&targetDt=" + date).build();
	          
	          //restTemplate.getForEntity(url, null, header);
	          
	          //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
	          ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
	          result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
	          result.put("header", resultMap.getHeaders()); //헤더 정보 확인
	          result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
	          
	          //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
	          ObjectMapper mapper = new ObjectMapper();
	          jsonString = mapper.writeValueAsString(resultMap.getBody());

	      } catch (HttpClientErrorException | HttpServerErrorException e) {
	          result.put("statusCode", e.getRawStatusCode());
	          result.put("body"  , e.getStatusText());
	          System.out.println(e.toString());

	      } catch (Exception e) {
	          result.put("statusCode", "500");
	          result.put("body"  , "excpetion오류");
	          System.out.println(e.toString());
	      }
		
		//JSONParser parser = new JSONParser();
		//JSONObject jsonObject = new JSONObject();
		Map<String, Object> map = null;
		List<DailyBoxOffice> ob = new ArrayList<>(); 
		
		//Object ob = null;
		//dailyBoxOfficeList
		try {
			map = new ObjectMapper().readValue(jsonString, Map.class);
			String mapKey = "";
			for (String menu : map.keySet()) {
				mapKey = menu;
			}
			map = (Map<String, Object>) map.get(mapKey);
			ob = (List<DailyBoxOffice>)map.get("dailyBoxOfficeList");
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("result", ob);
		//return jsonString;
		return "test/test";
	}
}
