package com.MoVie.user.bo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.MoVie.user.dao.CertifyDAO;
import com.MoVie.user.model.Certify;
import com.MoVie.user.model.Mail;

@Component
@Service
public class CertifyBO {

	@Autowired
	private CertifyDAO certifyDAO;
	
	private JavaMailSender mailSender;
	private static final String fromAddress = "vimm0824@naver.com";
	
	@Autowired
	public void MailUtil(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	// 메일 보내기
	public Mail createFinePasswordCode(int userId, String email) {
		String code = getCertifyCode();
		Mail mail = new Mail();
		
		mail.setAddress(email);
		mail.setTitle("MoVie : 비밀번호 인증 메일");
		mail.setMessage("안녕하세요. MoVie 비밀번호 찾기 인증번호 안내 관련 이메일 입니다. 회원님의 인증번호 "
				+ code + " 입니다. 페이지로 돌아가서 인증번호를 입력해주세요.");
		
		addCertifyCode(userId, code);
		return mail;
	}
	
	// 인증번호 DB에 쌓기
	public void addCertifyCode(int userId, String code) {
		certifyDAO.insertCertifyCode(userId, code);
	}
	
	// 인증번호 만들기
	public String getCertifyCode(){
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		
		String code = "";
		
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			code += charSet[idx];
		}
		return code;
	}
	
	// 메일 보내기
	public void mailSend(Mail mail) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(mail.getAddress());
	    message.setFrom(CertifyBO.fromAddress);
	    message.setSubject(mail.getTitle());
	    message.setText(mail.getMessage());

	    mailSender.send(message);
	}
	
	/*
	 * 3분안에 인증
	 */
	public Certify getCertifyByUserIdCode(int userId, String code) {
		// BO 불러오는 시간
		Date now = new Date();
		Certify certify = certifyDAO.selecetCertifyByUserIdCode(userId, code);
		
		if (certify != null) {
			// 인증번호 생성시간
			Date createdAt = certify.getCreatedAt();
			
			// BO - 생성 < 5분 이여야 OK
			long minLong = (now.getTime() - createdAt.getTime()) / 1000;
			int min = Long.valueOf(minLong).intValue();
			if (min < 180) {
				return certify;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
