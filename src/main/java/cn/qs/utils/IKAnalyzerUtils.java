package cn.qs.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.qs.exceptionHandler.MyExceptionHandler;

public class IKAnalyzerUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);

	public static void main(String[] args) {
		List<String> testTokenStream = testTokenStream("这是什么意思什么鬼");
		System.out.println(testTokenStream);
	}

	/**
	 * 分词
	 * 
	 * @param str
	 *            需要分词的字符串
	 * @return 分词后的集合
	 */
	public static List<String> testTokenStream(String str) {
		List<String> result = new ArrayList<>();

		try {
			// 创建一个标准分析器对象
			Analyzer analyzer = new IKAnalyzer();
			// 获得tokenStream对象
			// 第一个参数：域名，可以随便给一个
			// 第二个参数：要分析的文本内容
			TokenStream tokenStream = analyzer.tokenStream("test", str);
			// 添加一个引用，可以获得每个关键词
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			// 添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			// 将指针调整到列表的头部
			tokenStream.reset();
			// 遍历关键词列表，通过incrementToken方法判断列表是否结束
			while (tokenStream.incrementToken()) {
				// 关键词的起始位置
				// System.out.println("start->" +
				// offsetAttribute.startOffset());
				// 取关键词
				// System.out.println(charTermAttribute);
				result.add(charTermAttribute.toString());
				// 结束位置
				// System.out.println("end->" + offsetAttribute.endOffset());
			}
			tokenStream.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}
}
