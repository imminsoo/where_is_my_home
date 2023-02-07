package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.CovidHospitalDto;

public interface CovidHospitalService {
	List<CovidHospitalDto> getHospitalByGPS(String lat, String lng) throws Exception;
	List<CovidHospitalDto> getHospitalByName(String name) throws Exception;
	List<CovidHospitalDto> getHospitalList() throws Exception;
	List<CovidHospitalDto> getHospitalByDong(String dongCode) throws Exception;
	List<CovidHospitalDto> getHospitalByGugun(String dongCode) throws Exception;
	
	// 좀 더 폼나게 짠다면..
	// getHospitalList(String dongCode, int option) 식으로 해서
	// if(dongCode !=null){
	// if(option == 1) -> 읍면동 검색
	// else if(option ==2) -> 구군 검색 으로 가능할지도.
	// 근데 귀찮기도 하고 직관적이지 않음 그냥 이대로 GO
}