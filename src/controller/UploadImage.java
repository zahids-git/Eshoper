package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

import beanpackage.Configure;

public class UploadImage {
	
	public boolean uploadProductImages(MultipartFile file,String fileName) {
		
		if(!file.isEmpty()){
			try {
				Log.showConsole("Here");
				byte []bytes = file.getBytes();
                File serverFile = new File(Configure.APPLICATION_PATH+"product_images\\"+fileName+".jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                Log.showConsole("Successfully saved on = "
                        + serverFile.getAbsolutePath());
			} catch (Exception e) {
				Log.showConsole(e.getMessage());
				return false;
			}
		}
		else {
			Log.showConsole("Error type : File not found");
			return false;
		}
		return true;
	}

}
