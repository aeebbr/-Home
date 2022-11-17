package com.ssafy.article.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.article.dto.ArticleDto;
import com.ssafy.article.dto.mapper.ArticleMapper;
import com.ssafy.board.dto.BoardDto;
import com.ssafy.mvc.util.PageNavigation;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeArticle(ArticleDto articleDto) throws Exception {
		if(articleDto.getSubject() == null || articleDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(ArticleMapper.class).writeArticle(articleDto) == 1;
	}

	@Override
	public List<ArticleDto> listArticle(BoardDto boardDto) throws Exception {
		int start = boardDto.getPg() == 0 ? 0 : (boardDto.getPg() - 1) * boardDto.getSpp();
		boardDto.setStart(start);
		return sqlSession.getMapper(ArticleMapper.class).listArticle(boardDto);
	}

	@Override
	public PageNavigation makePageNavigation(BoardDto boardDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(ArticleMapper.class).getTotalCount(boardDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public ArticleDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(ArticleMapper.class).getArticle(articleno);
	}

	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(ArticleMapper.class).updateHit(articleno);
		
	}

	@Override
	@Transactional
	public boolean modifyArticle(ArticleDto boardDto) throws Exception {
		return sqlSession.getMapper(ArticleMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(ArticleMapper.class).deleteArticle(articleno) == 1;
	}


}
