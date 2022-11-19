package com.ssafy.apt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apt.dto.AptDto;
import com.ssafy.apt.service.AptServiceImpl;
import com.ssafy.favor.contoller.FavorController;

@RestController
@RequestMapping("/apt")
public class AptController {
	private final Logger logger = LoggerFactory.getLogger(FavorController.class);

	private AptServiceImpl aptService;

	@Autowired
	public AptController(AptServiceImpl aptService) {
		this.aptService = aptService;
	}

	@GetMapping("/{dongCode}")
	private ResponseEntity<List<AptDto>> selectAptList(@PathVariable("dongCode") String dongCode) {
		logger.info("아파트 list - 호출");
		return new ResponseEntity<List<AptDto>>(aptService.selectAptList(dongCode), HttpStatus.OK);
	}
}