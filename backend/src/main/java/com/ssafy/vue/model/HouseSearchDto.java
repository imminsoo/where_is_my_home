package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseSearchDto : 아파트 거래내역 검색 조건", description = "아파트의 거래내역을 검색할 시 지정한 검색조건을 담은 객체이다.")
public class HouseSearchDto {

	@ApiModelProperty(value = "구군 코드")
	String gugunCode;
	@ApiModelProperty(value = "아파트 이름")
	String aptName;
	@ApiModelProperty(value = "거래 금액 최소 범위")
	String minDealAmount;
	@ApiModelProperty(value = "거래 금액 최대 범위")
	String maxDealAmount;
	@ApiModelProperty(value = "전용 면적 최소 범위")
	String minArea;
	@ApiModelProperty(value = "전용 면적 최대 범위")
	String maxArea;
	@ApiModelProperty(value = "건축 연도 최소 범위")
	String minBuildYear;
	@ApiModelProperty(value = "건축 연도 최대 범위")
	String maxBuildYear;
	
	public HouseSearchDto() {}

	// 이 생성자는 HouseDealController의 dealInFilter에서 사용되므로 절대 삭제 금지!!
	public HouseSearchDto(String gugunCode, String aptName, String minDealAmount, String maxDealAmount,
			String minArea, String maxArea, String minBuildYear, String maxBuildYear) {
		this.gugunCode = gugunCode;
		this.aptName = aptName;
		this.minDealAmount = minDealAmount;
		this.maxDealAmount = maxDealAmount;
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.minBuildYear = minBuildYear;
		this.maxBuildYear = maxBuildYear;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getMinDealAmount() {
		return minDealAmount;
	}

	public void setMinDealAmount(String minDealAmount) {
		this.minDealAmount = minDealAmount;
	}

	public String getMaxDealAmount() {
		return maxDealAmount;
	}

	public void setMaxDealAmount(String maxDealAmount) {
		this.maxDealAmount = maxDealAmount;
	}

	public String getMinArea() {
		return minArea;
	}

	public void setMinArea(String minArea) {
		this.minArea = minArea;
	}

	public String getMaxArea() {
		return maxArea;
	}

	public void setMaxArea(String maxArea) {
		this.maxArea = maxArea;
	}

	public String getMinBuildYear() {
		return minBuildYear;
	}

	public void setMinBuildYear(String minBuildYear) {
		this.minBuildYear = minBuildYear;
	}

	public String getMaxBuildYear() {
		return maxBuildYear;
	}

	public void setMaxBuildYear(String maxBuildYear) {
		this.maxBuildYear = maxBuildYear;
	}

	@Override
	public String toString() {
		return "검색조건: [구군코드=" + gugunCode + ", 아파트이름=" + aptName + ", 최소거래금액=" + minDealAmount
				+ ", 최대거래금액=" + maxDealAmount + ", 최소전용면적=" + minArea + ", 최대전용면적=" + maxArea + ", 최소건축연도="
				+ minBuildYear + ", 최대건축연도=" + maxBuildYear + "]";
	}

}
