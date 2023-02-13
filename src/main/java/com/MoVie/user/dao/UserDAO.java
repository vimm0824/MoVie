package com.MoVie.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.MoVie.user.model.User;

@Repository
public interface UserDAO {

	public int insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("email") String email,
			@Param("name") String name,
			@Param("nickname") String nickname);
	
	public int existLoginId(String loginId);
	
	public User selecetUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
	
	public User selecetUserByLoginIdNameEmail(
			@Param("loginId") String loginId,
			@Param("name") String name,
			@Param("email") String email);
	
	public int updateUserByuserId(
			@Param("userId") int userId,
			@Param("password") String password);
	
	public User selectetUserById(int id);
	
	public void updataeUserById(
			@Param("id") int id,
			@Param("nickname") String nickname,
			@Param("profileUrl") String profileUrl);
}
