package com.MoVie.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MoVie.user.model.User;

@Repository
public interface UserDAO {

	public int insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("nickname") String nickname);
	
	public int existLoginId(String loginId);
	
	public User selecetUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
}
