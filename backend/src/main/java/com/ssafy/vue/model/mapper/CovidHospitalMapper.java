package com.ssafy.vue.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.CovidHospitalDto;

@Mapper
public interface CovidHospitalMapper {
	List<CovidHospitalDto> getHospitalByGPS(Map<String, String> pos) throws Exception;
	List<CovidHospitalDto> getHospitalByName(String name) throws Exception;
	List<CovidHospitalDto> getHospitalList() throws Exception;
	List<CovidHospitalDto> getHospitalByCode(String dongCode) throws Exception;
}