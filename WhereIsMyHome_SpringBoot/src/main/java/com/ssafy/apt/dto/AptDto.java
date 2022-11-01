package com.ssafy.apt.dto;

public class AptDto {

	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	private String aptName;
	private String floor;
	private String area;
	private String dealAmount;
	private String lat;
	private String lng;
	private String aptCode;

	public AptDto() {
	}

	public AptDto(String sidoName, String gugunName, String dongName, String jibun, String aptName, String floor, String area, String dealAmount,
			String lat, String lng, String aptCode) {
		super();
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.jibun = jibun;
		this.aptName = aptName;
		this.floor = floor;
		this.area = area;
		this.dealAmount = dealAmount;
		this.lat = lat;
		this.lng = lng;
		this.aptCode = aptCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	
	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
}