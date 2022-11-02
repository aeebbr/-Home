package com.ssafy.member.service;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.dto.mapper.MemberMapper;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberMapper memberMapper;

	private MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		return memberMapper.loginMember(userId, userPwd);
	}

	@Override
	public void deleteMember(String userId) throws SQLException {
		memberMapper.deleteMember(userId);
	}

	@Override
	public MemberDto infoMember(String userId) throws SQLException {
		return memberMapper.infoMember(userId);
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws SQLException {
		memberMapper.modifyMember(memberDto);
	}

}
