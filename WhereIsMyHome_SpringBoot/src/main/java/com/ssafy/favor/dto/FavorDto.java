package com.ssafy.favor.dto;

public class FavorDto{
	String favorNo;
	String userId;
	String dongCode;
	String sidoName;
	String gugunName;
	String dongName;

	public String getFavorNo() {
		return favorNo;
	}

	public void setFavorNo(String favorNo) {
		this.favorNo = favorNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
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

	@Override
	public String toString() {
		return "FavorDto [favorNo=" + favorNo + ", userId=" + userId + ", dongCode=" + dongCode + ", sidoName="
				+ sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName + "]";
	}
}
