package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseSearchDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

@Mapper
public interface CodeMapper {
	List<SidoGugunCodeDto> sido() throws SQLException;
	List<SidoGugunCodeDto> gugun(String sido) throws SQLException;
	List<SidoGugunCodeDto> dong(String gugun) throws SQLException;
}
