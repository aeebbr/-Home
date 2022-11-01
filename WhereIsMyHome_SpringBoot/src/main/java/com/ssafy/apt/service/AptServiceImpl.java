package com.ssafy.apt.service;

import java.util.List;

import com.ssafy.apt.dao.AptDaoImpl;
import com.ssafy.apt.dto.AptDto;

public class AptServiceImpl implements IAptService {

	private static AptServiceImpl aptServiceInstance;
	private AptDaoImpl aptDao = AptDaoImpl.getAptInstance();

	private AptServiceImpl() {
	}

	/**
	 * @return singleton apt object
	 */
	public static AptServiceImpl getAptInstance() {
		if (aptServiceInstance == null) {
			aptServiceInstance = new AptServiceImpl();
		}
		return aptServiceInstance;
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

		List<AptDto> list = aptDao.selectAptList(sido, gugun, dong);

		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1; j++) {
				int firstDealAmount = Integer.parseInt(list.get(j).getDealAmount().replace(",", ""));
				int secondDealAmount = Integer.parseInt(list.get(j + 1).getDealAmount().replace(",", ""));
				
				if (firstDealAmount > secondDealAmount) {
					list.add(j, list.get(j+1));
					list.remove(j+1);
					list.add(j+1, list.get(j));
					list.remove(j+2);
				}
				else if (firstDealAmount == secondDealAmount) {
					int firstArea = Integer.parseInt(list.get(j).getArea().replace(".", ""));
					int secondArea = Integer.parseInt(list.get(j + 1).getArea().replace(".", ""));
					
					if (firstArea < secondArea) {
						list.add(j, list.get(j+1));
						list.remove(j+1);
						list.add(j+1, list.get(j));
						list.remove(j+2);
					}
					else if (firstArea == secondArea) {
						int firstFloor = Integer.parseInt(list.get(j).getFloor());
						int secondFloor = Integer.parseInt(list.get(j + 1).getFloor());
						
						if(firstFloor > secondFloor) {
							list.add(j, list.get(j+1));
							list.remove(j+1);
							list.add(j+1, list.get(j));
							list.remove(j+2);
						}
					}
				}
			}
		}
		return list;
	}
}