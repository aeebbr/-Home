package com.ssafy.favor.contoller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.favor.service.FavorService;

@RestController
@RequestMapping("/favor")
public class FavorController {
	private final Logger logger = LoggerFactory.getLogger(FavorController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final FavorService favorService;

	@Autowired
	public FavorController(FavorService favorService) {
		this.favorService = favorService;
	}
	
	@PostMapping("/{userId}/{dongCode}")
	private ResponseEntity<String> insert(@PathVariable("userId") String userId, @PathVariable("dongCode") String dongCode) throws Exception {
		logger.info("관심지역 insert - 호출");
		FavorDto favorDto = favorService.getName(dongCode);
		favorDto.setUserId(userId);
		favorDto.setDongCode(dongCode);
		logger.info(favorDto.toString());
		System.out.println(favorService.checkExist(favorDto));
		if (!favorService.checkExist(favorDto) && favorService.insertFavor(favorDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{favorNo}")
	private ResponseEntity<?> delete(@PathVariable("favorNo") String favorNo) throws Exception {
		logger.info("관심지역 delete - 호출");
		if (favorService.deleteFavor(favorNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{userId}")
	private  ResponseEntity<List<FavorDto>> list(@PathVariable("userId") String userId) throws Exception {
		logger.info("관심지역 list - 호출");
		return new ResponseEntity<List<FavorDto>>(favorService.listFavor(userId), HttpStatus.OK);
	}
}
