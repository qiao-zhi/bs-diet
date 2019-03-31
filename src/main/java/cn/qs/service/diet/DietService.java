package cn.qs.service.diet;

import cn.qs.bean.diet.DietWithBLOBs;

public interface DietService {
	public void addDiet(DietWithBLOBs diet);
	
	public void updateDiet(DietWithBLOBs diet);
	
	public DietWithBLOBs getDietByDiseasename(String diseasename);
}
