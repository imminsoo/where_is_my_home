package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.CovidHospitalDto;
import com.ssafy.vue.model.mapper.CovidHospitalMapper;

@Service
public class CovidHospitalServiceImpl implements CovidHospitalService {
	
	@Autowired
	CovidHospitalMapper mapper;

	@Override
	public List<CovidHospitalDto> getHospitalByGPS(String lat, String lng) throws Exception {
		Map<String, String> pos=new HashMap<>();
		pos.put("lat", lat);
		pos.put("lng", lng);
		return mapper.getHospitalByGPS(pos);
	}

	@Override
	public List<CovidHospitalDto> getHospitalByName(String name) throws Exception {
		return mapper.getHospitalByName(name);
	}

	@Override
	public List<CovidHospitalDto> getHospitalList() throws Exception {
		return mapper.getHospitalList();
	}

	@Override
	public List<CovidHospitalDto> getHospitalByDong(String dongCode) throws Exception {
		return mapper.getHospitalByCode(dongCode.substring(0, 8));
	}

	@Override
	public List<CovidHospitalDto> getHospitalByGugun(String dongCode) throws Exception {
		System.out.println(dongCode.substring(0, 5));
		return mapper.getHospitalByCode(dongCode.substring(0, 5));
	}

}
