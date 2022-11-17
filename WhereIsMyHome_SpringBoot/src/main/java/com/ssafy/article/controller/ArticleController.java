package com.ssafy.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.article.dto.ArticleDto;
import com.ssafy.article.service.IArticleService;
import com.ssafy.board.dto.BoardDto;
import com.ssafy.member.controller.MemberController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/article")
public class ArticleController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private IArticleService articleService;

	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody ArticleDto articleDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (articleService.writeArticle(articleDto)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<ArticleDto>> listArticle(BoardDto boardDto) throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticle(boardDto), HttpStatus.OK);
	}
	
	@GetMapping("/{articleno}")
	public ResponseEntity<ArticleDto> getArticle(@PathVariable("articleno") int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		articleService.updateHit(articleno);
		return new ResponseEntity<ArticleDto>(articleService.getArticle(articleno), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody ArticleDto articleDto) throws Exception {
		logger.info("modifyArticle - 호출 {}", articleDto);
		
		if (articleService.modifyArticle(articleDto)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.OK);
	}
	
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (articleService.deleteArticle(articleno)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
}