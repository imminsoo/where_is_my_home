package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FavoriteDto : 즐겨찾기 정보", description = "즐겨찾기 정보를 나타낸다.")
public class FavoriteDto {
	@ApiModelProperty(value = "즐겨찾기 등록 번호(DB에서 데이터 구분을 위한 데이터일 뿐 의미 없음)")
	private int no;
	@ApiModelProperty(value = "즐겨찾기를 등록한 사용자 아이디")
	private String userid;
	@ApiModelProperty(value = "즐겨찾기 등록한 아파트 코드")
	private int aptCode;
	@ApiModelProperty(value = "즐겨찾기 등록한 아파트의 이름")
	private HouseInfoDto houseInfo;
	
	public FavoriteDto() {
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public HouseInfoDto getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfoDto houseInfo) {
		this.houseInfo = houseInfo;
	}
	
	
}