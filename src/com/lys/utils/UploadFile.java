package com.lys.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.lys.system.dictionary.SysStatic;
import com.lys.system.filter.SafetyFilter;


/**
 * 文件处理类（上传，删除）
 * @author shuang
 *
 */
public class UploadFile {
	/***
	 * 获取上传后的文件根目录绝对路径
	 * @param path 上传后的文件在根目录下的相对地址，例如： C:\根目录\newsFile ,其中newsFile就是要传入的值,如果为空，则默认存入根目录下
	 * @param name 上传文件的名字  ，例如：abc.txt
	 * @return 获取上传后的文件根目录绝对路径+文件名
	 */
	public static String getAbsolutePath(String path, String name) {
		return SysStatic.fileUploadPathDefault + (org.springframework.util.StringUtils.hasText(path)?(path + "/"):"") + name;
	}
	/**
	 * 获取UUID生成的文件名
	 * @param name 文件名 ，例如： abc.txt
	 * @return  UUID+".txt" 
	 */
	public static String getName(String name){
		try {
			return UUID.randomUUID().toString()+name.substring(name.lastIndexOf("."));
		} catch (Exception e) {
			return UUID.randomUUID().toString()+".error";
		}
	}
	/**
	 * 写出文件
	 * @param file 上传的文件流
	 * @param uploadPath 上传后的绝对地址+文件名
	 * @throws IOException
	 */
	public static void uploadInputStream(InputStream input,String uploadPath) throws IOException{ 
		if (input != null && uploadPath != null) {
			FileOutputStream out = new FileOutputStream(uploadPath);
			byte[] b = new byte[1024];
			int len;
			while ((len = input.read(b,0,b.length)) > 0) 
				out.write(b, 0, len);
			out.close();
			input.close();
		}
	}
	/**
	 * 写出文件
	 * @param file 上传的文件
	 * @param uploadPath 上传后的绝对地址+文件名
	 * @throws IOException
	 */
	public static void uploadFile(File file,String uploadPath) throws IOException {
		if (file!=null&&uploadPath!=null) {
			FileInputStream input = new FileInputStream(file);
			uploadInputStream(input,uploadPath);
		}
	}
	/**
	 * 单文件上传
	 * @param files 单个文件
	 * @param names 单个文件的名字
	 * @param path  共同的目录下相对路径,如果为空，则默认存入根目录下
	 * @return 文件在项目中的相对路径
	 */
	public static String uploadFile(File files, String name, String path)  {
		if (org.springframework.util.StringUtils.hasText(name)){
			String newName = getName(name);
			try {
				uploadFile(files, getAbsolutePath(path,newName));
			} catch (IOException e) {
				SafetyFilter.logger.error("文件上传失败！原因："+e.getMessage());
				return null;
			}
			return path + "/" + newName;
		}
		return null;
	}
	/**
	 * 单文件流上传
	 * @param files 单个文件
	 * @param names 单个文件的名字
	 * @param path  共同的目录下相对路径，
	 * @return 文件在项目中的相对路径
	 */
	public static String uploadInputStream(InputStream input, String name, String path)  {
		if (org.springframework.util.StringUtils.hasText(name)){
			String newName = getName(name);
			try {
				uploadInputStream(input, getAbsolutePath(path,newName ));
			} catch (IOException e) {
				SafetyFilter.logger.error("文件上传失败！原因："+e.getMessage());
				return null;
			}
			return path + "/" + newName;
		} 
		return null;
	}
	/**
	 * 删除文件  
	 * @param savapath 根目录下的相对路径，例如：file/abc.txt
	 */
	public static Boolean delpic(String savapath){
		if (org.springframework.util.StringUtils.hasText(savapath)) {
			String p=UploadFile.getAbsolutePath("",savapath );
			File file=new File(p);
			if (file!=null&&file.isFile()&&file.exists()) 
				return file.delete();
		}
		return false;
	}

}
