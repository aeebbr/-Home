package com.ssafy.article.service;

import java.util.List;

import com.ssafy.article.dto.ArticleDto;
import com.ssafy.board.dto.BoardDto;
import com.ssafy.mvc.util.PageNavigation;

public interface IArticleService {
	public boolean writeArticle(ArticleDto articleDto) throws Exception;
	public List<ArticleDto> listArticle(BoardDto boardDto) throws Exception;
	public PageNavigation makePageNavigation(BoardDto boardDto) throws Exception;
	
	public ArticleDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(ArticleDto articleDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
}
