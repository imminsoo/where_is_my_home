package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseSearchDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInFilter(HouseSearchDto searchDto) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInFilter(searchDto);
	}

	@Override
	public HouseInfoDto getAptInAptCode(int aptCode) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInAptCode(aptCode);
	}
}
