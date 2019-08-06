package go.Shop.com.Category.FreeBoard.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import go.Shop.com.Configuration.FileUtils;

@Controller
public class UploadContorller {

	private static final Logger logger=LoggerFactory.getLogger(UploadContorller.class);
	String uploadPath = "C:\\Users\\ucssystem\\git\\ShopProject\\src\\main\\resources\\static\\UploadImage";
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	public void uploadFormGET() throws Exception{
		logger.info("upload GET....");
	}
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public void uploadFormPOST(MultipartFile file,Model model,@RequestParam String type) throws Exception{
		logger.info("upload POST ... originalName={},size={},contentType={}"
				,file.getOriginalFilename()
				,file.getSize()
				,file.getContentType());
		
		String savedFileName=uploadFile(file);
		model.addAttribute("savedFileName",savedFileName);
		model.addAttribute("type",type);
	}

	private String uploadFile(MultipartFile file) throws IOException {
		String fileName=UUID.randomUUID().toString() + "_" +file.getOriginalFilename();//UUID는 랜덤값
		File target=new File(uploadPath,fileName);
		FileCopyUtils.copy(file.getBytes(), target);
		return fileName;
	}
	@ResponseBody
	@RequestMapping(value="/uploadAjaxes",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFormAJAX(MultipartFile file,@RequestParam String type) throws Exception{
		logger.info("upload POST ... originalName={},size={},contentType={}"
				,file.getOriginalFilename()
				,file.getSize()
				,file.getContentType());
		String saveFileName=uploadFile(file);
		return new ResponseEntity<String>(saveFileName,HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		logger.info("download AJAX ...original={}.size={},contentType={}",
				fileName);
		
		InputStream in=null;
		ResponseEntity<byte[]> entity=null;
		
		try {
				String formatName=FileUtils.getFileExtension(fileName);
				MediaType mType=FileUtils.getMediaType(formatName);
				HttpHeaders headers=new HttpHeaders();
				
				File file=new File(uploadPath+fileName);
				
				if(!file.exists())
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
				in=new FileInputStream(uploadPath+file);
				
				if(mType!=null) {//이미지니깐
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.lastIndexOf("_") + 1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					String dsp=new String(fileName.getBytes("UTF-8"),"IOS-8859-1");
					headers.add("Content-Dispostion"
							,"attachment:fileName=\""+dsp+"\"");
					
				}
				
				return new ResponseEntity<>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}finally {
			if(in != null) 
				in.close();
		}
	}
	
}
