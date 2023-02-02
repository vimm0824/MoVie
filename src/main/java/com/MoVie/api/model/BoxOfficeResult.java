package com.MoVie.api.model;

import java.util.List;

public class BoxOfficeResult {

	private String boxofficeType;
    private String showRange;
    private List<DailyBoxOffice> dailyBoxOfficeList;
    
    //
	public String getBoxofficeType() {
		return boxofficeType;
	}
	public void setBoxofficeType(String boxofficeType) {
		this.boxofficeType = boxofficeType;
	}
	public String getShowRange() {
		return showRange;
	}
	public void setShowRange(String showRange) {
		this.showRange = showRange;
	}
	public List<DailyBoxOffice> getDailyBoxOfficeList() {
		return dailyBoxOfficeList;
	}
	public void setDailyBoxOfficeList(List<DailyBoxOffice> dailyBoxOfficeList) {
		this.dailyBoxOfficeList = dailyBoxOfficeList;
	}
}
