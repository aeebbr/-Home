package com.ssafy.apt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.apt.dto.AptDto;
import com.ssafy.apt.service.AptServiceImpl;

@Controller
@RequestMapping("/apt")
public class AptController {
	
	private AptServiceImpl aptService;

	@Autowired
	public AptController(AptServiceImpl aptService) {
		this.aptService = aptService;
	}

	@GetMapping("/aptlist")
	private String selectAptList(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun,
			@RequestParam("dong") String dong, String url, Model model) {
		
		List<AptDto> aptList = aptService.selectAptList(sido, gugun, dong);
		model.addAttribute("aptList", aptList);
		
		return "aptlist";
	}
}