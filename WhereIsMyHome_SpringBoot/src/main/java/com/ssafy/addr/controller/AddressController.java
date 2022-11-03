package com.ssafy.addr.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.addr.dto.AddressDto;
import com.ssafy.addr.service.IAddressService;

@Controller
@RequestMapping("/addr")
public class AddressController {

	private IAddressService addrService;

	private List<AddressDto> addrList;

	@Autowired
	public AddressController(IAddressService addrService) {
		this.addrService = addrService;
	}

	@GetMapping("/dong")
	private void getDongList(@RequestParam("gugun") String gugunCode, HttpServletResponse res) {
		res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        
		addrList = addrService.getDongList(gugunCode);

		JSONObject json = buildJsonObj(addrList);
		try {
			res.getWriter().write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/gugun")
	private void getGugunList(@RequestParam("sido") String sidoCode, HttpServletResponse res) {
		res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");
        
		addrList = addrService.getGugunList(sidoCode);

		JSONObject json = buildJsonObj(addrList);
		try {
			res.getWriter().write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/sido")
	private void getSidoList(HttpServletResponse res) {
		res.setContentType("text/html");
        res.setCharacterEncoding("utf-8");

		addrList = addrService.getSidoList();

		JSONObject json = buildJsonObj(addrList);

		try {
			res.getWriter().write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JSONObject buildJsonObj(List<AddressDto> addrList) {
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		for (AddressDto addressDto : addrList) {
			JSONObject reg = new JSONObject();
			reg.put("name", addressDto.getName());
			reg.put("code", addressDto.getCode());
			jsonArr.add(reg);
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("addrList", jsonArr);

		return jsonObj;
	}
}