package com.ssafy.article.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.article.dto.ArticleDto;

public interface IArticleService {
	public boolean writeArticle(ArticleDto articleDto) throws Exception;
	public List<ArticleDto> listArticle(Map<String, String> map) throws Exception;
	public List<ArticleDto> latestArticle() throws Exception;
	public ArticleDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(ArticleDto articleDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
}
