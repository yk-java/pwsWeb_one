/**

 * @Title: FileUtil.java

 * @Package com.glens.fileServer.utils

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * @author 

 * @date 2016-5-11 下午3:44:30

 * @version V1.0

 **/

package com.glens.eap.platform.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: FileUtil
 * 
 * @Description: TODO
 * 
 * @author
 * 
 * @date 2016-5-11 下午3:44:30
 **/

public abstract class FileUtil {

	private static final int BUFFER_SIZE = 1024;

	/**
	 * 
	 * readFile(这里用一句话描述这个方法的作用)
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * 
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 * 
	 * 
	 * 
	 * @Title: readFile
	 * 
	 * @Description: 读取文件得到文件字节
	 * 
	 * @param @param file
	 * @param @return 设定文件
	 * 
	 * @return byte[] 返回类型
	 * 
	 * @throws
	 **/

	public static byte[] readFile(File file) {
		ByteArrayOutputStream ois = null;
		FileInputStream fis = null;
		try {
			ois = new ByteArrayOutputStream();
			fis = new FileInputStream(file);
			byte[] b = new byte[BUFFER_SIZE];
			int len;
			while ((len = fis.read(b)) != -1) {
				ois.write(b, 0, len);
			}
			return ois.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.flush();
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {

					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return null;

	}

	/**
	 * 
	 * writeFile(这里用一句话描述这个方法的作用)
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * 
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 * 
	 * 
	 * 
	 * @Title: writeFile
	 * 
	 * @Description: 把文件写入硬盘
	 * 
	 * @param @param is
	 * @param @param file 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 **/

	public static boolean writeFile(InputStream is, File file) {
		byte[] b = new byte[BUFFER_SIZE];
		boolean _ok = false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			int len;
			while ((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}

			_ok = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return _ok;
	}

	public static void deleteFile(File file) {

		if (file != null && file.exists()) {

			file.delete();
		}
	}

	public static Map<String, String> splitFileName(String file) {

		Map<String, String> result = null;
		if (file != null && !"".equals(file)) {

			int index = file.lastIndexOf(".");
			if (index > -1) {

				result = new HashMap<String, String>();

				result.put("fileName", file.substring(0, index));
				result.put("suffix", file.substring(index + 1));
			}
		}

		return result;
	}

	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}

	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}

	public static void copyFile(String inputFile, String outputFile)
			throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
