package com.dto;

public class BeachDTO {

	private String sido_nm; //시도명
	private String gugun_nm; //구군명
	private String sta_nm; //정점명
	private String res_num; //조사차수
	private String res_loc; //조사지점
	private String res1; //대장균
	private String res2; //장구균
	private String res_yn; //적합여부
	private String res_year; // 검사년도
	private String res_date; //검사일자
	private String res_knd;//검사종류
	private String res_loc_detail; //조사지점 설명
	private String lat; //위도
	private String lon; //경도
	public BeachDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BeachDTO(String sido_nm, String gugun_nm, String sta_nm, String res_num, String res_loc, String res1,
			String res2, String res_yn, String res_year, String res_date, String res_knd, String res_loc_detail,
			String lat, String lon) {
		super();
		this.sido_nm = sido_nm;
		this.gugun_nm = gugun_nm;
		this.sta_nm = sta_nm;
		this.res_num = res_num;
		this.res_loc = res_loc;
		this.res1 = res1;
		this.res2 = res2;
		this.res_yn = res_yn;
		this.res_year = res_year;
		this.res_date = res_date;
		this.res_knd = res_knd;
		this.res_loc_detail = res_loc_detail;
		this.lat = lat;
		this.lon = lon;
	}
	@Override
	public String toString() {
		return "BeachDTO [sido_nm=" + sido_nm + ", gugun_nm=" + gugun_nm + ", sta_nm=" + sta_nm + ", res_num=" + res_num
				+ ", res_loc=" + res_loc + ", res1=" + res1 + ", res2=" + res2 + ", res_yn=" + res_yn + ", res_year="
				+ res_year + ", res_date=" + res_date + ", res_knd=" + res_knd + ", res_loc_detail=" + res_loc_detail
				+ ", lat=" + lat + ", lon=" + lon + "]";
	}
	public String getSido_nm() {
		return sido_nm;
	}
	public void setSido_nm(String sido_nm) {
		this.sido_nm = sido_nm;
	}
	public String getGugun_nm() {
		return gugun_nm;
	}
	public void setGugun_nm(String gugun_nm) {
		this.gugun_nm = gugun_nm;
	}
	public String getSta_nm() {
		return sta_nm;
	}
	public void setSta_nm(String sta_nm) {
		this.sta_nm = sta_nm;
	}
	public String getRes_num() {
		return res_num;
	}
	public void setRes_num(String res_num) {
		this.res_num = res_num;
	}
	public String getRes_loc() {
		return res_loc;
	}
	public void setRes_loc(String res_loc) {
		this.res_loc = res_loc;
	}
	public String getRes1() {
		return res1;
	}
	public void setRes1(String res1) {
		this.res1 = res1;
	}
	public String getRes2() {
		return res2;
	}
	public void setRes2(String res2) {
		this.res2 = res2;
	}
	public String getRes_yn() {
		return res_yn;
	}
	public void setRes_yn(String res_yn) {
		this.res_yn = res_yn;
	}
	public String getRes_year() {
		return res_year;
	}
	public void setRes_year(String res_year) {
		this.res_year = res_year;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}
	public String getRes_knd() {
		return res_knd;
	}
	public void setRes_knd(String res_knd) {
		this.res_knd = res_knd;
	}
	public String getRes_loc_detail() {
		return res_loc_detail;
	}
	public void setRes_loc_detail(String res_loc_detail) {
		this.res_loc_detail = res_loc_detail;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}

}