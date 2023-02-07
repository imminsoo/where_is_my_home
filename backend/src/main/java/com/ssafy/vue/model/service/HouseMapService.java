package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseSearchDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInFilter(HouseSearchDto searchDto) throws Exception;
	HouseInfoDto getAptInAptCode(int aptCode) throws Exception;
}
