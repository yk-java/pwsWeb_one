package com.glens.pwCloudOs.cj.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {
	//导入excel
		public static List importExcel(MultipartFile multiFile) {
			
			List<LinkedHashMap<String, Object>> res=null;
			
			try {
				res= readExcel(multiFile.getInputStream());
				for (Iterator<LinkedHashMap<String, Object>> iterator = res.iterator(); iterator.hasNext();) {
					LinkedHashMap<String, Object> object = iterator.next();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return res;
		}
		
		public static  Workbook createWorkbook(InputStream in) throws     
	    IOException,InvalidFormatException {
	        if (!in.markSupported()) {
	            in = new PushbackInputStream(in, 8);
	        }
	        if (POIFSFileSystem.hasPOIFSHeader(in)) {
	            return new HSSFWorkbook(in);
	        }
	        if (POIXMLDocument.hasOOXMLHeader(in)) {
	            return new XSSFWorkbook(OPCPackage.open(in));
	        }
	        throw new IllegalArgumentException("你的excel版本目前poi解析不了");

	    }

		private static List<LinkedHashMap<String, Object>> readExcel(InputStream ins){
			Workbook workbook =null;
			
			List<LinkedHashMap<String, Object>> res = new ArrayList<LinkedHashMap<String, Object>>();
			try {
				workbook =createWorkbook(ins);	 
				/* Get first sheet */
				Sheet sheet = workbook.getSheetAt(0);
				/* Get first row, get table headers */
				List<String> ths = new ArrayList<String>();
				Row rowFirst = sheet.getRow(0);
				for(int i=0; true; i++){
					Cell cell = rowFirst.getCell(i);
					if(cell==null){
						break;
					}
					String val = cell.getStringCellValue();
					ths.add(val);
				}
				
				
				Row row;
				LinkedHashMap<String, Object> bean;
				for (int i = 1; true; i++) {

					/* Get row by index */
					row = sheet.getRow(i);
					if (row != null) {
						bean = new LinkedHashMap<String, Object>();
						/* Set value to bean */
						for (int j=0; j<ths.size(); j++) {
							String th = ths.get(j);
							/* Get cell value */
							Cell cell = row.getCell(j);
							if(cell!=null){
								String val;
								try {
									val =cell.toString();						 
									//cell.getNumericCellValue()
									//cell.getStringCellValue();
								} catch (Exception e) {
									Date val_d = cell.getDateCellValue();
									val = new SimpleDateFormat("yyyy-MM-dd").format(val_d);
								}
								if(StringUtils.isEmpty(val) ){
									val=null;
								}
								bean.put(th, val);
							}else{
								bean.put(th, null);
							}
						}
						res.add(bean);
					} else {
						/* Done, Current row is null, break */
						break;
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("ERROR: Get data error", e);
			}
			return res;
		}
}
