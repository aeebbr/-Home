package com.ssafy.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {

	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;

	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String userId) throws SQLException {
		int cnt = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(user_id) \n");
			sql.append("from members \n");
			sql.append("where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"insert into members (user_id, user_name, user_password, user_address, user_phone_number, join_date) \n");
			sql.append("values (?, ?, ?, ?, ?, now())");

			pstmt = conn.prepareStatement(sql.toString());

			int idx = 0;

			pstmt.setString(++idx, memberDto.getUserId());
			pstmt.setString(++idx, memberDto.getUserName());
			pstmt.setString(++idx, memberDto.getUserPwd());
			pstmt.setString(++idx, memberDto.getUserAddr());
			pstmt.setString(++idx, memberDto.getUserPhoneNum());

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}

	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_name \n");
			sql.append("from members \n");
			sql.append("where user_id = ? and user_password = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(userId);
				memberDto.setUserName(rs.getString("user_name"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return memberDto;
	}

	@Override
	public void deleteMember(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from members \n");
			sql.append("where user_id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}

		return;
	}

	@Override
	public MemberDto infoMember(String userId) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select user_id, user_name, user_password, user_address, user_phone_number \n");
			sql.append("from members \n");
			sql.append("where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserPwd(rs.getString("user_password"));
				memberDto.setUserAddr(rs.getString("user_address"));
				memberDto.setUserPhoneNum(rs.getString("user_phone_number"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return memberDto;
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String id = memberDto.getUserId();
		String pw = memberDto.getUserPwd();
		String name = memberDto.getUserName();
		String addr = memberDto.getUserAddr();
		String pNum = memberDto.getUserPhoneNum();

		System.out.println("id: " + id);
		System.out.println(name + " " + addr + " " + pNum);

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members \n");
			sql.append("set user_name = ?, user_password = ?, user_address = ?, user_phone_number = ? \n");
			sql.append("where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, pw);
			pstmt.setString(3, addr);
			pstmt.setString(4, pNum);
			pstmt.setString(5, id);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

}
