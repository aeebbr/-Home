package com.ssafy.addr.dto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.addr.dto.AddressDto;

@Mapper
public interface AddressMapper {
	public List<AddressDto> getSidoList();

	public List<AddressDto> getGugunList(String sidoCode);

	public List<AddressDto> getDongList(String gugunCode);
}
