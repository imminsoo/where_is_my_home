package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HousedDealDto : 아파트 거래내역 정보", description = "아파트 거래내역의 상세 정보를 나타낸다.")
public class HouseDealDto {
	@ApiModelProperty(value = "아파트 거래 번호")
	private int no;
	@ApiModelProperty(value = "아파트 코드")
	private int aptCode;
	@ApiModelProperty(value = "아파트 이름")
	private String aptName;
	@ApiModelProperty(value = "아파트 주소의 동코드")
	private String dongCode;
	@ApiModelProperty(value = "아파트 거래 금액")
	private String dealAmount;
	@ApiModelProperty(value = "아파트 거래 연도")
	private int dealYear;
	@ApiModelProperty(value = "아파트 거래 월")
	private int dealMonth;
	@ApiModelProperty(value = "아파트 거래 일")
	private int dealDay;
	@ApiModelProperty(value = "아파트 법정동")
	private String area;
	@ApiModelProperty(value = "거래된 아파트 매물의 층")
	private String floor;
	@ApiModelProperty(value = "해제사유")
	private String type;
	@ApiModelProperty(value = "뭔지 모름")
	private String rentMoney;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getAptCode() {
		return aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}

	@Override
	public String toString() {
		return "HouseDealDto [no=" + no + ", aptCode=" + aptCode + ", dealAmount=" + dealAmount + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor
				+ ", type=" + type + "]";
	}
}
