package com.MoVie.mypage.model;

import com.MoVie.user.model.User;

public class UserCardView {

	// 사용자 정보
	private User user;
	// 내가 본영화
	private int reviewCount;
	// 보고싶은 영화
	private int wishCount;
	// 보유티켓
	private int ticketCount;
	// 팔로우
	private int followCount;
	// 팔로잉
	private int followingCount;
	// 팔로우 됐는지 안 됐는지
	private boolean filedFollow;
	
	//
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getWishCount() {
		return wishCount;
	}
	public void setWishCount(int wishCount) {
		this.wishCount = wishCount;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getFollowingCount() {
		return followingCount;
	}
	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}
	public boolean isFiledFollow() {
		return filedFollow;
	}
	public void setFiledFollow(boolean filedFollow) {
		this.filedFollow = filedFollow;
	}
}
