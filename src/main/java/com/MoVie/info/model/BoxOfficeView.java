package com.MoVie.info.model;

import java.util.List;

import com.MoVie.api.model.NaverSearch;

public class BoxOfficeView {

	private String movieCd;
	private String movieNm;
	//private String image;
	private String rank;
	
	private List<NaverSearch> items;
	
	public List<NaverSearch> getItems() {
		return items;
	}
	public void setItems(List<NaverSearch> items) {
		this.items = items;
	}
	//
	public String getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}
