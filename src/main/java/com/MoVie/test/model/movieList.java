package com.MoVie.test.model;

import java.util.Map;

public class movieList {

	private String movieCd;
	private String movieNm;
	private String movieNmEn;
	private String prdtYear;
	private String openDt;
	private String typeNm;
	private String prdtStatNm;
	private String nationAlt;
	private String genreAlt;
	private String repNationNm;
	private String repGenreNm;
    private Map<String, Map<String, String>> directors;
    private Map<String, Map<String, String>> companys;
    
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
	public String getMovieNmEn() {
		return movieNmEn;
	}
	public void setMovieNmEn(String movieNmEn) {
		this.movieNmEn = movieNmEn;
	}
	public String getPrdtYear() {
		return prdtYear;
	}
	public void setPrdtYear(String prdtYear) {
		this.prdtYear = prdtYear;
	}
	public String getOpenDt() {
		return openDt;
	}
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
	public String getTypeNm() {
		return typeNm;
	}
	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}
	public String getPrdtStatNm() {
		return prdtStatNm;
	}
	public void setPrdtStatNm(String prdtStatNm) {
		this.prdtStatNm = prdtStatNm;
	}
	public String getNationAlt() {
		return nationAlt;
	}
	public void setNationAlt(String nationAlt) {
		this.nationAlt = nationAlt;
	}
	public String getGenreAlt() {
		return genreAlt;
	}
	public void setGenreAlt(String genreAlt) {
		this.genreAlt = genreAlt;
	}
	public String getRepNationNm() {
		return repNationNm;
	}
	public void setRepNationNm(String repNationNm) {
		this.repNationNm = repNationNm;
	}
	public String getRepGenreNm() {
		return repGenreNm;
	}
	public void setRepGenreNm(String repGenreNm) {
		this.repGenreNm = repGenreNm;
	}
	public Map<String, Map<String, String>> getDirectors() {
		return directors;
	}
	public void setDirectors(Map<String, Map<String, String>> directors) {
		this.directors = directors;
	}
	public Map<String, Map<String, String>> getCompanys() {
		return companys;
	}
	public void setCompanys(Map<String, Map<String, String>> companys) {
		this.companys = companys;
	}
      
}
