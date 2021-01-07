package com.ly.cloud.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class NHZipWriter {

	private String zipFilePath;
	private ZipOutputStream zo;
	/**
	 * 使用一个文件名购进一个zipwriter 
	 * @param zipName
	 */
	public NHZipWriter(String zipName) {
		this.zipFilePath=NHFileUtils.TEMP_FILE_PATH+zipName;
		File f=new File(zipFilePath);
		if(f.exists()){
			f.delete();
		}
		try {
			f.createNewFile();
			zo=new ZipOutputStream(new FileOutputStream(zipFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用一个输出流向zip文件中添加一个压缩条目
	 * @throws IOException 
	 */
	public void addZipEntry(File file) throws IOException{
		String fileName=file.getName();
		InputStream is=null;
		try{
			is=new FileInputStream(file);
			zo.putNextEntry(new ZipEntry(fileName));
			byte[] buffer =new byte[1024];
			int byteread = 0; 
			while ( (byteread = is.read(buffer)) != -1) { 
				zo.write(buffer, 0, byteread); 
			} 
			zo.closeEntry();
		}finally{
			if(is!=null)
				is.close();
		}

	}

	/**
	 * 使用一个输出流向zip文件中添加一个压缩条目
	 * @throws IOException 
	 */
	public void addZipEntry(InputStream is,String fileName) throws IOException{
		if(is==null || fileName==null){
			return;
		}
		zo.putNextEntry(new ZipEntry(fileName));
		byte[] buffer =new byte[1024];
		int byteread = 0; 
		while ( (byteread = is.read(buffer)) != -1) { 
			zo.write(buffer, 0, byteread); 
		} 
		zo.closeEntry();
	}

	/**
	 * 将变参数file所代表的文件全部压缩到压缩包中，并获得该压缩文件
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public File getTheZipFile(File[] files) throws IOException{
		for(File f:files){
			addZipEntry(f);
		}
		zo.closeEntry();
		return getTheZipFile();
	}
	/**
	 * 获得压缩卷对应的压缩文件，调用此方法前，请保证调用过addZipEntry方法想压缩卷汇总添加了压缩文件
	 * @return
	 * @throws IOException
	 */
	public File getTheZipFile() throws IOException{
		zo.finish();
		return new File(this.zipFilePath);

	}

	/**
	 * 将列表压缩到压缩卷中
	 * @param list map中需要有 is：文件流，fileName:文件名
	 * @return
	 * @throws IOException
	 */
	public File getTheZipFile(List<Map<String,Object>> list) throws IOException{
		for (Map<String, Object> map : list) {
			addZipEntry((InputStream)map.get("is"), (String)map.get("fileName"));
		}
		zo.closeEntry();
		return getTheZipFile();
	}
	
	/**
	 * 将一个流写入文件中
	 * @param is
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static File isToFile(InputStream is, String fileName, String filePath) throws Exception{  
		File dir = new File(filePath);  
		if(!dir.exists()){  
			dir.mkdir();  
		}  
		File file = new File(filePath+"/"+fileName);  
		file.createNewFile();  
		OutputStream os = null;  
		try{  
			os = new FileOutputStream(file);  
			byte buffer[] = new byte[10*1024];  
			while((is.read(buffer))!=-1){  
				os.write(buffer);  
			}  
			os.flush();  
		}  
		catch(Exception e){  
			e.printStackTrace();  
		}  
		finally{  
			try{  
				os.close();  
			}  
			catch(Exception e){  
				e.printStackTrace();  
			}  
		}  
		return file;  
	} 
}
