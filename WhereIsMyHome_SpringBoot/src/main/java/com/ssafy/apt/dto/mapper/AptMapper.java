package com.ssafy.apt.dto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apt.dto.AptDto;

@Mapper
public interface AptMapper {
	public List<AptDto> selectAptList(String sido, String gugun, String dong);
}
