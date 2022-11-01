package com.ssafy.favor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.favor.dto.FavorDto;
import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;
import com.ssafy.util.DBUtil;

public class FavorDaoImpl implements FavorDao {
	private static FavorDao favorDao = new FavorDaoImpl();
	private DBUtil dbUtil;

	private FavorDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static FavorDao getFavorDao() {
		return favorDao;
	}

	@Override
	public void insertFavor(FavorDto favorDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append(
					"insert into favor (user_id, sidoCode, guguncode, dongcode, sidoName, gugunName, dongName, join_date) \n");
			sql.append("select ?, ?, ?, ?, ?, ?, ?, now() \n");
			sql.append("from dual \n");
			sql.append("where not exists(select * from favor where user_id = ? and dongcode = ?)");

			pstmt = conn.prepareStatement(sql.toString());

			int idx = 0;

			pstmt.setString(++idx, favorDto.getUserId());
			pstmt.setString(++idx, favorDto.getSidoCode());
			pstmt.setString(++idx, favorDto.getGugunCode());
			pstmt.setString(++idx, favorDto.getDongCode());
			pstmt.setString(++idx, favorDto.getSidoName());
			pstmt.setString(++idx, favorDto.getGugunName());
			pstmt.setString(++idx, favorDto.getDongName());
			// ===============
			pstmt.setString(++idx, favorDto.getUserId());
			pstmt.setString(++idx, favorDto.getDongCode());

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<FavorDto> listFavor(String userID) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		List<FavorDto> list = new ArrayList<>();

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select * from favor \n");
			sql.append("where user_id = ? ");
			sql.append("order by id");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FavorDto favorDto = new FavorDto();

				favorDto.setId(rs.getString("id"));

				favorDto.setSidoCode(rs.getString("sidoCode"));
				favorDto.setGugunCode(rs.getString("gugunCode"));
				favorDto.setDongCode(rs.getString("dongCode"));

				favorDto.setSidoName(rs.getString("sidoName"));
				favorDto.setGugunName(rs.getString("gugunName"));
				favorDto.setDongName(rs.getString("dongName"));

				list.add(favorDto);
			}

			return list;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteFavor(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("delete from favor \n");
			sql.append("where id = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, id);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
