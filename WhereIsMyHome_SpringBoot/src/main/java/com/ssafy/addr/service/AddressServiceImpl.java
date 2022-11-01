package com.ssafy.addr.service;

import java.util.List;

import com.ssafy.addr.dao.AddressDaoImpl;
import com.ssafy.addr.dto.AddressDto;

public class AddressServiceImpl implements IAddressService {

	private static AddressServiceImpl instance;

	private AddressServiceImpl() {
	}

	public static AddressServiceImpl getInstance() {
		if (instance == null) {
			instance = new AddressServiceImpl();
		}
		return instance;
	}
	
	private AddressDaoImpl addrDao = AddressDaoImpl.getInstance();
	
	@Override
	public List<AddressDto> getSidoList() {
		return addrDao.getSidoList();
	}

	@Override
	public List<AddressDto> getGugunList(String sidoCode) {
		return addrDao.getGugunList(sidoCode);
	}

	@Override
	public List<AddressDto> getDongList(String gugunCode) {
		return addrDao.getDongList(gugunCode);
	}
}