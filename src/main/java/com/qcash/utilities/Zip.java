package com.qcash.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Zip {
	static Logger logger = LogManager.getLogger(Zip.class);
	static List<String> fileList;
	private static String OUTPUT_ZIP_FILE = System.getProperty("user.dir")+"//reports//Report.zip";
	private static String SOURCE_FOLDER = System.getProperty("user.dir")+"//target//extentreports";
	
	
	Zip(){
		fileList = new ArrayList<String>();
	}
	
	public static void main(String args[])
{
		
	
		/* Copy results xml to remote location - for updating eagle-eye */
		FileInputStream ec;
		Properties emailconfig = new Properties();
		/*try 
		{
			ec = new FileInputStream("./src/main/resources/email_config.properties");
			emailconfig.load(ec);
			Util.copyFile(emailconfig.getProperty("testresults_xml_src"), SOURCE_FOLDER+"testresults_xml_dest.xml");
		} catch (Exception e) {
			logger.error(e.getMessage());
		} */
	
		Zip appZip = new Zip();
		
		System.out.println("System current dir from zip.java is: "+System.getProperty("user.dir"));
		System.out.println("Full path1 zip.java is: "+System.getProperty("user.dir")+"//reports//Report.zip");
		System.out.println("Full path2 zip.java is: "+System.getProperty("user.dir")+"//target//extentreports");
		System.out.println("Full path3 zip.java is: "+System.getProperty("user.dir")+"//screenshots");
		
		appZip.generateFileList(new File(SOURCE_FOLDER));
		logger.debug("ReportItems="+fileList.size());
		if(fileList.size()>0)
			appZip.zipIt(OUTPUT_ZIP_FILE);
		
		appZip = new Zip();
		SOURCE_FOLDER = System.getProperty("user.dir")+"//screenshots";
		OUTPUT_ZIP_FILE = System.getProperty("user.dir")+"//screenshots//Screenshots.zip";
		appZip.generateFileList(new File(SOURCE_FOLDER));
		logger.debug("ScreenshotItems="+fileList.size());
		if(fileList.size()>0)
			appZip.zipIt(OUTPUT_ZIP_FILE);
		
		
	}
	public void zipIt(String zipFile){

		byte[] buffer = new byte[1024];

		try{

			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);

			logger.debug("Output to Zip : " + zipFile);

			for(String file : Zip.fileList){
				logger.debug("File Added : " + file);
				ZipEntry ze= new ZipEntry(file);
				zos.putNextEntry(ze);

				FileInputStream in = 
					new FileInputStream(SOURCE_FOLDER + File.separator + file);

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			zos.close();
			
			logger.debug("Zip successful");
		}catch(IOException ex){
			ex.printStackTrace();   
		}
	}

	/**
	 * Traverse a directory and get all files,
	 * and add the file into fileList  
	 * @param node file or directory
	 */
	public void generateFileList(File node){

		//add file only
		if(node.isFile()){
			if(node.getAbsoluteFile().toString().endsWith(".html") || node.getAbsoluteFile().toString().endsWith(".jpg"))
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				generateFileList(new File(node, filename));
			}
		}

	}

	/**
	 * Format the file path for zip
	 * @param file file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file){
		System.out.println("Name of the file passed is: "+ file);
		System.out.println("Length of the file passed is: "+file.length());
		System.out.println("Source Folder passed is: "+ SOURCE_FOLDER);
		System.out.println("Length of the source folder passed is: "+SOURCE_FOLDER.length());
		
		if (file.contains("screenshot"))
		{
			return file.substring(SOURCE_FOLDER.length()-1, file.length());
		}else {
			
			return file.substring(SOURCE_FOLDER.length()-2, file.length());
		}
		
	}
}






