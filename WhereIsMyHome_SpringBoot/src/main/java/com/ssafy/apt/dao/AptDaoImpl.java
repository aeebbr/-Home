package com.ssafy.apt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ssafy.apt.dto.AptDto;
import com.ssafy.util.DBUtil;

public class AptDaoImpl implements IAptDao {

	private static AptDaoImpl aptDaoInstance;
	private static DBUtil dbUtil = DBUtil.getInstance();
	private List<AptDto> aptList; // 아파트 정보를 담는 리스트

	private AptDaoImpl() {
	}

	/**
	 * @return singleton apt object
	 */
	public static AptDaoImpl getAptInstance() {
		if (aptDaoInstance == null) {
			aptDaoInstance = new AptDaoImpl();
		}
		return aptDaoInstance;
	}

	@Override
	public List<AptDto> selectAptList(String sido, String gugun, String dong) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select houseinfo.apartmentName, housedeal.floor, housedeal.area, dongcode.sidoName, dongcode.gugunName, houseinfo.dong, housedeal.dealAmount, houseinfo.lat, houseinfo.lng, houseinfo.dongCode, houseinfo.aptCode, houseinfo.jibun\r\n");
		sql.append("from houseinfo inner join housedeal on (houseinfo.aptCode = housedeal.aptCode)\r\n");
		sql.append("inner join dongcode on (houseinfo.dongCode = dongcode.dongCode)\r\n");
		sql.append("where dongcode.sidoName=? and dongcode.gugunName=? and dongcode.dongName=?");

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		aptList = new ArrayList<AptDto>();

		try {
			con = dbUtil.getConnection();
			ps = con.prepareStatement(sql.toString());

			ps.setString(1, sido);
			ps.setString(2, gugun);
			ps.setString(3, dong);

			rs = ps.executeQuery();

			while (rs.next()) {
				AptDto aptDto = new AptDto();
				aptDto.setSidoName(rs.getString("dongcode.sidoName"));
				aptDto.setGugunName(rs.getString("dongcode.gugunName"));
				aptDto.setDongName(rs.getString("houseinfo.dong"));
				aptDto.setJibun(rs.getString("houseinfo.jibun"));
				aptDto.setAptName(rs.getString("houseinfo.apartmentName"));
				aptDto.setFloor(rs.getString("housedeal.floor"));
				aptDto.setArea(rs.getString("housedeal.area"));
				aptDto.setDealAmount(rs.getString("housedeal.dealAmount"));
				aptDto.setLat(rs.getString("houseinfo.lat"));
				aptDto.setLng(rs.getString("houseinfo.lng"));
				aptDto.setAptCode(rs.getString("houseinfo.aptCode"));

				aptList.add(aptDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(con, ps, rs);
		}

		return aptList;
	}
}