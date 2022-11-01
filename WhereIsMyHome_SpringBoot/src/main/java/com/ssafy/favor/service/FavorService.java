package com.ssafy.favor.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.favor.dto.FavorDto;

public interface FavorService {
	void insertFavor(FavorDto favorDto) throws SQLException;
	List<FavorDto> listFavor(String userId) throws SQLException;
	void deleteFavor(String id) throws SQLException;
}
