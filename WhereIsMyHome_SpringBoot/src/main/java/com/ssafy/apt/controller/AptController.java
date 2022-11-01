package com.ssafy.apt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.apt.dto.AptDto;
import com.ssafy.apt.service.AptServiceImpl;

@WebServlet("/apt")
public class AptController extends HttpServlet {
	private static final long serialVersionUID = 5061579799838944624L;

	private AptServiceImpl aptService;

	@Override
	public void init() throws ServletException {
		super.init();
		aptService = AptServiceImpl.getAptInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println(action);
		String url = "";
		
		if (action.equals("aptlist")) {
			url = selectAptList(req, res, url);
			foward(req, res, url);
		} //
	}

	private void foward(HttpServletRequest req, HttpServletResponse res, String url) {
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		try {
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private String selectAptList(HttpServletRequest req, HttpServletResponse res, String url) {
		String sido = req.getParameter("sido");
		String gugun = req.getParameter("gugun");
		String dong = req.getParameter("dong");
		
		List<AptDto> aptList = aptService.selectAptList(sido, gugun, dong);
		req.setAttribute("aptList", aptList);
		
		url = "aptlist.jsp";
		return url;
	}
}