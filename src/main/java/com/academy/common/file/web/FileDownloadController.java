package com.academy.common.file.web;

import java.io.File;
import java.io.FileInputStream;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.academy.common.FileVO;
import com.academy.common.CommonUtil;

/**
 * 파일 다운로드 컨트롤러
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.11  Y.K.KIM          최초 생성
 *
 * </pre>
 */
@Controller
public class FileDownloadController implements ApplicationContextAware {
	@Value("${file.upload.path:C:/upload/}")
	private String uploadPath;

	private WebApplicationContext context = null;

	@RequestMapping("download.do")
	public ModelAndView download(HttpServletRequest request){
		String rootPath = uploadPath;
		String path = CommonUtil.isNull(request.getParameter("path"), "");
		File f = new File(rootPath+path);
		String filename = CommonUtil.isNull(request.getParameter("filename"), f.getName());
		FileVO file = new FileVO();
		file.setFILE_PATH(rootPath+path);
		file.setFILE_NM(filename);
		file.setFILE_SIZE(f.length());
		if(f.length() > 0) {

			return new ModelAndView("fileDownloadView", "downloadFile" , file);
		} else {
			return new ModelAndView("common/error/fileNotFound");
		}
	}

	@RequestMapping("imgFileView.do")
	public ResponseEntity<byte[]>  imgFileView(@RequestParam("path")String path) throws Exception{
		String rootPath = uploadPath;

		FileInputStream fin = new FileInputStream(new File(rootPath + path));
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    return new ResponseEntity<byte[]>(IOUtils.toByteArray(fin), headers, HttpStatus.CREATED);
	}


	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = (WebApplicationContext)arg0;
	}
}