package cn.qs.service.impl.diet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qs.bean.diet.Diet;
import cn.qs.bean.diet.DietExample;
import cn.qs.bean.diet.DietWithBLOBs;
import cn.qs.bean.user.User;
import cn.qs.bean.diet.DietExample.Criteria;
import cn.qs.mapper.diet.DietMapper;
import cn.qs.service.diet.DietService;
import cn.qs.utils.DefaultValue;
import cn.qs.utils.IKAnalyzerUtils;
import cn.qs.utils.MySimHash;

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

	@Override
	public List<DietWithBLOBs> getDietsByKeyword(String keyWord) {
		List<DietWithBLOBs> result = new ArrayList<>();
		List<DietWithBLOBs> allDiets = dietMapper.selectByExampleWithBLOBs(null);

		// IKAnalyzer分词
		// List<String> keys = IKAnalyzerUtils.testTokenStream(keyWord);

		for (DietWithBLOBs diet : allDiets) {
			String dietName = StringUtils.defaultIfBlank(diet.getDiseasename(), "");

			MySimHash hash1 = new MySimHash(dietName, 64);
			MySimHash hash2 = new MySimHash(keyWord, 64);
			int hammingDistance = hash1.hammingDistance(hash2);
			// 海明距离<20证明达到匹配的相似度
			if (hammingDistance <= DefaultValue.HAMMING_VALUE && !result.contains(diet)) {
				result.add(diet);
			}
		}

		return result;
	}

	@Override
	public DietWithBLOBs getDietById(Integer id) {
		return dietMapper.selectByPrimaryKey(id);
	}

}
