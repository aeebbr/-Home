package com.ssafy.member.dto.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	int idCheck(String userId) throws Exception; // 아이디 중복검사

	void joinMember(MemberDto memberDto) throws Exception; // 회원가입

	MemberDto loginMember(Map<String, String> map) throws Exception; // 로그인

	void deleteMember(String userId) throws SQLException; // 회원 탈퇴

	MemberDto infoMember(String userId) throws SQLException;

	void modifyMember(MemberDto memberDto) throws SQLException;
}
