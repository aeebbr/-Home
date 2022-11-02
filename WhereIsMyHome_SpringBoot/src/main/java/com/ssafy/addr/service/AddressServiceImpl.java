package com.ssafy.addr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.addr.dto.AddressDto;
import com.ssafy.addr.dto.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements IAddressService {
	private AddressMapper addressMapper;
	
	private AddressServiceImpl(AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}

	@Override
	public List<AddressDto> getSidoList() {
		return addressMapper.getSidoList();
	}

	@Override
	public List<AddressDto> getGugunList(String sidoCode) {
		return addressMapper.getGugunList(sidoCode);
	}

	@Override
	public List<AddressDto> getDongList(String gugunCode) {
		return addressMapper.getDongList(gugunCode);
	}
}