package com.ssafy.addr.dao;

import java.util.List;
import com.ssafy.addr.dto.AddressDto;

public interface IAddressDao {

	public List<AddressDto> getSidoList();

	public List<AddressDto> getGugunList(String sidoCode);

	public List<AddressDto> getDongList(String gugunCode);
}