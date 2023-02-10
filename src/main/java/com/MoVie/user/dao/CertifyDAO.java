package com.MoVie.user.dao;

import org.apache.ibatis.annotations.Param;

import com.MoVie.user.model.Certify;

public interface CertifyDAO {

	public void insertCertifyCode(
			@Param("userId") int userId,
			@Param("code") String code);
	
	public Certify selecetCertifyByUserIdCode(
			@Param("userId") int userId,
			@Param("code") String code);
}
