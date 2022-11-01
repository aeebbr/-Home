package com.ssafy.addr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ssafy.addr.dto.AddressDto;
import com.ssafy.util.DBUtil;

public class AddressDaoImpl implements IAddressDao {

	private DBUtil dbUtil = DBUtil.getInstance();
	private List<AddressDto> addrList;

	private static AddressDaoImpl instance;

	private AddressDaoImpl() {
	}

	public static AddressDaoImpl getInstance() {
		if (instance == null) {
			instance = new AddressDaoImpl();
		}
		return instance;
	}

	@Override
	public List<AddressDto> getSidoList() {
		String sql = "select sidoName, substring(dongCode, 1, 2) from dongCode where gugunName is null and dongName is null;";

		System.out.println(sql);
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			addrList = new ArrayList<>();
			while (rs.next()) {
				AddressDto addrObj = new AddressDto();
				addrObj.setName(rs.getString("sidoName"));
				addrObj.setCode(rs.getString("substring(dongCode, 1, 2)"));
				addrList.add(addrObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(con, ps, rs);
		}

		return addrList;
	}

	@Override
	public List<AddressDto> getGugunList(String sidoCode) {
		String sql = "select gugunName, substring(dongCode, 1, 5) from dongCode where dongCode like '" + sidoCode + "%' and dongName is null and gugunName is not null";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			addrList = new ArrayList<>();
			while (rs.next()) {
				AddressDto addrObj = new AddressDto();
				addrObj.setName(rs.getString("gugunName"));
				addrObj.setCode(rs.getString("substring(dongCode, 1, 5)"));
				addrList.add(addrObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(con, ps, rs);
		}

		return addrList;
	}

	@Override
	public List<AddressDto> getDongList(String gugunCode) {
		String sql = "select dongName, dongCode from dongCode where dongCode like '" + gugunCode + "%' and dongName is not null";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			addrList = new ArrayList<>();
			while (rs.next()) {
				AddressDto addrObj = new AddressDto();
				addrObj.setName(rs.getString("dongName"));
				addrObj.setCode(rs.getString("dongCode"));
				addrList.add(addrObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(con, ps, rs);
		}

		return addrList;
	}
}