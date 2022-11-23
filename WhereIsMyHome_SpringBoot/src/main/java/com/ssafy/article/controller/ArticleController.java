package com.ssafy.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.article.dto.ArticleDto;
import com.ssafy.article.service.IArticleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/article")
public class ArticleController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private IArticleService articleService;

	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody Map<String, Object> map) throws Exception {
		logger.info("writeArticle - 호출");
		ArticleDto articleDto = new ArticleDto();
		articleDto.setUserid((String) map.get("userid"));
		articleDto.setSubject((String) map.get("subject"));
		articleDto.setContent((String) map.get("content"));
		if (articleService.writeArticle(articleDto)) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<ArticleDto>> listArticle() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticle(new HashMap<String, String>()), HttpStatus.OK);
	}
	
	@GetMapping("/latest")
	public ResponseEntity<List<ArticleDto>> latestArticle() throws Exception {
		logger.info("latestArticle - 호출");
		return new ResponseEntity<List<ArticleDto>>(articleService.latestArticle(), HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<ArticleDto>> searchArticle(@RequestBody Map<String, String> map) throws Exception {
		logger.info("searchArticle - 호출");
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticle(map), HttpStatus.OK);
	}

	@GetMapping("/{articleno}")
	public ResponseEntity<ArticleDto> getArticle(@PathVariable("articleno") int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		articleService.updateHit(articleno);
		return new ResponseEntity<ArticleDto>(articleService.getArticle(articleno), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody Map<String, Object> map) throws Exception {
		logger.info("modifyArticle - 호출 {}");
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleno((int) map.get("articleno"));
		articleDto.setSubject((String) map.get("subject"));
		articleDto.setContent((String) map.get("content"));

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