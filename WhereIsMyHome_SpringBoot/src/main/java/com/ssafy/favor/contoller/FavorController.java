package com.ssafy.favor.contoller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.favor.service.FavorService;
import com.ssafy.favor.service.FavorServiceImpl;
import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.service.MemberService;
import com.ssafy.member.service.MemberServiceImpl;

/**
 * Servlet implementation class FavorController
 */
@WebServlet("/favor")
public class FavorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FavorService favorService;

	public void init() {
		favorService = FavorServiceImpl.getFavorService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String act = req.getParameter("act");
		System.out.println("favor act: " + act);

		String path = "/index.jsp";

		if ("mvinsert".equals(act)) {
			path = "/assets/favor/favor.jsp";
			redirect(req, res, path);
		} else if ("insert".equals(act)) {
			path = insert(req, res);
			redirect(req, res, path);
		} else if ("mvlist".equals(act)) {
			redirect(req, res, "/assets/favor/favorList.jsp");
		} else if ("list".equals(act)) {
			path = list(req, res);
			forward(req, res, path);
		} else if ("delete".equals(act)) {
			path = delete(req, res);
			forward(req, res, path);
		}
	}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		
		try {
			favorService.deleteFavor(id);
			
			return "/favor?act=list";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String list(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		String userId = memberDto.getUserId();

		try {
			List<FavorDto> list = favorService.listFavor(userId);

			req.setAttribute("regions", list);

			return "/assets/favor/favorList.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		String sidoCode = req.getParameter("sidoCode");
		String gugunCode = req.getParameter("gugunCode");
		String dongCode = req.getParameter("dongCode");
		String sidoName = req.getParameter("sidoName");
		String gugunName = req.getParameter("gugunName");
		String dongName = req.getParameter("dongName");

		HttpSession session = req.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		String userId = memberDto.getUserId();

		FavorDto favorDto = new FavorDto();

		favorDto.setUserId(userId);

		favorDto.setSidoCode(sidoCode);
		favorDto.setGugunCode(gugunCode);
		favorDto.setDongCode(dongCode);

		favorDto.setSidoName(sidoName);
		favorDto.setGugunName(gugunName);
		favorDto.setDongName(dongName);

		try {
			favorService.insertFavor(favorDto);
			return "/favor?act=list";
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
}
