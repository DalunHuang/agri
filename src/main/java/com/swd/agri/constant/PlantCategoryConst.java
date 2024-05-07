package com.swd.agri.constant;

public enum PlantCategoryConst {

	// 未設定
	NON(6),
	// 被子植物
	ANGIOSPERMS(1),
	// 裸子植物
	GYMNOSPERMAE(2),
	// 蕨類植物
	PTERIDOPHYTA(3),
	// 苔蘚植物
	BRYOPHYTES(4),
	// 藻類
	ALGAE(5);

	private int value;

	private PlantCategoryConst(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static PlantCategoryConst valueOf(int value) {

		for (PlantCategoryConst categoryConst : PlantCategoryConst.values()) {
			if (categoryConst.getValue() == value) {
				return categoryConst;
			}
		}

		throw new IllegalArgumentException("No such PlantCategoryConst with value: " + value);

	}

}
