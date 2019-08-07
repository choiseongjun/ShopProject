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
	
	private String uploadDirectPath="C:\\Users\\ucssystem\\git\\ShopProject\\src\\main\\resources\\static\\UploadImage";
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
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFormAJAX(MultipartFile file,@RequestParam String type) throws Exception{
		logger.info("upload AJAX .....orinalName={}, size={}, contentType={}", 
				file.getOriginalFilename(),
				file.getSize(),
				file.getContentType());
		logger.info("......type={}", type);
		
		return new ResponseEntity<>(HttpStatus.OK);
		/*
		 * try { String savedFileName = FileUtils.uploadFile(file, uploadPath); return
		 * new ResponseEntity<>(savedFileName, HttpStatus.CREATED); } catch (Exception
		 * e) { return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }
		 */
	}
	@ResponseBody
	@RequestMapping(value = "/uploadAjaxes", method = RequestMethod.POST)
	public ResponseEntity<String[]> uploadFormAJAXes(MultipartFile[] files,
			Integer bno, boolean isdirect) throws Exception {
		int len = files == null ? 0 : files.length;
		logger.info("upload AJAXes .....files.length={}, bno={}, isdirect={}", len, bno, isdirect); 
		
		try {
			String[] uploadedFiles = new String[len];
			String updir = isdirect ? uploadDirectPath : uploadPath;
			for (int i = 0; i < len; i++) {
				uploadedFiles[i] = FileUtils.uploadFile(files[i], updir);
			}
			
			if (bno != null) {
				//service.appendAttach(uploadedFiles, bno);
			}
			System.out.println("리턴 파일은????");
			System.out.println(uploadedFiles.toString());
			return new ResponseEntity<>(uploadedFiles, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new String[] { e.getMessage() }, HttpStatus.BAD_REQUEST);
		}
	}
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName, boolean isdirect) throws Exception {
		logger.info("display File .....fileName={}, isdirect={}", fileName, isdirect);
		
		InputStream in = null;
		try {
			String formatName = FileUtils.getFileExtension(fileName);
			MediaType mType = FileUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			String updir = isdirect ? uploadDirectPath : uploadPath;
			File file = new File(updir + fileName);
			logger.info("exists={}", file.exists());
			if (!file.exists())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			in = new FileInputStream(file);
			
			if (mType != null) {
				headers.setContentType(mType);
				
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String dsp = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				logger.info("dsp={}", dsp);
				headers.add("Content-Disposition", 
						"attachment; filename=\"" + dsp + "\"" );
			}  
			
			return new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null)
				in.close();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteFile(String fileName, Integer bno) throws Exception {
		logger.info("deleteFile.....fileName={}, bno={}", fileName, bno);
		
		try {
			if (bno > 0) {
				//service.removeAttach(fileName);
			}
			
			boolean isImage = FileUtils.getMediaType(FileUtils.getFileExtension(fileName)) != null;
			File file = new File(uploadPath + fileName);
			if (!file.exists())
				file = new File(uploadDirectPath + fileName);
			
			file.delete();
			
			// image면 원본 이미지도 삭제!
			if (isImage) {
				// /2018/09/21/s_resaldsfjadfldsj_realname.jpg
				int lastSlash = fileName.lastIndexOf("/") + 1;
				String realName = fileName.substring(0, lastSlash) + fileName.substring(lastSlash + 2);
				File real = new File(uploadPath + realName);
				real.delete();
			}
			
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
