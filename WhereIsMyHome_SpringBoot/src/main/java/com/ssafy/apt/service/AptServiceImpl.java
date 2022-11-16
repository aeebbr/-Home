package com.ssafy.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.apt.dto.AptDto;
import com.ssafy.apt.dto.mapper.AptMapper;

@Service
public class AptServiceImpl implements IAptService {
	private AptMapper aptMapper;

	@Autowired
	private AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}

	@Override
	public List<AptDto> selectAptList(String sido, String gugun, String dong) {
		if (gugun.contains("+")) {
			gugun.replace("+", " ");
		}
		if (gugun.contains("%20")) {
			gugun.replace("%20", " ");
		}
		if (dong.contains("+")) {
			dong.replace("+", " ");
		}
		if (dong.contains("%20")) {
			dong.replace("%20", " ");
		}

		List<AptDto> list = aptMapper.selectAptList(sido, gugun, dong);
		System.out.println(list);
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1; j++) {
				int firstDealAmount = Integer.parseInt(list.get(j).getDealAmount().replace(",", ""));
				int secondDealAmount = Integer.parseInt(list.get(j + 1).getDealAmount().replace(",", ""));

				if (firstDealAmount > secondDealAmount) {
					list.add(j, list.get(j + 1));
					list.remove(j + 1);
					list.add(j + 1, list.get(j));
					list.remove(j + 2);
				} else if (firstDealAmount == secondDealAmount) {
					int firstArea = Integer.parseInt(list.get(j).getArea().replace(".", ""));
					int secondArea = Integer.parseInt(list.get(j + 1).getArea().replace(".", ""));

					if (firstArea < secondArea) {
						list.add(j, list.get(j + 1));
						list.remove(j + 1);
						list.add(j + 1, list.get(j));
						list.remove(j + 2);
					} else if (firstArea == secondArea) {
						int firstFloor = Integer.parseInt(list.get(j).getFloor());
						int secondFloor = Integer.parseInt(list.get(j + 1).getFloor());

						if (firstFloor > secondFloor) {
							list.add(j, list.get(j + 1));
							list.remove(j + 1);
							list.add(j + 1, list.get(j));
							list.remove(j + 2);
						}
					}
				}
			}
		}
		System.out.println(list);
		return list;
	}
}