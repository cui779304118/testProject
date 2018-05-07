package com.test.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReadCsvFile {
	
	public static void main(String [] args){
		System.out.println("==================统计计算开始==================");
		Long beginTime = System.currentTimeMillis();
		
		ReadCsvFile rcf = new ReadCsvFile();
		List<String> resultRows77 = rcf.tongJiZabbix(CsvConstants.ORIGIN_FILE_NAME_77);
		List<String> resultRows78 = rcf.tongJiZabbix(CsvConstants.ORIGIN_FILE_NAME_78);
		List<String> resultRows = new ArrayList<String>();
		
		resultRows.add(CsvConstants.TEXT_77);
		resultRows.addAll(resultRows77);
		resultRows.add(",");
		resultRows.add(CsvConstants.TEXT_78);
		resultRows.addAll(resultRows78);
		
		if(rcf.isMorethan8Or01(resultRows77) == CsvConstants.POINT_MORETHAN_8 
				&& rcf.isMorethan8Or01(resultRows78) == CsvConstants.POINT_MORETHAN_8){
			resultRows.add(",");
			resultRows.add(CsvConstants.SUM_TEXT_POINT);
		}
		
		if(rcf.isMorethan8Or01(resultRows77) == CsvConstants.LOG_MORETHAN_01 
				&& rcf.isMorethan8Or01(resultRows78) == CsvConstants.LOG_MORETHAN_01){
			resultRows.add(",");
			resultRows.add(CsvConstants.SUM_TEXT_LOG);
		}
		
		SimpleDateFormat sf = new SimpleDateFormat(CsvConstants.DATE_FORMAT);
		String date = sf.format(new Date());
		System.out.println(date);
		rcf.exportCsv(resultRows, CsvConstants.PATH + File.separator + date + CsvConstants.RESULT_FILE_NAME);
		Long endTime = System.currentTimeMillis();
		System.out.println("=============统计计算完成！耗时： " + (endTime - beginTime) + "毫秒==============");
	}
	
	public int isMorethan8Or01(List<String> resultRows){
		int flag = CsvConstants.NO_MORETHAN;
		String pointRow = resultRows.get(resultRows.size() - 1);
		String pointMore50 = pointRow.split(",")[2];
		
		String logRow = resultRows.get(1);
		String logMore50 = logRow.split(",")[2];
		
		if(Integer.valueOf(pointMore50.charAt(0))>8){
			flag = CsvConstants.POINT_MORETHAN_8;
		}
		
		if(Integer.valueOf(logMore50.charAt(0))>0.1){
			flag = CsvConstants.POINT_MORETHAN_8;
		}
		return flag;
	}
	
	public List<String> tongJiZabbix(String fileName){
		List<String> impResults = importCsv(CsvConstants.PATH + File.separator + fileName);
		Map<String,List<Integer>> dataMap = parse2Map(impResults);
		Map<String,List<String>> resultMap = computeCsv(dataMap);
		List<String> resultRows = formatResutl(resultMap, dataMap);
		return resultRows;
	}
	
	public Map<String,List<Integer>> parse2Map(List<String> impResults){
		Map<String,List<Integer>> dataMap = new HashMap<String,List<Integer>>();
		for(String s : impResults){
			String [] row = s.split(",");
			
			if(CsvConstants.PREFIXS.contains(row[0])){
				List<Integer> dataList = new ArrayList<Integer>();
				dataMap.put(row[0], dataList);
				continue;
			}
			if(row[0].contains("domeos_log") && !row[0].contains("20")){
				dataMap.get(CsvConstants.LOG_PREFIX).add(Integer.valueOf(row[1]));
				continue;
			}
			if(row[0].contains("domeos_mobile") && !row[0].contains("20")){
				dataMap.get(CsvConstants.MOBILE_PREFIX).add(Integer.valueOf(row[1]));
				continue;
			}
			if(row[0].contains("domeos_point") && !row[0].contains("20")){
				dataMap.get(CsvConstants.POINT_PREFIX).add(Integer.valueOf(row[1]));
				continue;
			}
		}
		return dataMap;
	}
	
	public Map<String,List<String>> computeCsv(Map<String,List<Integer>> dataMap){
		Map<String,List<String>> resultMap = new HashMap<String,List<String>>();
		Set<String> keySet = dataMap.keySet();
		for(String key : keySet){
			System.out.println(key + "====计算开始！");
			List<Integer> dataList = dataMap.get(key);
			List<String> resultList = new ArrayList<String>();
			int total = dataList.get(0);
			BigDecimal totalDec = new BigDecimal(String.valueOf(total));
			for(int i = 1,len = dataList.size(); i<len; i++){
				BigDecimal dataDec = new BigDecimal(String.valueOf(dataList.get(i)));
				for(int j = i + 1; j<len; j++){
					dataDec = dataDec.add(new BigDecimal(String.valueOf(dataList.get(j))));
				}
				BigDecimal resultDec = dataDec.divide(totalDec, 4, BigDecimal.ROUND_HALF_DOWN);
				System.out.println(key + "====计算过程数据：" + dataDec.toString() + "除以" + totalDec.toString() + "结果是：" + resultDec.toString());
				String resultStr = resultDec.multiply(new BigDecimal(100)) + "%";
				resultList.add(resultStr);
			}
			resultMap.put(key, resultList);
		}
		return resultMap;
	}
	
	public List<String> formatResutl(Map<String,List<String>> resultMap, Map<String,List<Integer>> dataMap){
		List<String> resultRows = new ArrayList<String>();
		Set<String> keySet = resultMap.keySet();
		String tempRow = "";
		List<String> resultList = null;
		List<Integer> dataList = null;
		
		resultRows.add(CsvConstants.RESUILT_HEAD);
		for(String key : keySet){
			resultList = resultMap.get(key);
			dataList = dataMap.get(key);
			
			Integer more50 = dataList.get(1) + dataList.get(2) +dataList.get(3);
			Integer more100 = dataList.get(2) +dataList.get(3);
			tempRow = String.format(CsvConstants.RESUILT_FORMAT, key,
					resultList.get(0),more50,dataList.get(0),
					resultList.get(1),more100,dataList.get(0),
					resultList.get(2));
			resultRows.add(tempRow);
		}
		return resultRows;
	}
	
	public void exportCsv(List<String> rows, String path){
		int rowNums = rows.size();
		File file = new File(path);
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"gbk"));
			for(int i = 0; i < rowNums; i++){
				out.write(rows.get(i));
				out.newLine();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public  List<String> importCsv(String fileName) {
        List<String> dataList = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
                    dataList.add(line);
            }
        } catch (Exception e) {
            System.out.println("读取csv文件异常！fileName =" + file.toString() + ",exception=" + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }

}
