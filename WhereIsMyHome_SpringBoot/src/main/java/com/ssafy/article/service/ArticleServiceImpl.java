package com.ssafy.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.article.dto.ArticleDto;
import com.ssafy.article.dto.mapper.ArticleMapper;
@Service
public class ArticleServiceImpl implements IArticleService {
	private final ArticleMapper articleMapper;
	
	@Autowired
	private ArticleServiceImpl(ArticleMapper articleMapper) {
		this.articleMapper = articleMapper;
	}
	
	@Override
	public boolean writeArticle(ArticleDto articleDto) throws Exception {
		if(articleDto.getSubject() == null || articleDto.getContent() == null) {
			throw new Exception();
		}
		return articleMapper.writeArticle(articleDto) == 1;
	}

	@Override
	public List<ArticleDto> listArticle(Map<String, String> map) throws Exception {
		return articleMapper.listArticle(map);
	}
	
	@Override
	public List<ArticleDto> latestArticle() throws Exception {
		return articleMapper.latestArticle();
	}

	@Override
	public ArticleDto getArticle(int articleno) throws Exception {
		return articleMapper.getArticle(articleno);
	}

	@Override
	public void updateHit(int articleno) throws Exception {
		articleMapper.updateHit(articleno);
	}

	@Override
	public boolean modifyArticle(ArticleDto articleDto) throws Exception {
		return articleMapper.modifyArticle(articleDto) == 1;
	}

	@Override
	public boolean deleteArticle(int articleno) throws Exception {
		return articleMapper.deleteArticle(articleno) == 1;
	}
}
