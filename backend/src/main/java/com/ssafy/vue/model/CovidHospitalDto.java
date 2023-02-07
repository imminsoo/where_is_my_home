package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CovidHospitalDto : 선별진료소정보", description = "선별진료소의 상세 정보를 나타낸다.")
public class CovidHospitalDto {

	@ApiModelProperty(value = "진료소 번호")
	int hospitalNo;
	@ApiModelProperty(value = "선별진료소 이름")
	String hospitalName;
	@ApiModelProperty(value = "선별진료소가 위치한 지역의 동코드")
	String dongCode;
	@ApiModelProperty(value = "선별진료소의 GPS 좌표 중 위도 좌표")
	String lat;
	@ApiModelProperty(value = "선별진료소의 GPS 좌표 중 경도 좌표")
	String lng;
	@ApiModelProperty(value = "선별진료소의 GPS 좌표 중 위도 좌표")
	String phoneNum;
	@ApiModelProperty(value = "선별진료소의 담당 보건소")
	String clinicName;
	@ApiModelProperty(value = "담당 보건소의 전화번호")
	String clinicPhoneNum;
	@ApiModelProperty(value = "진료시간, 장애인편의시설 등의 기타정보")
	String info;
	@ApiModelProperty(value = "지도의 현위치에서 거리가 얼마나 되는지 계산하기 위해 사용하는 임시 거리 변수.") // 주의: 이 변수는 지도 주변 진료소를 검색할때만 값이 저장되는 변수입니다.
	double distance;

	public int getHospitalNo() {
		return hospitalNo;
	}

	public void setHospitalNo(int hospitalNo) {
		this.hospitalNo = hospitalNo;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicPhoneNum() {
		return clinicPhoneNum;
	}

	public void setClinicPhoneNum(String clinicPhoneNum) {
		this.clinicPhoneNum = clinicPhoneNum;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "CovidHospitalDto [hospitalNo=" + hospitalNo + ", hospitalName=" + hospitalName + ", dongCode="
				+ dongCode + ", lat=" + lat + ", lng=" + lng + ", phoneNum=" + phoneNum + ", clinicName=" + clinicName
				+ ", clinicPhoneNum=" + clinicPhoneNum + ", info=" + info + "]";
	}
}
