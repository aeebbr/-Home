package com.ssafy.favor.contoller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.favor.service.FavorService;
import com.ssafy.favor.service.FavorServiceImpl;
import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.service.MemberService;
import com.ssafy.member.service.MemberServiceImpl;

@Controller
@RequestMapping("/favor")
public class FavorController {
	
	@Autowired
	private ServletContext servletContext;
	
	private final FavorService favorService;
	
	@Autowired
	public FavorController(FavorService favorService) {
		this.favorService = favorService;
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
	
	@GetMapping("/delete")
	private String delete(@RequestParam("id") String id) {
		favorService.deleteFavor(id);
		return "favor/list";
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
}
