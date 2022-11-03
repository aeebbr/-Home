package com.ssafy.favor.contoller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.favor.service.FavorService;
import com.ssafy.member.dto.MemberDto;

@Controller
@RequestMapping("/favor")
public class FavorController {

	private final FavorService favorService;

	@Autowired
	public FavorController(FavorService favorService) {
		this.favorService = favorService;
	}

	@GetMapping("/delete")
	private String delete(@RequestParam("id") String id, Model model) {
		try {
			favorService.deleteFavor(id);
		} catch (SQLException e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
		}
		return "redirect:/favor/list";
	}

	@GetMapping("/list")
	private String list(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		String userId = memberDto.getUserId();
		try {
			List<FavorDto> list = favorService.listFavor(userId);

			model.addAttribute("regions", list);

			return "favor/favorList";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "error/error";
		}
	}

	@GetMapping("/favor")
	private String insert() {
		return "favor/favor";
	}

	@GetMapping("/insert")
	private String insert(@RequestParam("sidoCode") String sidoCode, @RequestParam("gugunCode") String gugunCode,
			@RequestParam("dongCode") String dongCode, @RequestParam("sidoName") String sidoName,
			@RequestParam("gugunName") String gugunName, @RequestParam("dongName") String dongName, HttpSession session,
			Model model) {
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

			return "redirect:/favor/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 처리중 에러 발생!!!");
			return "error/error";
		}
	}
}
