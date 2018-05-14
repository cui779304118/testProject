package com.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ReadCsvFileTest {
	private static Integer ROWLENGTH = 4;
	private static Integer DATA_NUMS = 12;
	
	public List<Integer> parseCsv(List<String> impResults){
		List<Integer> dataList = new ArrayList<Integer>(); 
		String [] row = new String [ROWLENGTH];
		for(String s : impResults){
			row = s.split(",");
			if(row.length != ROWLENGTH){
				continue;
			}
			if(!StringUtils.isNumeric(row[ROWLENGTH-1])){
				System.out.println("csv格式有错，第4列不为数字");
				return null;
			}
			dataList.add(Integer.valueOf(row[ROWLENGTH-1]));
		}
		return dataList;
	}
	
	public void computeCsv(String fileName){
		List<String> impResults = importCsv(fileName);
		List<Integer> dataList = parseCsv(impResults);
		List<Float> resultList = new ArrayList<Float>();
		
		int length = dataList.size();
		if(length != DATA_NUMS){
			return;
		}
		float temp = 0;
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.0000");  
		for(int beginIndex = 0; beginIndex < DATA_NUMS; beginIndex += 4){
			for(int index = beginIndex + 1; index <beginIndex + 4;index++){
				temp =(float) dataList.get(index)/dataList.get(beginIndex);
				df.format(temp);
				resultList.add(temp);
			}
		}
		System.out.println();
		
	}
	
	public  List<String> importCsv(String fileName) {
        List<String> dataList = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
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
