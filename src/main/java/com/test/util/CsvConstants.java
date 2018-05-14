package com.test.util;

import java.util.HashSet;

public class CsvConstants {
	
	public static final String LOG_PREFIX = "trapper-zx_domeos_log.log";
	public static final String MOBILE_PREFIX = "trapper-zx_domeos_mobile.log";
	public static final String POINT_PREFIX = "trapper-zx_domeos_point.log";
	public static final HashSet<String> PREFIXS = new HashSet<String>(){
		{
			add(LOG_PREFIX);
			add(MOBILE_PREFIX);
			add(POINT_PREFIX);
		}
	};
	public static final String RESUILT_HEAD = "日志类型,超过50毫秒,超过100毫秒,超过1000毫秒";
	public static final String RESUILT_FORMAT = "%s,%s (%d/%d),%s (%d/%d),%s";
	public static final Integer PARAMS_LENGTH = 8;
	
	public static final String RESULT_FILE_NAME ="统计数据.csv";
	public static final String ORIGIN_FILE_NAME_77 ="zabix77.csv";
	public static final String ORIGIN_FILE_NAME_78 ="zabix78.csv";
	public static final String PATH = "C:/Users/weicui004746/Desktop/文档/zabix统计";
	
	public static final String TEXT_77 = "77机器的统计结果如下,";
	public static final String TEXT_77_MORE = "77机器的统计结果如下,(trapper-zx_domeos_point.log超过8%)";
	
	public static final String TEXT_78 = "78机器的统计结果如下,";
	public static final String TEXT_78_MORE = "78机器的统计结果如下,(trapper-zx_domeos_point.log超过8%)";
	
	public static final String SUM_TEXT_POINT = "trapper-zx_domeos_point.log有超过8%！！！";
	public static final String SUM_TEXT_LOG = "trapper-zx_domeos_point.log有超过0.1%！！！";
	
	public static final String DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss";
	
	public static final Integer NO_MORETHAN = 0;
	public static final Integer POINT_MORETHAN_8 = 1;
	public static final Integer LOG_MORETHAN_01 = 2;

}
