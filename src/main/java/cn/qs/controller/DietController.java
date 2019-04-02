package cn.qs.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qs.bean.diet.DietWithBLOBs;
import cn.qs.scheduled.Scheduled;
import cn.qs.service.diet.DietService;
import cn.qs.utils.JSONResultUtil;

@Controller
public class DietController {

	private static final Logger logger = LoggerFactory.getLogger(DietController.class);

	@Autowired
	private DietService dietService;

	@Autowired
	private Scheduled scheduled;

	@RequestMapping("/diet_list")
	public String diet_list() {
		return "diet_list";
	}

	/**
	 * 手动同步数据，用于测试爬虫
	 * 
	 * @return
	 */
	@RequestMapping("/syncData")
	@ResponseBody
	public JSONResultUtil syncData() {
		try {
			scheduled.scheduledMethod();
		} catch (Exception e) {
			logger.error("syncData error", e);
			return JSONResultUtil.error("爬数据出错");
		}

		return JSONResultUtil.ok();
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
