package com.ssafy.member.dao;

import java.sql.SQLException;

import com.ssafy.member.dto.MemberDto;

public interface MemberDao {

	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	MemberDto infoMember(String userId) throws SQLException;
	void modifyMember(MemberDto memberDto) throws SQLException;
	
}
