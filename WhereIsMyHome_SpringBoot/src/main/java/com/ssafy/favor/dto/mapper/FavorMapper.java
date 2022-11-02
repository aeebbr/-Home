package com.ssafy.favor.dto.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favor.dto.FavorDto;

@Mapper
public interface FavorMapper {
	void insertFavor(FavorDto favorDto) throws SQLException;

	List<FavorDto> listFavor(String userId) throws SQLException;

	void deleteFavor(String id) throws SQLException;
}
