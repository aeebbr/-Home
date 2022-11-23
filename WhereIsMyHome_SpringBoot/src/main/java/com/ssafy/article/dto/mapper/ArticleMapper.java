package com.ssafy.article.dto.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.article.dto.ArticleDto;

@Mapper
public interface ArticleMapper {
	
	public int writeArticle(ArticleDto articleDto) throws SQLException;
	public List<ArticleDto> listArticle(Map<String, String> map) throws SQLException;
	public List<ArticleDto> latestArticle() throws SQLException;
	public ArticleDto getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(ArticleDto articleDto) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	
}
