package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.service.JwtServiceImpl;
import com.ssafy.vue.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.POST }, maxAge = 6000)
@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//        logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//                로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원가입", notes = "넘겨받은 회원정보를 바탕으로 회원을 DB에 등록한다.")
	@PostMapping("/register")
	public ResponseEntity<?> register(
			@RequestBody @ApiParam(value = "회원가입 시 등록한 회원정보(아이디, 사용자명, 비밀번호, 이메일). 가입일은 DB에서 current_timestamp를 하므로 넣지 않는다.", required = true) MemberDto memberDto) {
		try {
			memberService.register(memberDto);
			MemberDto test = memberService.userInfo(memberDto.getUserid());
			if (test != null) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "회원정보 수정", notes = "넘겨받은 회원정보를 바탕으로 회원 정보를 수정한다.", response = Map.class)
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> editInfo(
			@RequestBody @ApiParam(value = "수정할 회원의 정보", required = true) MemberDto memberDto,
			HttpServletRequest request) {
//        logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			logger.info("안녕안녕!! {}", jwtService.get(request.getHeader("access-token")).get("userid").toString());
			try {
//                로그인 사용자 정보.
				MemberDto updatedMember = memberService.editInfo(memberDto);
				resultMap.put("userInfo", updatedMember.getUserid());
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "해당하는 ID의 회원을 탈퇴시킨다.", response = Map.class)
	@PutMapping("/delete/{userid}")
	public ResponseEntity<Map<String, Object>> deleteMember(
			@PathVariable @ApiParam(value = "탈퇴할 회원 ID", required = true) String userid, HttpServletRequest request) {
//        logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("access-token");
		if (jwtService.isUsable(token) && jwtService.get(token).get("userid").equals(userid)) {
			logger.info("사용 가능한 토큰!!!");
			try {
//                로그인 사용자 정보.
				memberService.deleteMember(userid);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "아이디 중복검사", notes = "입력한 userid가 사용중인지 검사한다. 사용중이라면 1 이상의 숫자가 반환된다.")
	@GetMapping("/idcheck")
	public ResponseEntity<?> idCheck(
			@RequestParam("userid") @ApiParam(value = "검사하려는 사용자 ID", required = true) String userid) {
		logger.info("MemberController - idCheck({}) 호출", userid);
		try {
			int isUsing = memberService.idCheck(userid);
			logger.info("{}를 사용중인 사용자 : {}", userid, isUsing);
				return new ResponseEntity<Integer>(isUsing, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "6개월 이내 탈퇴 여부 검사", notes = "입력한 userid가 6개월 이내에 탈퇴되었는지 여부를 반환한다. 6개월 이내에 탈퇴한 아이디라면 1 이상의 숫자가 반환된다.")
	@GetMapping("/deleted")
	public ResponseEntity<?> recentlyDeleted(
			@RequestParam("userid") @ApiParam(value = "검사하려는 사용자 ID", required = true) String userid) {
		logger.info("MemberController - recentlyDeleted({}) 호출", userid);
		try {
			int hasLocked = memberService.recentlyDeleted(userid);
			logger.info("{} 아이디의 6개월 이내 탈퇴 여부 : {}", userid, hasLocked);
				return new ResponseEntity<Integer>(hasLocked, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}