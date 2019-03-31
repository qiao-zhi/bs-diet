package cn.qs.scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.qs.bean.diet.DietWithBLOBs;
import cn.qs.service.diet.DietService;

@Component
public class Scheduled {
	private static final Logger log = LoggerFactory.getLogger(Scheduled.class);
	@Autowired
	private DietService dietService;

	@org.springframework.scheduling.annotation.Scheduled(cron = "* * 1 * * ?") // 设置任务执行时间(此处可自行设置)
	public void scheduledMethod() {
		log.debug("定时器被触发");
		this.getDataSource();
	}

	/**
	 * 爬虫获取首页疾病名称及链接
	 */
	public void getDataSource() {
		try {
			Document document = Jsoup.connect("https://www.meishij.net/yaoshanshiliao/jibingtiaoli/").get();
			Element element = document.body();
			Elements div = element.getElementsByClass("listnav_dl_style1");
			Element div1 = div.get(0);
			Elements tagA = div1.getElementsByTag("a");
			for (Element ele : tagA) {
				// 获取疾病标签链接及名称
				System.out.println("href=" + ele.attr("href") + "名称=" + ele.text());
				String diseUrl = ele.attr("href");// 获取疾病链接
				String diseName = ele.text();// 获取疾病名称
				this.getFoodSource(diseUrl, diseName);
			}
		} catch (Exception e) {
			log.debug("datasource exception:{}", e);
		}
	}

	/**
	 * 获取疾病对应的适宜食物与不适宜食物
	 * 
	 * @param url
	 * @param name
	 */
	public void getFoodSource(String url, String name) {
		try {
			Document document = Jsoup.connect(url).get();
			Elements element = document.getElementsByClass("slys_con");
			Element elementFirstDiv = element.get(0);
			Elements ul = elementFirstDiv.getElementsByClass("clearfix");

			// 获取适宜食物
			Element firstul = ul.get(0);
			Elements suitable = firstul.getElementsByTag("a");
			List<String> suitableList = new ArrayList<String>();
			List<String> unsuitableList = new ArrayList<String>();

			for (Element ele : suitable) {
				// 获取标签链接及名称
				log.debug("链接=" + ele.attr("href") + "   食物名称=" + ele.attr("title"));
				String href = ele.attr("href");
				String foodname = ele.attr("title");
				if (StringUtils.isBlank(foodname)) {
					continue;
				}
				suitableList.add(foodname);
			}

			// 获取不适宜食物
			Element secondul = ul.get(1);
			Elements unsuitable = secondul.getElementsByTag("a");
			for (Element ele : unsuitable) {
				// 获取标签链接及名称
				log.debug("链接=" + ele.attr("href") + "   食物名称=" + ele.attr("title"));
				String href = ele.attr("href");
				String foodname = ele.attr("title");
				if (StringUtils.isBlank(foodname)) {
					continue;
				}
				unsuitableList.add(foodname);
			}

			String suitableFood = null;
			String unsuitableFood = null;
			if (suitableList.size() > 0 && suitableList != null) {
				suitableFood = StringUtils.join(suitableList.toArray(), ",");
			}
			if (unsuitableList.size() > 0 && unsuitableList != null) {
				unsuitableFood = StringUtils.join(unsuitableList.toArray(), ",");
			}

			/**
			 * 入库操作 根据拿到的疾病名称先与库中对比，若存在则更新，若不存在则添加
			 */
			DietWithBLOBs diet = dietService.getDietByDiseasename(name);
			if (diet == null) {
				DietWithBLOBs dietWithBLOBs = new DietWithBLOBs();
				dietWithBLOBs.setCreatetime(new Date());
				dietWithBLOBs.setCrowurl(url);
				dietWithBLOBs.setDatasource("爬虫");
				dietWithBLOBs.setDiseasename(name);
				dietWithBLOBs.setSuitablefood(suitableFood);
				dietWithBLOBs.setUnsuitablefood(unsuitableFood);
				dietService.addDiet(dietWithBLOBs);
			} else {
				diet.setSuitablefood(suitableFood);
				diet.setUnsuitablefood(unsuitableFood);
				dietService.updateDiet(diet);
			}

		} catch (Exception e) {
			log.debug("foodSource exception：{}", e);
		}
	}
}
