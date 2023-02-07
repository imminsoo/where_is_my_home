package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.HouseSearchDealDto;
import com.ssafy.vue.model.HouseSearchDto;
import com.ssafy.vue.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	HouseDealMapper mapper;
	
	@Override
	public List<HouseDealDto> getDeal(int aptCode) throws SQLException {
		return mapper.getDeal(aptCode);
	}

	@Override
	public List<HouseDealDto> getDealInFilter(HouseSearchDealDto filter) throws SQLException {
		return mapper.getDealInFilter(filter);
	}
}
