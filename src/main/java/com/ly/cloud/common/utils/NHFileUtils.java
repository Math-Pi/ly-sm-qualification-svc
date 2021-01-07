package com.ly.cloud.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import com.ly.zhxg.utils.NHStringUtils;

public class NHFileUtils {
	static{//一个静态方法，当文件系统中没有nhtemp文件夹的时候，创建
		File sf=new File(System.getProperty("java.io.tmpdir")+File.separator+"nhtemp");
		if(!sf.exists()){
			sf.mkdirs();
		}
		
	}
	/**
	 * 临时文件夹，在临时文件夹中创建nhtemp，用来保存所有使用本工具类创建的文件，以便于统一清空临时文件夹,并且已经包含了文件分割符号，请注意
	 */
	public static String TEMP_FILE_PATH=System.getProperty("java.io.tmpdir")+File.separator+"nhtemp"+File.separator;
	/**
	 * 复制文件
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) { 
		InputStream inStream=null;
		FileOutputStream fs =null;
		try { 
			int byteread = 0; 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) { //文件存在时 
				inStream = new FileInputStream(oldPath); //读入原文件 
				fs=new FileOutputStream(newPath); 
				byte[] buffer = new byte[1024]; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					fs.write(buffer, 0, byteread); 
				} 
			} 
		} 
		catch (Exception e) { 
			System.out.println("复制单个文件操作出错"); 
			e.printStackTrace(); 
		} finally{
			if(inStream!=null){
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	} 
	/**
	 * 向文件写入数据
	 * @param is
	 * @param file
	 * @throws IOException
	 */
	public static void writeToFile(InputStream is,File file) throws IOException{
		FileOutputStream fs=null;
		try{
			fs=new FileOutputStream(file);
			byte[] buffer =new byte[1024];
			int byteread = 0; 
			while ( (byteread = is.read(buffer)) != -1) { 
				fs.write(buffer, 0, byteread); 
			} 
		}catch (IOException e) {
			throw e;
		}finally{
			if(fs!=null){
				fs.close();
			}
			is.close();
		}
	}
	/** 
	 * 复制整个文件夹内容 
	 * @param oldPath String 原文件路径 如：c:/fqf 
	 * @param newPath String 复制后路径 如：f:/fqf/ff 
	 * @return boolean 
	 */ 
	public static void copyFolder(String oldPath, String newPath) { 

		try { 
			(new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
			File a=new File(oldPath); 
			String[] file=a.list(); 
			File temp=null; 
			for (int i = 0; i < file.length; i++) { 
				if(oldPath.endsWith(File.separator)){ 
					temp=new File(oldPath+file[i]); 
				} 
				else{ 
					temp=new File(oldPath+File.separator+file[i]); 
				} 

				if(temp.isFile()){ 
					FileInputStream input = new FileInputStream(temp); 
					FileOutputStream output = new FileOutputStream(newPath + "/" + 
							(temp.getName()).toString()); 
					byte[] b = new byte[1024 * 5]; 
					int len; 
					while ( (len = input.read(b)) != -1) { 
						output.write(b, 0, len); 
					} 
					output.flush(); 
					output.close(); 
					input.close(); 
				} 
				if(temp.isDirectory()){//如果是子文件夹 
					copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
				} 
			} 
		} 
		catch (Exception e) { 
			System.out.println("复制整个文件夹内容操作出错"); 
			e.printStackTrace(); 

		} 
	}
	/**
	 * 删除文件夹（会删除文件夹下所有的文件）
	 * @param dir
	 * @return
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	} 
	/**
	 * 获取桌面路径
	 * @return
	 */
	public static String getDeskPath(){
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath(); 
		return desktopPath;
	}
	/**
	 * 创建一个文件，如果已经存在就删除后再创建
	 * 在临时文件家中窗建一个随机文件夹
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static File createFolder()throws IOException{
		File file=new File(TEMP_FILE_PATH+NHStringUtils.getShortUuid());
		if(!file.exists()){
			file.mkdirs();
			return file;
		}else{
			return createFolder();
		}
	}
	/**
	 * 创建一个文件，如果已经存在就删除
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public static File createFile(String path) throws IOException{
		File file=new File(path);
		if(file.exists()){
			file.delete();
			file.createNewFile();
		}else{
			file.createNewFile();
		}
		return file;
	}
	
	/**
	 * 把一个字符串写入一个文件中，如果当前文件已经存在，则删除那个文件
	 * @param path
	 * @param content
	 * @throws IOException 
	 */
	public static void writeStringToFile(String path,String content) throws IOException{
		writeStringToFile(createFile(path), content);
	}
	
	/**
	 * 把一个字符串写入一个文件中，如果当前文件已经存在，则删除那个文件
	 * @param path
	 * @param content
	 * @throws FileNotFoundException 
	 */
	public static void writeStringToFile(File file,String content) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(file);
		pw.println(content);
		pw.flush();
		pw.close();
	}
	/**
	 * 读取流数据
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	public static String streamToString(InputStream stream) throws IOException {
		InputStreamReader is = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(is);
		String tmp = null;
		StringBuffer str=new StringBuffer();
		while((tmp=reader.readLine())!=null){
			str.append(tmp+"\n");
		}
		return str.toString();
	}
	/**
	 * 向文件中写入lines，如果文件不存在则创建
	 * @param deleteFile
	 * @param deleteSqls
	 * @throws IOException 
	 */
	public static void writeStringLineToFile(File file, List<String> lines) throws IOException {
		if(!file.exists()){
			file.createNewFile();
		}
		PrintWriter pw=null;
		try{
			pw=new PrintWriter(file);
			for(String str:lines){
				pw.println(str);
				pw.flush();
			}
		}finally {
			pw.close();
		}
	}
	/**使用流build一个文件
	 * 
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static File buildTempFile(InputStream is) throws IOException {
		File file=createFile(TEMP_FILE_PATH+NHStringUtils.getShortUuid());
		writeToFile(is, file);
		return file;
	}
	public static File buildTempFile(InputStream is,String ext) throws IOException {
		File file=createFile(TEMP_FILE_PATH+NHStringUtils.getShortUuid()+"."+ext);
		writeToFile(is, file);
		return file;
	}
}
