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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private String info(RedirectAttributes redirectAtt, HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		String userId = memberDto.getUserId();

		try {
			memberDto = memberService.infoMember(userId);

			redirectAtt.addFlashAttribute("member", memberDto);

			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 처리중 에러 발생!!!");
			return "/error/error";
		}
	}

	@GetMapping("/delete")
	private String delete(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			memberService.deleteMember(memberDto.getUserId());
			logout(session);
			return "redirect:/";
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 탈퇴 처리중 에러 발생!!!");
			return "/error/error";
		}
	}

	@PostMapping("/modify")
	private String modify(@RequestParam Map<String, String> map, MemberDto memberDto, Model model) {
		logger.debug("modify user : {}", map.get("name"));

		try {
			memberService.modifyMember(map);
			return "redirect:/user/info";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error";
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, RedirectAttributes redirectAtt, Model model, HttpSession session,
			HttpServletResponse response) {

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
				redirectAtt.addFlashAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "/error/error";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/join")
	private String join(@RequestParam Map<String, String> map, MemberDto memberDto, Model model) {
		logger.debug("member info: {}", map.get("userid"));

		try {
			memberService.joinMember(map);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "/error/error";
		}
	}

	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}

}
