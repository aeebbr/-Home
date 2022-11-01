package com.ssafy.apt.dao;

import java.util.List;
import com.ssafy.apt.dto.AptDto;

public interface IAptDao {

	/**
	 * 아파트 목록 조회 메소드
	 * 
	 * @return 아파트 정보가 담긴 리스트
	 */
	public List<AptDto> selectAptList(String sido, String gugun, String dong);
}