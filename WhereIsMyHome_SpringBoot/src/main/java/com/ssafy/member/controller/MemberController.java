package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		logger.info("MemberCotroller 생성자 호출!!!!");
		this.memberService = memberService;
	}

	@GetMapping("/info")
	private String info(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		String userId = memberDto.getUserId();

		try {
			memberDto = memberService.infoMember(userId);
			model.addAttribute("member", memberDto);

			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	@GetMapping("/delete")
	private String delete(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			memberService.deleteMember(memberDto.getUserId());
			logout(session);
			return "/user/login";
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 탈퇴 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	@PostMapping("/modify")
	private String modify(@RequestParam Map<String, String> map, MemberDto memberDto, Model model) {
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String name = request.getParameter("name");
//		String addr = request.getParameter("addr");
//		String pNum = request.getParameter("pNumber");

//		MemberDto memberDto = new MemberDto();

//		memberDto.setUserId(id);
//		memberDto.setUserPwd(pw);
//		memberDto.setUserName(name);
//		memberDto.setUserAddr(addr);
//		memberDto.setUserPhoneNum(pNum);

		logger.debug("modify user : {}", map.get("id"));

		try {
			memberService.modifyMember(memberDto);
			return "/user/info";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) {
//		String userId = map.get("userid");
//		String userPwd = map.get("userpwd");

		logger.debug("map : {}", map.get("userid"));

		try {
			MemberDto memberDto = memberService.loginMember(map);
			logger.debug("memberDto : {}", memberDto);

			if (memberDto != null) { // 로그인 성공
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//					cookie.setPath("/user");

				response.addCookie(cookie);

				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/join")
	public String join() {
		return "user/join";
	}

	@PostMapping("/join")
	private String join(MemberDto memberDto, Model model) {
		logger.debug("member info: {}", memberDto);

		try {
			memberService.joinMember(memberDto);
			return "redirect:/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

//	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
//		String userId = request.getParameter("userid");
//
//		try {
//			int count = memberService.idCheck(userId);
//			return count;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 500;
//	}
}
