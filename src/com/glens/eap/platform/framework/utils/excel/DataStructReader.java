package com.glens.eap.platform.framework.utils.excel;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DataStructReader {
	private static final String CONFIG_FILE_PATH = "/data-structure.xml";
	private static Map<String, Map<String, Object>> dataStructure = new HashMap<String, Map<String, Object>>();
	private static String configFile = CONFIG_FILE_PATH;
	private static Logger logger = Logger.getLogger(DataStructReader.class);

	public static Map<String, Map<String, Object>> getDataStruct() {
		if (dataStructure.size() == 0) {
			readerDataStruct();
		}
		return dataStructure;
	}

	private static void readerDataStruct() throws RuntimeException {
		try {
			InputStream in = DataStructReader.class
					.getResourceAsStream(configFile);
			if (in == null) {
				throw new RuntimeException(
						"ERROR: Not found the data structure file \""
								+ configFile + "\"");
			}
			SAXReader reader = new SAXReader();
			/*
			 * Load configuration file
			 */
			Document doc = reader.read(in);
			in.close();
			Element root = doc.getRootElement();
			dataStructure.clear();
			/*
			 * Read bean structure
			 */
			Iterator it_beans = root.elementIterator();
			boolean flag = false;
			while (it_beans.hasNext()) {
				Element element_bean = (Element) it_beans.next();
				if (element_bean.getName().toLowerCase().equals("bean")) {
					flag = true;
					String id = element_bean.attributeValue("id");
					String className = element_bean.attributeValue("class");
					String sheet_name = element_bean
							.attributeValue("sheet-name");
					Integer start_row_index = Integer.parseInt(element_bean
							.attributeValue("start-row-index"));
					Map<String, Object> bean = new HashMap<String, Object>();
					bean.put("id", id);
					bean.put("class", className);
					bean.put("sheet-name", sheet_name);
					bean.put("start-row-index", start_row_index);
					/*
					 * Read property info
					 */
					Iterator it_properties = element_bean.elementIterator();
					List<Map<String, Object>> properties = new ArrayList<Map<String, Object>>();
					int proIndex=0;
					while (it_properties.hasNext()) {
						Element element_property = (Element) it_properties
								.next();
						if (element_property.getName().toLowerCase().equals(
								"property")) {
							String name = element_property
									.attributeValue("name");
							String type = element_property
									.attributeValue("type");
							Integer col_index = proIndex;
							if(element_property
									.attributeValue("col-index")!=null){
								col_index = Integer
									.parseInt(element_property
											.attributeValue("col-index"));
							}
							String cell_type = element_property
									.attributeValue("cell-type");
							String comment = element_property.attributeValue("comment");
							Boolean not_null = false;
							if(element_property.attributeValue(
									"not-null")!=null){
								not_null = element_property.attributeValue(
										"not-null").equals("true");
							}
							String format = element_property.attributeValue("format");
							Map<String, Object> property = new HashMap<String, Object>();
							property.put("name", name);
							property.put("type", type);
							property.put("col-index", col_index);
							property.put("cell-type", cell_type);
							property.put("comment", comment);
							property.put("not-null", not_null);
							property.put("format", format);
							/*
							 * sub-property
							 */
							Iterator it_sub_properties = element_property
									.elementIterator();
							List<Map<String, Object>> sub_properties = null;
							if (it_sub_properties.hasNext()) {
								sub_properties = new ArrayList<Map<String, Object>>();
							}
							while (it_sub_properties.hasNext()) {
								Element element_sub_property = (Element) it_sub_properties
										.next();
								if (element_sub_property.getName()
										.toLowerCase().equals("sub-property")) {
									String sub_name = element_sub_property
											.attributeValue("name");
									String sub_type = element_sub_property
											.attributeValue("type");
									Boolean sub_not_null = element_sub_property
											.attributeValue("not-null").equals(
													"true");
									Map<String, Object> sub_property = new HashMap<String, Object>();
									sub_property.put("name", sub_name);
									sub_property.put("type", sub_type);
									sub_property.put("not-null", sub_not_null);
									sub_properties.add(sub_property);
								}
							}
							property.put("sub-properties", sub_properties);
							properties.add(property);
						}
						proIndex++;
					}
					bean.put("properties", properties);
					if(id==null){
						id=className;
					}
					dataStructure.put(id, bean);
				}
			}
			if (!flag) {
				throw new RuntimeException(
						"ERROR: Not found tag like <bean ...");
			}
		} catch (Exception e) {
			logger.error("Read data structure error", e);
			throw new RuntimeException("ERROR: Read data structure error", e);
		}
	}

	/* Test */
	public static void main(String[] args) {
		Map<String, Map<String, Object>> dataStruct = DataStructReader
				.getDataStruct();
		/*
		 * All data structure
		 */
		for (String key : dataStruct.keySet()) {
			System.out.println(key);
		}
		System.out.println("-------------------------");
		/*
		 * Detail
		 */
		Map<String, Object> beanStruct = dataStruct
				.get("com.yaao.mdx.util.databean.TeacherBean");

		/* Bean's properties */
		for (String key : beanStruct.keySet()) {
			System.out.println(key + "=" + beanStruct.get(key));
		}
		System.out.println("-------------------------");
		/* Properties */
		List<Map<String, Object>> properties = (List<Map<String, Object>>) beanStruct
				.get("properties");
		for (Map<String, Object> property : properties) {
			for (String key : property.keySet()) {
				System.out.println(key + "=" + property.get(key));
			}
			System.out.println();
		}
	}
	
	public final static String Author="Makeruike";
	public final static String Version="1.0";
}
