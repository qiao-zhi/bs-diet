package cn.qs.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qs.bean.diet.DietWithBLOBs;
import cn.qs.service.diet.DietService;

@Controller
public class DietController {

	@Autowired
	private DietService dietService;

	@RequestMapping("/diet_list")
	public String diet_list() {
		return "diet_list";
	}

	@RequestMapping("/getDietsByKeyword")
	@ResponseBody
	public List<DietWithBLOBs> getDietsByKeyword(String keyword) {
		List<DietWithBLOBs> result = new ArrayList<>();

		if (StringUtils.isBlank(keyword)) {
			return result;
		}

		return dietService.getDietsByKeyword(keyword);
	}
}
