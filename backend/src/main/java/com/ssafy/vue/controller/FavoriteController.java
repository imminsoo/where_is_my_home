package com.ssafy.vue.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.service.FavoriteService;
import com.ssafy.vue.model.service.JwtServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/favorites")
@Api("즐겨찾기 컨트롤러  API V1")
public class FavoriteController {

	@Autowired
	private JwtServiceImpl jwtService;

	private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String LIMIT = "limit";

	@Autowired
	private FavoriteService service;

	@ApiOperation(value = "즐겨찾기 등록", notes = "사용자가 등록한 즐겨찾기 정보를 등록한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/add")
	public ResponseEntity<String> addFavorite(
			@RequestBody @ApiParam(value = "등록할 즐겨찾기 정보.", required = true) FavoriteDto favoriteDto, HttpServletRequest request)
			throws Exception {
		logger.info("FavoriteController - addFavorite 호출 {}", favoriteDto.getAptCode());
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			String userid = jwtService.get(request.getHeader("access-token")).get("userid").toString();
			// 최대 즐겨찾기 개수 제한을 5개로 하기 위해 현재 사용자의 즐겨찾기 개수가 5개라면 더이상 등록 못하도록 LIMIT을 반환.
			if (service.getFavoritesCount(userid) == 5) {
				return new ResponseEntity<String>(LIMIT, HttpStatus.ACCEPTED);
			}
			//FavoriteDto favoriteDto = new FavoriteDto(aptCode.getAptCode());
			favoriteDto.setUserid(userid);
			if (service.addFavorite(favoriteDto)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			return new ResponseEntity<String>(FAIL, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "즐겨찾기 목록 보기", notes = "사용자의 즐겨찾기 목록을 가져온다. 사용자의 아이디 정보는 access-token에 있는 정보를 통해 추출한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<FavoriteDto>> getFavorites(HttpServletRequest request) throws Exception {
		logger.info("FavoriteController - getFavorites 호출");
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			String userid = jwtService.get(request.getHeader("access-token")).get("userid").toString();
			return new ResponseEntity<List<FavoriteDto>>(service.getFavoritesByUserId(userid), HttpStatus.OK);
		} else {
			logger.error("사용 불가능 토큰!!!");
			return new ResponseEntity<List<FavoriteDto>>(new ArrayList<FavoriteDto>(), HttpStatus.ACCEPTED);
		}
	}

	@ApiOperation(value = "즐겨찾기 삭제", notes = "넘겨받은 즐겨찾기 번호에 해당하는 정보를 삭제한다.")
	@DeleteMapping("/delete/{aptCode}")
	public ResponseEntity<Void> deleteFavorite(@PathVariable @ApiParam(value = "삭제할 즐겨찾기 정보", required = true) int aptCode,
			HttpServletRequest request) throws Exception {
		logger.info("FavoriteController - deleteFavorite 호출 {}", aptCode);
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			String userid = jwtService.get(request.getHeader("access-token")).get("userid").toString();
			try {
				service.deleteFavorite(userid,aptCode);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
	}
}