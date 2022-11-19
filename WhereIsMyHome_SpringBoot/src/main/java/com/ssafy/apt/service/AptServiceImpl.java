package com.ssafy.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apt.dto.AptDto;
import com.ssafy.apt.dto.mapper.AptMapper;

@Service
public class AptServiceImpl implements IAptService {
	private AptMapper aptMapper;

	@Autowired
	private AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}

	@Override
	public List<AptDto> selectAptList(String dongCode) {
		return aptMapper.selectAptList(dongCode);
	}
}