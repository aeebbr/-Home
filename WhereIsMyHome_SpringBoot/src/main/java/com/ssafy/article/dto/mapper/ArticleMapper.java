package com.ssafy.article.dto.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.article.dto.ArticleDto;
import com.ssafy.board.dto.BoardDto;

@Mapper
public interface ArticleMapper {
	
	public int writeArticle(ArticleDto articleDto) throws SQLException;
	public List<ArticleDto> listArticle(BoardDto boardDto) throws SQLException;
	public int getTotalCount(BoardDto boardDto) throws SQLException;
	public ArticleDto getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(ArticleDto boardDto) throws SQLException;
	public void deleteMemo(int articleno) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	
}
