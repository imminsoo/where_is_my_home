package com.ssafy.vue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.CovidHospitalDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.service.CovidHospitalService;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/hospital")
@Api("선별진료소 컨트롤러  API V1")
public class CovidHospitalController {

	private static final Logger logger = LoggerFactory.getLogger(CovidHospitalController.class);

	// 개발 단계 중 화면에서 시도.구군 코드 가져오기 위해 넣은 코드. 나중에 아파트 지역 중심으로 선별진료소 검색하게 되면 불필요. 
	@Autowired
	private HouseMapService houseService;
	
	@Autowired
	private CovidHospitalService service;

	@ApiOperation(value = "전체 선별진료소 목록", notes = "등록된 모든 선별진료소 목록을 보여준다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<CovidHospitalDto>> getHospitals() throws Exception {
		logger.info("CovidHospitalController - getHospitals 호출");
		return new ResponseEntity<List<CovidHospitalDto>>(service.getHospitalList(), HttpStatus.OK);
	}

	// 주소 형식: /hospital/(구군코드)
	@ApiOperation(value = "지역 선별진료소 검색", notes = "지역의 선별진료소를 검색한다.", response = List.class)
	@GetMapping("/{gugunCode}")
	public ResponseEntity<?> getHospitalByGugun(
			@PathVariable("gugunCode") @ApiParam(value = "선별진료소를 검색할 지역의 구군코드", required = true) String gugunCode,
			HttpServletRequest request) throws Exception {
		logger.info("getHospitalByDongCode - 호출 : {}", gugunCode);
		try {
			List<CovidHospitalDto> list = service.getHospitalByGugun(gugunCode);
			if (!list.isEmpty())
				return new ResponseEntity<List<CovidHospitalDto>>(list, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error! :" + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	// 주소 형식: /hospital/?lat=(위도)&lng=(경도)
	@ApiOperation(value = "gps 좌표 근처 선별진료소 검색", notes = "gps 좌표를 중심으로 5km 이내의 선별진료소를 검색한다.", response = String.class)
	@GetMapping("/")
	public ResponseEntity<?> getHospitalByGPS(
			@RequestParam("lat") @ApiParam(value = "현재 GPS 좌표의 위도", required = true) String lat,
			@RequestParam("lng") @ApiParam(value = "현재 GPS 좌표의 경도", required = true) String lng) throws Exception {
		logger.info("getHospitalByGPS - 호출 : ({}, {})", lat, lng);
		try {
			List<CovidHospitalDto> list = service.getHospitalByGPS(lat, lng);
			if (!list.isEmpty())
				return new ResponseEntity<List<CovidHospitalDto>>(list, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error! :" + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}