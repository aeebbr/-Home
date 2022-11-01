package com.ssafy.apt.service;

import java.util.List;

import com.ssafy.apt.dto.AptDto;

public interface IAptService {

	public List<AptDto> selectAptList(String sido, String gugun, String dong);
}
