package com.swd.agri.constant;

public enum PlantOrganCategoryConst {
	
	//未設定
	NON(1),
	//全身
	ALL(2),
	//根
	PLANT_ROOT(3),
	//莖
	PLANT_STEM(4),
	//葉
	PLANT_LEAF(5),
	//花
	PLANT_FLOWER(6),
	//果實
	PLANT_FRUIT(7),
	//種子
	PLANT_SEED(8);
	
	private int value;
	
	private PlantOrganCategoryConst(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static PlantOrganCategoryConst valueOf(int value) {
		
		for (PlantOrganCategoryConst categoryConst : PlantOrganCategoryConst.values()) {
			if (categoryConst.getValue() == value) {
				return categoryConst;
			}
		}
		
		throw new IllegalArgumentException("No such PlantOrganCategoryConst with value: " + value);
				
	}

}
