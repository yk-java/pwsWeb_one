package com.glens.eap.platform.framework.utils.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	private SXSSFWorkbook workbook;
	
	private String sheetName;

	public ExcelHelper() {
	}
	public void writeData(String id, OutputStream out, Class clazz, List data){
		/* Read data structure */
		Map<String, Map<String, Object>> dataStruct = DataStructReader
				.getDataStruct();
		/* Get bean structure by class name */
		Map<String, Object> beanStruct = dataStruct.get(id);
		writeData(beanStruct, out, clazz, data);
	
	}
	public void writeData(OutputStream out, Class clazz, List data){
		/* Read data structure */
		Map<String, Map<String, Object>> dataStruct = DataStructReader
				.getDataStruct();
		/* Get bean structure by class name */
		Map<String, Object> beanStruct = dataStruct.get(clazz.getName());
		writeData(beanStruct, out, clazz, data);
	}
	
	public <T> List<T> getData(String id, InputStream ins, String fileName, Class<T> clazz) throws RuntimeException, IOException {
		/* Read data structure */
		Map<String, Map<String, Object>> dataStruct = DataStructReader
				.getDataStruct();
		/* Get bean structure by id */
		Map<String, Object> beanStruct = dataStruct.get(id);
		int postfixBeginIdx = fileName.lastIndexOf(".");
		String postfix = "";
		if(postfixBeginIdx != -1){
			postfix = fileName.substring(postfixBeginIdx+1);
		}
		if("xlsx".equals(postfix)){
			return getXSSFData(beanStruct, ins, clazz);
		}else if("xls".equals(postfix)){
			return getHSSFData(beanStruct, ins, clazz);
		}else{
			throw new RuntimeException("不支持的文件类型");
		}
	}
	
	public <T> List<T> getData(InputStream ins, String fileName, Class<T> clazz) throws RuntimeException, IOException {
		/* Read data structure */
		Map<String, Map<String, Object>> dataStruct = DataStructReader
				.getDataStruct();
		/* Get bean structure by class name */
		Map<String, Object> beanStruct = dataStruct.get(clazz.getName());
		int postfixBeginIdx = fileName.lastIndexOf(".");
		String postfix = "";
		if(postfixBeginIdx != -1){
			postfix = fileName.substring(postfixBeginIdx+1);
		}
		if("xlsx".equals(postfix)){
			return getXSSFData(beanStruct, ins, clazz);
		}else if("xls".equals(postfix)){
			return getHSSFData(beanStruct, ins, clazz);
		}else{
			throw new RuntimeException("不支持的文件类型");
		}
	}
	
	public void writeData(Map<String, Object> beanStruct, OutputStream out, Class clazz, List data){
		
		workbook = new SXSSFWorkbook();
		try{
			/* Get sheet from configuration */
			//String sheetName = //(String) beanStruct.get("sheet-name");
			if (sheetName == null || "".equals(sheetName)) {
				
				sheetName = (String) beanStruct.get("sheet-name");
			}
			// Create sheet
	        SXSSFSheet sheet = workbook.createSheet(sheetName);
	        sheet.setDefaultColumnWidth((short) 15);
	        // Table Header Style
	        CellStyle thStyle = workbook.createCellStyle();
	        thStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	        thStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        /*thStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        thStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        thStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        thStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);*/
	        thStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        // Table Header Font
	        Font thFont = workbook.createFont();
	        //thFont.setColor(HSSFColor.VIOLET.index);
	        thFont.setFontHeightInPoints((short) 12);
	        thFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        // Set font
	        thStyle.setFont(thFont);
	        // Table Data Style
	        CellStyle tdStyle = workbook.createCellStyle();
	        tdStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	        tdStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        tdStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        tdStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        tdStyle.setBorderRight(CellStyle.BORDER_THIN);
	        tdStyle.setBorderTop(CellStyle.BORDER_THIN);
	        tdStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        tdStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        // Table Data Font
	        Font tdFont = workbook.createFont();
	        tdFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
	        // Set font
	        tdStyle.setFont(tdFont);
	
	        
//	        Drawing patriarch = sheet.createDrawingPatriarch();
//	        // Create Comment
//	        HSSFComment docComment = patriarch.createCellComment(new )createComment(new HSSFClientAnchor(0,
//	                0, 0, 0, (short) 4, 2, (short) 6, 5));
//	        // Comment content
//	        docComment.setString(new HSSFRichTextString(""));
//	        // Author
//	        docComment.setAuthor("");
	
	        // Create table header
	        SXSSFRow th = sheet.createRow(0);
	        List<Map<String, Object>> properties = (List<Map<String, Object>>) beanStruct.get("properties");
	        for (Map<String, Object> property : properties) {
	        	String name=property.get("name").toString();
	            String comment = name;
	            if(property.get("comment")!=null)
	            	comment = property.get("comment").toString();
	            /* Get column index */
				int col_index = (Integer) property.get("col-index");
	        	SXSSFCell cell = th.createCell((short)col_index);
	            cell.setCellStyle(thStyle);
	            XSSFRichTextString text = new XSSFRichTextString(comment);
	            cell.setCellValue(text);
	        }
	
	        // Data
	        for (int i = 0; i < data.size(); i++) {
	        	int rowIndex=i+1;
	        	Object bean = data.get(i);
	        	SXSSFRow row = sheet.createRow(rowIndex);
	        	/* Set value to bean */
				for (Map<String, Object> property : properties) {
					
					/* Get column index */
					int col_index = (Integer) property.get("col-index");
					/* Get cell type */
					String cell_type = (String) property.get("cell-type");
					/* Get property's name and type */
					String propertyName = (String) property.get("name");
					String type = (String) property.get("type");
					String format = (String) property.get("format");
					/* Get value from bean's property */
					Object cellVal = getValFromBean(bean, propertyName);
					
					/* Set cell value */
					setCellValue(row, col_index, cell_type, cellVal, type, format);
	
				}
			}
           workbook.write(out);
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException("ERROR: Write data error, ClassName:"
					+ clazz.getName(), e);
        }
	}
	/* XSSF */
	private <T> List<T> getXSSFData(Map<String, Object> beanStruct, InputStream ins, Class<T> clazz) throws IOException{
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(ins);
		List<T> res = new ArrayList<T>();
		try {
			/* Get sheet from configuration */
			String sheetName = (String) beanStruct.get("sheet-name");
			XSSFSheet sheet = xssfWorkbook.getSheet(sheetName);
			if (sheet == null) {
				throw new RuntimeException("ERROR: Not found sheet \""
						+ sheetName + "\"");
			}

			/* Get properties */
			List<Map<String, Object>> properties = (List<Map<String, Object>>) beanStruct
					.get("properties");
			int start_row_index = (Integer) beanStruct.get("start-row-index");

			/* Get bean data by properties info from sheet */
			XSSFRow row;
			T bean;
			for (int i = start_row_index; true; i++) {
				if(i>10000){// 最大不能超过10000条
					throw new RuntimeException("数据量不能超过10000条");
				}
				/* Get row by index */
				row = sheet.getRow(i);
				if (row != null) {

					/* A row data is a bean data */

					/* Instance a bean */
					bean = clazz.newInstance();

					/* Set value to bean */
					for (Map<String, Object> property : properties) {

						/* Get column index */
						int col_index = (Integer) property.get("col-index");

						/* Get cell type */
						String cell_type = (String) property.get("cell-type");

						/* Get cell value */
						Object cellVal = getCellValue(row, col_index, cell_type);

						/* Get property's name and type */
						String propertyName = (String) property.get("name");
						String type = (String) property.get("type");
						String format = (String) property.get("format");
						Boolean not_null = (Boolean) property.get("not-null");

						/* Set value to bean's property */
						setValToBean(bean, propertyName, type, cellVal,
								not_null, format);

					}
					res.add(bean);
				} else {

					/* Current row is null, break */
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Get data error, ClassName:"
					+ clazz.getName(), e);
		}
		xssfWorkbook.close();
		return res;

	}
	/* HSSF */
	private <T> List<T> getHSSFData(Map<String, Object> beanStruct, InputStream ins, Class<T> clazz) throws IOException{
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(ins);
		List<T> res = new ArrayList<T>();
		try {
			/* Get sheet from configuration */
			String sheetName = (String) beanStruct.get("sheet-name");
			HSSFSheet sheet = hssfWorkbook.getSheet(sheetName);
			if (sheet == null) {
				throw new RuntimeException("ERROR: Not found sheet \""
						+ sheetName + "\"");
			}

			/* Get properties */
			List<Map<String, Object>> properties = (List<Map<String, Object>>) beanStruct
					.get("properties");
			int start_row_index = (Integer) beanStruct.get("start-row-index");

			/* Get bean data by properties info from sheet */
			HSSFRow row;
			T bean;
			for (int i = start_row_index; true; i++) {
				if(i>10000){// 最大不能超过10000条
					throw new RuntimeException("数据量不能超过10000条");
				}
				/* Get row by index */
				row = sheet.getRow(i);
				if (row != null) {

					/* A row data is a bean data */

					/* Instance a bean */
					bean = clazz.newInstance();

					/* Set value to bean */
					for (Map<String, Object> property : properties) {

						/* Get column index */
						int col_index = (Integer) property.get("col-index");

						/* Get cell type */
						String cell_type = (String) property.get("cell-type");

						/* Get cell value */
						Object cellVal = getCellValue(row, col_index, cell_type);

						/* Get property's name and type */
						String propertyName = (String) property.get("name");
						String type = (String) property.get("type");
						String format = (String) property.get("format");
						Boolean not_null = (Boolean) property.get("not-null");

						/* Set value to bean's property */
						setValToBean(bean, propertyName, type, cellVal,
								not_null, format);

					}
					res.add(bean);
				} else {

					/* Current row is null, break */
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Get data error, ClassName:"
					+ clazz.getName(), e);
		}
		hssfWorkbook.close();
		return res;

	}
	
	/* Set value to bean's property */
	private void setValToBean(Object bean, String property, String type,
			Object val, Boolean not_null, String format) throws RuntimeException {
		try {
			String setMethodName = "set"
					+ (property.substring(0, 1)).toUpperCase()
					+ property.substring(1);
			Class propertyType = Class.forName(type);
			Method method = bean.getClass().getMethod(setMethodName,
					propertyType);
			if (not_null && val == null) {
				throw new RuntimeException("ERROR: Cell value is null, Bean:"
						+ bean.getClass().getName() + ", Property:" + property
						+ ", Type:" + type + ", Val:" + val);
			}
			if (val != null) {
				if(propertyType.equals(java.util.Date.class)){
					val = new SimpleDateFormat(format).parse(val.toString());
				}else if(val != null && propertyType.equals(java.lang.Float.class) && val.getClass().equals(java.lang.String.class)){
					val = Float.parseFloat(val.toString());
				}else if(val != null && propertyType.equals(java.lang.Double.class) && val.getClass().equals(java.lang.String.class)){
					val = Double.parseDouble(val.toString());
				}else if(val != null && propertyType.equals(java.lang.Integer.class) && val.getClass().equals(java.lang.String.class)){
					val = Integer.parseInt(val.toString());
				}
				if(bean.getClass().equals(java.util.Map.class) || bean.getClass().equals(java.util.HashMap.class)){
					Map map = (Map)bean;
					map.put(property, val);
				}else{
					method.invoke(bean, propertyType.cast(correntType(propertyType,
						val)));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Set value to bean error, Bean:"
					+ bean.getClass().getName() + ", Property:" + property
					+ ", Type:" + type + ", Val:" + val, e);
		}
	}
	/* Get value from bean */
	private Object getValFromBean(Object bean, String property){
		try {
			if(bean.getClass().equals(java.util.Map.class) || bean.getClass().equals(java.util.HashMap.class)){
				Map map = (Map)bean;
				return map.get(property);
			}
			String getMethodName = "get"
					+ (property.substring(0, 1)).toUpperCase()
					+ property.substring(1);
			Method method = bean.getClass().getMethod(getMethodName);
			Object val = method.invoke(bean);
			return val;
		} catch (Exception e) {
			throw new RuntimeException("ERROR: Get value from bean error, Bean:"
					+ bean.getClass().getName() + ", Property:" + property, e);
		}
	}
	
	/* Correct type */
	private Object correntType(Class propertyType, Object val) {
		if (propertyType.equals(Integer.class)) {
			val = Integer.parseInt(val.toString().substring(0,
					val.toString().lastIndexOf(".")));
		}else if(propertyType.equals(Float.class)){
			val = Float.parseFloat(val.toString());
		}else if(propertyType.equals(Double.class)){
			val = Double.parseDouble(val.toString());
		}
		return val;
	}

	/* Get cell value */
	private Object getCellValue(Row row, int col_index, String cell_type)
			throws RuntimeException {
		Object cellVal = null;
		try {
			Cell cell = row.getCell((short) col_index);
			if (cell != null) {
				/* Get value by cell type */
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if(DateUtil.isCellDateFormatted(cell)){
						Date val = cell.getDateCellValue();
						cellVal = new SimpleDateFormat("yyyy-MM-dd").format(val);
					}else{
						cellVal = cell.getNumericCellValue();
					}
					break;
				case Cell.CELL_TYPE_STRING:
					cellVal = cell.getRichStringCellValue().getString();
					break;
				case Cell.CELL_TYPE_FORMULA:
					cellVal = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_BLANK:
					cellVal = null;
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					cellVal = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_ERROR:
					cellVal = cell.getErrorCellValue();
					break;
				}
			} else {
				cellVal = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"ERROR: Get cell value error, Col-index:" + col_index, e);
		}
		return cellVal;
	}
	
	/* Set cell value */
	private void setCellValue(SXSSFRow row, int col_index, String cell_type, Object val, String type, String format)
			throws RuntimeException {
		try {
			SXSSFCell cell = row.createCell((short) col_index);
			if(val==null)
				return;
			Class propertyType = Class.forName(type);
			if("CELL_TYPE_NUMERIC".equals(cell_type)){
				cell.setCellType(SXSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(Double.parseDouble(val.toString()));
			}else if("CELL_TYPE_STRING".equals(cell_type)){
				cell.setCellType(SXSSFCell.CELL_TYPE_STRING);
				String strVal=val.toString();
				if(val.getClass().equals(java.util.Date.class)){
					if(format==null){
						format="yyyy-MM-dd HH:mm:ss";
					}
					strVal=new SimpleDateFormat(format).format((java.util.Date)val);
				}
				cell.setCellValue(new XSSFRichTextString(strVal));
			}else if("CELL_TYPE_FORMULA".equals(cell_type)){
				cell.setCellType(SXSSFCell.CELL_TYPE_FORMULA);
				cell.setCellValue(new XSSFRichTextString((String)val));// TODO not test
			}else if("CELL_TYPE_BLANK".equals(cell_type)){
				cell.setCellType(SXSSFCell.CELL_TYPE_BLANK);
			}else if("CELL_TYPE_BOOLEAN".equals(cell_type)){
				cell.setCellType(SXSSFCell.CELL_TYPE_BOOLEAN);
				if(propertyType.equals(Boolean.class)){
					cell.setCellValue((Boolean)val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"ERROR: Set cell value error, Col-index:" + col_index, e);
		}
	}

	public final static String Author = "Makeruike";
	public final static String Version = "1.0";

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
