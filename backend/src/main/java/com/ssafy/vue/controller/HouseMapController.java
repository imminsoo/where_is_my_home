package com.ssafy.vue.controller;

import java.util.List;

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

import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.HouseSearchDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@ApiOperation(value = "동별 아파트 정보", notes = "해당 읍/면/동에 있는 모든 아파트 리스트를 반환한다. 우리 프로젝트에서는 사용하지 않는 함수.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구/군별 아파트 정보", notes = "해당 구/군에 있는 모든 아파트 리스트를 반환한다.", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> aptDong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 상세정보", notes = "아파트 코드에 대응하는 아파트 상세정보를 반환한다.", response = HouseInfoDto.class)
	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<HouseInfoDto> aptInAptCode(@PathVariable @ApiParam(value="아파트 코드", required = true) int aptCode) throws Exception {
		return new ResponseEntity<HouseInfoDto>(haHouseMapService.getAptInAptCode(aptCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "필터 조건에 맞는 아파트 정보 검색", notes="사용자가 지정한 아파트 조건에 맞는 아파트 리스트를 반환한다.", response=List.class)
	@GetMapping("/search")
	// 검색 필터 기능은 나중에 검색 조건을 어떻게 넘겨줄 것인지 결정되는 대로 RequestParam 또는 RequestBody로 바꾸는걸로 하고 일단은 스켈레톤 코드입니다.
	public ResponseEntity<List<HouseInfoDto>> aptSearch(
			@RequestParam(defaultValue = "", name = "gugunCode") @ApiParam(value = "필터조건 중 구군코드") String gugunCode, 
			@RequestParam(defaultValue = "", name = "aptName") @ApiParam(value = "필터조건 중 아파트 이름") String aptName, 
			@RequestParam(defaultValue = "", name = "minDealAmount") @ApiParam(value = "필터조건 중 최소 거래금액") String minDealAmount, 
			@RequestParam(defaultValue = "", name = "maxDealAmount") @ApiParam(value = "필터조건 최소 거래금액") String maxDealAmount, 
			@RequestParam(defaultValue = "", name = "minArea") @ApiParam(value = "필터조건 중 최소 전용면적") String minArea, 
			@RequestParam(defaultValue = "", name = "maxArea") @ApiParam(value = "필터조건 중 최대 전용면적") String maxArea, 
			@RequestParam(defaultValue = "", name = "minBuildYear") @ApiParam(value = "필터조건 중 최소 건축연도") String minBuildYear, 
			@RequestParam(defaultValue = "", name = "maxBuildYear") @ApiParam(value = "필터조건 중 최대 건축연도") String maxBuildYear) throws Exception {
		HouseSearchDto searchDto = new HouseSearchDto(gugunCode, aptName, minDealAmount, maxDealAmount, minArea, maxArea, minBuildYear, maxBuildYear); 
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInFilter(searchDto), HttpStatus.OK);
	}
}
