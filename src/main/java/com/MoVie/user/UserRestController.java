package com.MoVie.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MoVie.common.EncryptUtils;
import com.MoVie.user.bo.CertifyBO;
import com.MoVie.user.bo.UserBO;
import com.MoVie.user.model.Certify;
import com.MoVie.user.model.Mail;
import com.MoVie.user.model.User;

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
		
		User user = userBO.existLoginId(loginId);
		
//		int row =  0;
//		if (user != null) {
//			row = user.getId();
//		}
		if (user != null) { // 중복
			result.put("code", 1);
			result.put("result", false);
			result.put("userId", user.getId());
		} else { // 없는 아이디
			result.put("code", 1);
			result.put("result", true);
			result.put("userId", 0);
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
			result.put("code", 500);
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
	
	@PostMapping("/certify_code")
	public Map<String, Object> certifyCode(
			@RequestParam("userId") int userId,
			@RequestParam("code") String code
			) {
		Map<String, Object> result = new HashMap<>();
		
		Certify certify = certifyBO.getCertifyByUserIdCode(userId, code);
		if (certify != null) {
			result.put("code", 1);
			result.put("result", "인증성공, 비밀번호를 바꿔주세요!");
		} else {
			result.put("code", 500);
			result.put("result", "인증실패, 다시 시도해주세요.");
		}
		
		return result;
	}
	
	@PostMapping("/change_password")
	public Map<String, Object> changePassword(
			@RequestParam("userId") int userId,
			@RequestParam("password") String password
			) {
		Map<String, Object> result = new HashMap<>();
		
		password = EncryptUtils.md5(password);
		
		int row = userBO.updateUserByuserId(userId, password);
		
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "비밀번호가 변경되었습니다. 다시 로그인 해주세요.");
		} else {
			result.put("code", 500);
			result.put("result", "비밀번호가 변경에 실패하였습니다. 다시 시 해주세요.");
		}
		
		return result;
	}
	
	@PostMapping("/update_user")
	public Map<String, Object> updateUser(
			@RequestParam(value="userId", required=false) Integer userId,
			@RequestParam(value="nickname", required=false) String nickname,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpSession session
			) {
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("result", "로그인후 이용해주세요.");
			return result;
		}
		
		userBO.updataeUserById(userId, nickname, file);
		
		result.put("code", 1);
		result.put("result", "수정이 완료되었습니다.");
		
		if (nickname.isEmpty() == false) {
			session.setAttribute("nickname", nickname);
		}
		
		return result;
	}
	
}
