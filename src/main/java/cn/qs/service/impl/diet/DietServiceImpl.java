package cn.qs.service.impl.diet;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qs.bean.diet.Diet;
import cn.qs.bean.diet.DietExample;
import cn.qs.bean.diet.DietWithBLOBs;
import cn.qs.bean.user.User;
import cn.qs.bean.diet.DietExample.Criteria;
import cn.qs.mapper.diet.DietMapper;
import cn.qs.service.diet.DietService;

@Service
public class DietServiceImpl implements DietService {

	@Autowired
	private DietMapper dietMapper;
	
	@Override
	public void addDiet(DietWithBLOBs diet) {
		dietMapper.insert(diet);
	}

	@Override
	public void updateDiet(DietWithBLOBs diet) {
		dietMapper.updateByPrimaryKeyWithBLOBs(diet);
	}

	@Override
	public DietWithBLOBs getDietByDiseasename(String diseasename) {
		DietWithBLOBs diet = dietMapper.getDietByDiseasename(diseasename);
		return diet;
	}

}
