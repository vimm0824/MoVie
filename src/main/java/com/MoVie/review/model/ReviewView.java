package com.MoVie.review.model;

import com.MoVie.user.model.User;

public class ReviewView {

	private Review review;
	private User user;
	
	//
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
