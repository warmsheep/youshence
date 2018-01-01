package com.chanpay.cloud.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel导入工具类
 * @author warmsheep
 */
public class ExcelImportUtil {
	
	/**
	 * Excel文件内容读取
	 * @param input 文件读取流
	 * @param isExcel07 是否2007版本（true是；false否）
	 * @param startRow 数据读取开始行（默认从0开始）
	 * @param length 结果集大小
	 * @return
	 * @version 1.0,2015年12月4日 下午2:42:52,Liugl,Ins
	 */
	public static List<String[]> readExcel(InputStream input, boolean isExcel07, int startRow, int length) {
		List<String[]> data = new ArrayList<String[]>();
		Workbook wb  = null;
		try {
			//根据文件格式(2003或者2007)来初始化
			if(isExcel07)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0);				//获得第一个表单
			Iterator<Row> rows = sheet.rowIterator();	//获得第一个表单的迭代器
			String[] str = null;
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
			sheet.getLastRowNum();
			while (rows.hasNext()) {
				Row row = rows.next();//获得行数据
				
				if (startRow > 0) {
					startRow--;
					continue;
				}
				
				Iterator<Cell> cells = row.cellIterator();//获得第一行的迭代器
				int cellCount = row.getLastCellNum();
				
				str = new String[length];
				for(int i = 0; i < cellCount; i ++){
					Cell cell = row.getCell(i);
					if(cell == null){
						str[i] = "";
						continue;
					}
					//根据cell中的类型来输出数据
					switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								//处理日期格式、时间格式
								if (null != cell.getCellStyle().getDataFormatString() && "yyyymmdd".equals(cell.getCellStyle().getDataFormatString()))
									str[i] = sdfDate.format(cell.getDateCellValue());
								else if (null != cell.getCellStyle().getDataFormatString() && "hhmmss".equals(cell.getCellStyle().getDataFormatString()))
									str[i] = sdfTime.format(cell.getDateCellValue());
							} else if (cell.getCellStyle().getDataFormat() == 58) {
								//处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
								final double value = cell.getNumericCellValue();  
								final Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
								str[i] = formatDate.format(date);
							} else {
								//数值
								if (StringUtils.isNotBlank(cell.getNumericCellValue()+"")){
									cell.setCellType(Cell.CELL_TYPE_STRING); 
									str[i] = String.valueOf(cell.getStringCellValue());
									if(str[i] != null && !"".equals(str[i]) && str[i].indexOf(",") != -1){
										str[i] = str[i].replaceAll(",", "");
									}
								}
							}
							break;
						case HSSFCell.CELL_TYPE_STRING:
							str[i] = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							if (StringUtils.isNotBlank(cell.getBooleanCellValue()+""))
								str[i] = String.valueOf(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							str[i] = cell.getCellFormula();
							break;
						default:
							str[i] = "";
							System.out.println("UnSuported Cell Type "+">> "+i);
						break;
					}//end switch
					
				}
				
				
				//结果集组装
				data.add(str);
			}//end while
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if(wb != null){
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		String dir = "C:\\Users\\warms\\Downloads\\QRCODE_20170707_10000026.result.xlsx";
		File file = new File(dir);
		InputStream input = new FileInputStream(file);
		List<String[]> data = readExcel(input, true, 1, 8);
		System.out.println("结果集大小:"+data.size());
		StringBuilder sel = new StringBuilder().append("select * from cp_ac_remit_flow where id not in (select id from cp_ac_remit_flow where ");
		int i = 0;
		for (String[] ss : data) {
			i++;
			StringBuilder sb = new StringBuilder();
			for(String s : ss){
				sb.append(s).append("|");
			}
			System.out.println(sb.toString());
			sel.append("(account_name = '").append(ss[1]).append("' and ")
			.append("account_no = '").append(ss[0]).append("' and ")
			.append("receive_amount = ").append(ss[2]).append(" and ")
			.append("remit_batch_id =").append("1692").append(" ) OR ") ;
		}
		sel.append(") and remit_batch_id = ").append("1692");
		System.out.println(sel);
		System.out.println(i);
	}
	
}
