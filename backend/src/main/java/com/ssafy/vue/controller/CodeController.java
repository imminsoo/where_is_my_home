package com.ssafy.vue.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.service.BoardService;
import com.ssafy.vue.model.service.CodeService;
import com.ssafy.vue.model.service.JwtServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/code")
@Api("지역코드 컨트롤러  API V1")
public class CodeController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@Autowired
	CodeService service;

	// 주소 형식: /code/sido
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("CodeController - sido() 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(service.sido(), HttpStatus.OK);
	}
	
	// 주소 형식: /code/gugun?sido=(시도코드)
	@ApiOperation(value = "구군 정보", notes = "현재 시/도안에 있는 구/군의 리스트를 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("CodeController - gugun({}) 호출",sido);
		return new ResponseEntity<List<SidoGugunCodeDto>>(service.gugun(sido), HttpStatus.OK);
	}
	
	// 필요한 경우에만 사용하세요.
	// 주소 형식: /code/dong?gugun=(구군코드)
	@ApiOperation(value = "읍면동 정보", notes = "현재 지역 안의 읍/면/동 리스트를 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<SidoGugunCodeDto>> dong(@RequestParam("gugun") @ApiParam(value = "구군코드.", required = true) String gugun) throws Exception {
		logger.info("CodeController - dong({}) 호출",gugun);
		return new ResponseEntity<List<SidoGugunCodeDto>>(service.dong(gugun), HttpStatus.OK);
	}
}