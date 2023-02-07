package com.ssafy.vue.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.mapper.CodeMapper;

@Service
public class CodeServiceImpl implements CodeService {
	
	@Autowired
	CodeMapper mapper;

	@Override
	public List<SidoGugunCodeDto> sido() throws Exception {
		return mapper.sido();
	}

	@Override
	public List<SidoGugunCodeDto> gugun(String sido) throws Exception {
		return mapper.gugun(sido);
	}

	@Override
	public List<SidoGugunCodeDto> dong(String gugun) throws Exception {
		return mapper.dong(gugun);
	}
}
