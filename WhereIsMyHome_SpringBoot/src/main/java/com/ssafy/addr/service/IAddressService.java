package com.ssafy.addr.service;

import java.util.List;
import com.ssafy.addr.dto.AddressDto;

public interface IAddressService {

	public List<AddressDto> getSidoList();
	
	public List<AddressDto> getGugunList(String sidoCode);
	
	public List<AddressDto> getDongList(String gugunCode);
}