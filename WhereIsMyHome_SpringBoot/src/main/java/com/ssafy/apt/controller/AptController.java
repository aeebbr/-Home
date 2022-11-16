package com.ssafy.apt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.apt.dto.AptDto;
import com.ssafy.apt.service.AptServiceImpl;
import com.ssafy.member.dto.MemberDto;

@RestController
@RequestMapping("/apt")
public class AptController {

	private AptServiceImpl aptService;

	@Autowired
	public AptController(AptServiceImpl aptService) {
		this.aptService = aptService;
	}

	@GetMapping("/aptlist")
	private ResponseEntity<List<AptDto>> selectAptList(@RequestParam("sido") String sido,
			@RequestParam("gugun") String gugun, @RequestParam("dong") String dong) {
		System.out.println(sido + " " + gugun + " " + dong);
		List<AptDto> aptList = aptService.selectAptList(sido, gugun, dong);
		System.out.println(aptList);
//		mav.addObject("aptList", aptList);
//		mav.setViewName("aptlist");

		return new ResponseEntity<>(aptList, HttpStatus.OK);
	}
}