package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.SidoGugunCodeDto;

public interface CodeService {
	List<SidoGugunCodeDto> sido() throws Exception;
	List<SidoGugunCodeDto> gugun(String sido) throws Exception;
	List<SidoGugunCodeDto> dong(String gugun) throws Exception;
}
