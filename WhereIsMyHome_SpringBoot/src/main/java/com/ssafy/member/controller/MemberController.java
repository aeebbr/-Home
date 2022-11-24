package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.service.IMemberService;

import io.swagger.v3.oas.models.media.MediaType;

@RestController
@RequestMapping("/user")
public class MemberController {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private final IMemberService memberService;

	@Autowired
	public MemberController(IMemberService memberService) {
		logger.info("MemberCotroller 생성자 호출!!!!");
		this.memberService = memberService;
	}

	@GetMapping("/info")
	private ResponseEntity<MemberDto> info(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		String userId = memberDto.getUserId();

		try {
			memberDto = memberService.infoMember(userId);
			
//			Map<String, String> map = new HashMap<String, String>();
//			map.put(", value)
			
//			return memberDto;
			return new ResponseEntity<>(memberDto,HttpStatus.OK);
//			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 처리중 에러 발생!!!");
//			return "/error/error";
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			return null;
		}
	}

	@GetMapping("/delete")
	private ResponseEntity<?> delete(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");

		try {
			memberService.deleteMember(memberDto.getUserId());
			logout(session);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 탈퇴 처리중 에러 발생!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/modify")
	private ResponseEntity<?> modify(@RequestBody MemberDto member, Model model) {
		logger.debug("modify user : {}", member.getUserName());
				
		Map<String, String> map = new HashMap<>();
		map.put("userId", member.getUserId());
		map.put("userPwd", member.getUserPwd());
		map.put("userName", member.getUserName());
		map.put("userAddr", member.getUserAddr());
		map.put("userPhoneNum", member.getUserPhoneNum());
		
		
		try {
			memberService.modifyMember(map);
			
			System.out.println();
			return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("실패!!!!!!!!!!!");
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody MemberDto member, RedirectAttributes redirectAtt, Model model,
			HttpSession session, HttpServletResponse response) {

		logger.debug("map : {}", member.getUserId());
		Map<String, String> map = new HashMap<>();
		map.put("userId", member.getUserId());
		map.put("userPwd", member.getUserPwd());

		try {
			MemberDto memberDto = memberService.loginMember(map);
			logger.debug("memberDto : {}", memberDto);

			if (memberDto != null) { // 로그인 성공
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("userId"));
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//					cookie.setPath("/user");

				response.addCookie(cookie);

				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				System.out.println("로그인 실패");
				redirectAtt.addFlashAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return ResponseEntity.status(100).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
//			return "/error/error";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		try {
			session.invalidate();
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/join")
	private ResponseEntity<?> join(@RequestBody MemberDto member, MemberDto memberDto, Model model) {
//		private ResponseEntity<?> join(@RequestParam Map<String, String> map, MemberDto memberDto, Model model) {
		logger.debug("member info: {}", member.getUserId());

		try {
			System.out.println(member.getUserId());
			
			Map<String, String> map = new HashMap<>();
			map.put("userId", member.getUserId());
			map.put("userPwd", member.getUserPwd());
			map.put("userName", member.getUserName());
			map.put("userAddr", member.getUserAddr());
			map.put("userPhoneNum", member.getUserPhoneNum());
			
//			map.set(userid, member.getUserId());
//            map.set(userpwd, this.signUp.password);
//            map.set(username, this.signUp.name);
//            map.set(useraddr, this.signUp.address);
//            map.set(userphonenumber, this.signUp.phoneNumber);
			
			memberService.joinMember(map);
//			return "redirect:/";
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
//			return "/error/error";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
