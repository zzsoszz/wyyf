package com.lys.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lys.system.filter.SafetyFilter;

public class ExcelUtil {
	/**
	 * 解析Excel文件流
	 * 
	 * @param id
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static Map<Integer,List<Map<String, String>>> readXls(InputStream is) throws IOException {
		Map<Integer,List<Map<String, String>>> map = new HashMap<Integer,List<Map<String, String>>>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		SafetyFilter.logger.info("解析Excel文件中...");
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			List<Map<String, String>> objs = new LinkedList<Map<String, String>>();
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum < hssfSheet.getPhysicalNumberOfRows(); rowNum++) {
				Map<String, String> obj = new HashMap<String, String>();
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				// 循环列Cell
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell((short) cellNum);
					if (hssfCell == null) {
						continue;
					}
					obj.put(String.valueOf(cellNum), getValue(hssfCell));
				}
				objs.add(obj);
			}
			map.put(numSheet, objs);
		}
		SafetyFilter.logger.info("解析Excel文件完毕！");
		return map;
	}

	/**
	 * 获取Excel中cell值
	 * 
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
