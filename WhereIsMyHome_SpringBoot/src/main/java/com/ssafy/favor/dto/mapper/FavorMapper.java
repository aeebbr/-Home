package com.ssafy.favor.dto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favor.dto.FavorDto;

@Mapper
public interface FavorMapper {
	public int checkExist(FavorDto favorDto) throws Exception;
	public FavorDto getName(String dongCode);
	public int insertFavor(FavorDto favorDto) throws Exception;
	public int deleteFavor(String id) throws Exception;
	List<FavorDto> listFavor(String userId) throws Exception;
}
