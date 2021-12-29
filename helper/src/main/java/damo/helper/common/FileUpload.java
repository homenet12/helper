package damo.helper.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	public static String fileUpload(MultipartFile multipartFile, String uploadPath) {
		if(uploadPath == null) {
			uploadPath = "D:/fileupload/helper";
		}
		
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
		String filename = UUID.randomUUID().toString() + "." + prefix;
		
		File folder = new File(uploadPath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		String realPath = uploadPath+"/"+filename;
		File uploadFile = new File(realPath);
		try {
			multipartFile.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return realPath;
	}
}
