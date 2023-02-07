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
import com.MoVie.user.model.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	
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
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
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
			result.put("errorMessage", "DB 실패, 문의 부탁드립니다.");
		}
		
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		password = EncryptUtils.md5(password);
		
		User user = userBO.getUserByLoginIdPassword(loginId, password);
		
		if (user != null) {
			result.put("code", 1);
			result.put("result", "로그인 성공");
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("loginId", user.getLoginId());
			session.setAttribute("nickname", user.getNickname());
		} else {
			result.put("code", 1);
			result.put("result", "아이디와 비밀번호가 잘못되었습니다.");
		}
		
		return result;
	}
	
}
