package com.MoVie.user;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MoVie.common.EncryptUtils;
import com.MoVie.user.bo.CertifyBO;
import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.Mail;
import com.MoVie.user.model.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	@Autowired
	private CertifyBO certifyBO;
	
	
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
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("nickname") String nickname
			) {
		Map<String, Object> result = new HashMap<>();
		
		password = EncryptUtils.md5(password);
		
		int row = userBO.addUser(loginId, password, email, name, nickname);
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
	
	@PostMapping("/find_password")
	public Map<String, Object> findPassword(
			@RequestParam("loginId") String loginId,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		Map<String, Object> result = new HashMap<>();
		
		// 정보일치하는 유저 찾기
		User user = userBO.getUserByLoginIdNameEmail(loginId, name, email);
		
		if (user != null) {
			Mail mail = certifyBO.createFinePasswordCode(user.getId(), user.getEmail());
			certifyBO.mailSend(mail);
			result.put("code", 1);
			result.put("userId", user.getId());
			result.put("result", "이메일에서 인증번호를 확인하여 인증해주세요.");
		} else {
			result.put("code", 400);
			result.put("result", "일치하는 회원정보가 없습니다.");
		}
		
		return result;
	}
	
	public Map<String, Object> certifyCode(
			@RequestParam("userId") int userId,
			@RequestParam("code") String code
			) {
		Map<String, Object> result = new HashMap<>();
		
		
		return result;
	}
}
