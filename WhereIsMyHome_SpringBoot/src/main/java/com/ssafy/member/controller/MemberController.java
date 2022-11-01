package com.ssafy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.service.MemberService;
import com.ssafy.member.service.MemberServiceImpl;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;

	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		System.out.println("act ==== " + act);

		String path = "/index.jsp";
		if ("mvjoin".equals(act)) {
			path = "/user/join.jsp";
			redirect(request, response, path);
		} else if ("idcheck".equals(act)) {
			int cnt = idCheck(request, response);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(cnt);
		} else if ("join".equals(act)) {
			path = join(request, response);
			forward(request, response, path);
		} else if ("mvlogin".equals(act)) {
			// 일단 메인 화면으로 이동
			// 로그인 모달로 이동 -> 추후 보강
			path = "/index.jsp";
			redirect(request, response, path);
		} else if ("login".equals(act)) {
			path = login(request, response);
			forward(request, response, path);
		} else if ("logout".equals(act)) {
			path = logout(request, response);
			forward(request, response, path);
		} else if ("info".equals(act)) {
			path = info(request, response);
			forward(request, response, path);
		} else if ("modify".equals(act)) {
			path = modify(request, response);
			forward(request, response, path);
		} else if ("delete".equals(act)) {
			path = delete(request, response);
			forward(request, response, path);
		} else {
			redirect(request, response, path);
		}

	}

	private String info(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		String userId = memberDto.getUserId();

		try {
			memberDto = memberService.infoMember(userId);
			request.setAttribute("member", memberDto);
			
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			memberService.deleteMember(memberDto.getUserId());
			logout(request, response);
			return "/user?act=mvlogin";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 탈퇴 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String pNum = request.getParameter("pNumber");
		
		MemberDto memberDto = new MemberDto();

		memberDto.setUserId(id);
		memberDto.setUserPwd(pw);
		memberDto.setUserName(name);
		memberDto.setUserAddr(addr);
		memberDto.setUserPhoneNum(pNum);

		try {
			memberService.modifyMember(memberDto);
			return "/user?act=info";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		
		try {
			
			MemberDto memberDto = memberService.loginMember(userId, userPwd);
			if (memberDto != null) { // 로그인 성공
				String saveid = request.getParameter("saveid");
				if ("ok".equals(saveid)) { // 아이디 저장 체크 O.(구현 안함)
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
					cookie.setPath(request.getContextPath());

					response.addCookie(cookie);
				} else { 
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("ssafy_id")) {
								cookie.setMaxAge(0);
								cookie.setPath(request.getContextPath());

								response.addCookie(cookie);
								break;
							}
						}
					}
				}

				HttpSession session = request.getSession();
				session.setAttribute("userinfo", memberDto);
				String referer = request.getHeader("referer");
				
				return "/index.jsp";
			} else { // 로그인 실패(id, pwd 불일치!!!!)
				request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다");
				return "/index.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();

		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setUserAddr(request.getParameter("useraddr"));
		
		System.out.println("넘버 " + request.getParameter("userphonenumber"));
		memberDto.setUserPhoneNum(request.getParameter("userphonenumber"));
		
		try {
			memberService.joinMember(memberDto);
			return "/user?act=mvlogin";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		
		try {
			int count = memberService.idCheck(userId);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}

}
