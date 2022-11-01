package com.ssafy.favor.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.favor.dto.FavorDto;

public interface FavorDao {
	void insertFavor(FavorDto favorDto) throws SQLException;
	List<FavorDto> listFavor(String userId) throws SQLException;
	void deleteFavor(String id) throws SQLException;
}
