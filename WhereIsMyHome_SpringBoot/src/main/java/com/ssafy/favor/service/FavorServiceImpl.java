package com.ssafy.favor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.favor.dto.mapper.FavorMapper;

@Service
public class FavorServiceImpl implements FavorService {
	private FavorMapper favorMapper;

	@Autowired
	private FavorServiceImpl(FavorMapper favorMapper) {
		this.favorMapper = favorMapper;
	}

	@Override
	public boolean checkExist(FavorDto favorDto) throws Exception {
		return favorMapper.checkExist(favorDto) == 0;
	}

	@Override
	public FavorDto getName(String dongCode) throws Exception {
		return favorMapper.getName(dongCode);
	}
	
	@Override
	public boolean insertFavor(FavorDto favorDto) throws Exception {
		return favorMapper.insertFavor(favorDto) == 1;
	}

	@Override
	public List<FavorDto> listFavor(String userId) throws Exception {
		return favorMapper.listFavor(userId);
	}

	@Override
	public boolean deleteFavor(String id) throws Exception {
		return favorMapper.deleteFavor(id) == 1;
	}
}
