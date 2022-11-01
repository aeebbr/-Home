package com.ssafy.addr.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.addr.dto.AddressDto;
import com.ssafy.addr.service.AddressServiceImpl;

@WebServlet("/addr")
public class AddressController extends HttpServlet {
	private static final long serialVersionUID = -3039549227896163104L;

	private AddressServiceImpl addrService;
	private List<AddressDto> addrList;

	@Override
	public void init() throws ServletException {
		super.init();
		addrService = AddressServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println(action);

		res.setContentType("application/json;charset=utf-8");
		if (action.equals("sido")) {
			getSidoList(req, res);
			return;
		} //
		else if (action.equals("gugun")) {
			getGugunList(req, res);
			return;
		} //
		else if (action.equals("dong")) {
			getDongList(req, res);
			return;
		}
	}

	private void getDongList(HttpServletRequest req, HttpServletResponse res) {
		String gugunCode = req.getParameter("gugun");
		addrList = addrService.getDongList(gugunCode);

		JSONObject json = buildJsonObj(addrList);
		try {
			res.getWriter().write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getGugunList(HttpServletRequest req, HttpServletResponse res) {
		String sidoCode = req.getParameter("sido");
		addrList = addrService.getGugunList(sidoCode);

		JSONObject json = buildJsonObj(addrList);
		try {
			res.getWriter().write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getSidoList(HttpServletRequest req, HttpServletResponse res) {
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