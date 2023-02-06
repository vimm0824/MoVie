package com.MoVie.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.common.EncryptUtils;
import com.MoVie.user.bo.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, Object> addUser(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("nickname") String nickname
			) {
		Map<String, Object> result = new HashMap<>();
		
		password = EncryptUtils.md5(password);
		
		int row = userBO.addUser(loginId, password, name, nickname);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("result", "실패");
			result.put("errorMessage", "회원가입이 실패하였습니다. 문의 부탁드립니다.");
		}
		
		return result;
	}
	
	@GetMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId
			){
		Map<String, Object> result = new HashMap<>();
		
		int row = userBO.existLoginId(loginId);
		
		if (row > 0) { // 중복
			result.put("code", 1);
			result.put("result", false);
		} else { // 사용가능
			result.put("code", 1);
			result.put("result", true);
		}
		
		return result;
	}
}
