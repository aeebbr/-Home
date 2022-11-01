package com.ssafy.member.service;

import com.ssafy.member.dto.MemberDto;

import java.sql.SQLException;

import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;

	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}

	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberDao.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		memberDao.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		return memberDao.loginMember(userId, userPwd);
	}

	@Override
	public void deleteMember(String userId) throws SQLException {
		memberDao.deleteMember(userId);
	}

	@Override
	public MemberDto infoMember(String userId) throws SQLException {
		return memberDao.infoMember(userId);
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws SQLException {
		memberDao.modifyMember(memberDto);
	}

}
