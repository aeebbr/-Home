package com.ssafy.favor.contoller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.favor.service.FavorService;
import com.ssafy.member.dto.MemberDto;

@RestController
@RequestMapping("/favor")
public class FavorController {

	private final FavorService favorService;

	@Autowired
	public FavorController(FavorService favorService) {
		this.favorService = favorService;
	}

	@GetMapping("/delete")
	private ResponseEntity<?> delete(@RequestParam("id") String id, Model model) {
		try {
			favorService.deleteFavor(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("msg", "관심지역 삭제 중 오류 발생");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

//		return "redirect:/favor/list";
	}

	@GetMapping("/list")
	private ModelAndView list(HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		String userId = memberDto.getUserId();
		ModelAndView mav = new ModelAndView();

		try {
			List<FavorDto> list = favorService.listFavor(userId);
			mav.addObject("regions", list);
			mav.setViewName("favor/favorList");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", "관심지역 리스트 불러오기 중 오류 발생");
			mav.setViewName("error/error");
		}

		return mav;
	}

	@GetMapping("/favor")
	private ModelAndView insert() {
		return new ModelAndView("favor/favor");
	}

	@GetMapping(value = "/insert", produces = "application/text; charset=utf8")
	private ResponseEntity<?> insert(@RequestParam("sidoCode") String sidoCode,
			@RequestParam("gugunCode") String gugunCode, @RequestParam("dongCode") String dongCode,
			@RequestParam("sidoName") String sidoName, @RequestParam("gugunName") String gugunName,
			@RequestParam("dongName") String dongName, HttpSession session, Model model) {
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

			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "관심지역 삽입 중 오류 발생");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
