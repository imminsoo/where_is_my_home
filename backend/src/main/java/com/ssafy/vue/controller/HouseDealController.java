package com.ssafy.vue.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.HouseSearchDealDto;
import com.ssafy.vue.model.HouseSearchDto;
import com.ssafy.vue.model.service.HouseDealService;

import io.swagger.annotations.*;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.POST }, maxAge = 6000)
@RestController
@RequestMapping("/deal")
@Api("사용자 컨트롤러  API V1")
public class HouseDealController {

	public static final Logger logger = LoggerFactory.getLogger(HouseDealController.class);

	@Autowired
	private HouseDealService service;

	@ApiOperation(value = "거래정보 가져오기", notes = "해당 아파트 코드의 거래내역을 반환한다.", response = Map.class)
	@GetMapping("/{aptCode}")
	public ResponseEntity<?> dealByAptCode(@PathVariable @ApiParam(value = "검색하려는 거래내역의 아파트 코드", required = true) int aptCode) {
		logger.info("HouseDealController - dealByAptCode({}) 호출", aptCode);
		try {
			List<HouseDealDto> list = service.getDeal(aptCode);
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("거래정보 불러오기 실패! : {}", e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "거래정보 가져오기", notes = "해당 아파트 코드의 거래내역을 반환한다.", response = Map.class)
	@GetMapping()
	@Deprecated
	public ResponseEntity<?> dealInFilter(
			@RequestParam(defaultValue = "", name = "aptCode") @ApiParam(value = "필터조건 중 아파트 코드") String aptCode, 
			@RequestParam(defaultValue = "", name = "gugunCode") @ApiParam(value = "필터조건 중 구군코드") String gugunCode, 
			@RequestParam(defaultValue = "", name = "aptName") @ApiParam(value = "필터조건 중 아파트 이름") String aptName, 
			@RequestParam(defaultValue = "", name = "minDealAmount") @ApiParam(value = "필터조건 중 최소 거래금액") String minDealAmount, 
			@RequestParam(defaultValue = "", name = "maxDealAmount") @ApiParam(value = "필터조건 최소 거래금액") String maxDealAmount, 
			@RequestParam(defaultValue = "", name = "minArea") @ApiParam(value = "필터조건 중 최소 전용면적") String minArea, 
			@RequestParam(defaultValue = "", name = "maxArea") @ApiParam(value = "필터조건 중 최대 전용면적") String maxArea, 
			@RequestParam(defaultValue = "", name = "minBuildYear") @ApiParam(value = "필터조건 중 최소 건축연도") String minBuildYear, 
			@RequestParam(defaultValue = "", name = "maxBuildYear") @ApiParam(value = "필터조건 중 최대 건축연도") String maxBuildYear
			) {
		HouseSearchDealDto filter=new HouseSearchDealDto(aptCode, gugunCode, aptName, minDealAmount, maxDealAmount, minArea, maxArea, minBuildYear, maxBuildYear);
		System.out.println(filter);
		logger.info("HouseDealController - dealInFilter() 호출\n{}", filter);
		try {
			
			List<HouseDealDto> list = service.getDealInFilter(filter);
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("거래정보 불러오기 실패! : {}", e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}