package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.HouseSearchDealDto;
import com.ssafy.vue.model.HouseSearchDto;

@Mapper
public interface HouseDealMapper {
	List<HouseDealDto> getDeal(int aptCode) throws SQLException;
	List<HouseDealDto> getDealInFilter(HouseSearchDealDto filter) throws SQLException;
}
