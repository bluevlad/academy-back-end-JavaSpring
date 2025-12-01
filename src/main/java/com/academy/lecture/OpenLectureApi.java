package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.academy.common.CORSFilter;
import com.academy.common.service.CmmUseService;
import com.willbes.platform.util.file.FileUtil;
import com.academy.book.service.BookService;
import com.academy.lecture.service.OpenLectureService;
import com.academy.lecture.service.OpenLectureVO;
import com.academy.lecture.service.SubjectService;
import com.academy.lecture.service.SubjectVO;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;
import com.academy.productOrder.service.ProductOrderService;

@RestController
@RequestMapping("/api/openlecture")
public class OpenLectureApi extends CORSFilter {

	@Inject
	private FileSystemResource fsResource;
	@Inject
	private FileUtil fileUtil;

	private BookService bookservice;
	private OpenLectureService openlectureservice;
	private TeacherService teacherservice;
	private CmmUseService cmmUseService;
	private SubjectService subjectservice;
	private ProductOrderService productOrderService;

	@Autowired
	public OpenLectureApi(BookService bookservice, OpenLectureService openlectureservice,
			TeacherService teacherservice, CmmUseService cmmUseService,
			SubjectService subjectservice, ProductOrderService productOrderService) {
		this.bookservice = bookservice;
		this.openlectureservice = openlectureservice;
		this.teacherservice = teacherservice;
		this.cmmUseService = cmmUseService;
		this.subjectservice = subjectservice;
		this.productOrderService = productOrderService;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 공개강의 목록 조회
	 * @param openLectureVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/list")
	public JSONObject list(@ModelAttribute OpenLectureVO openLectureVO, HttpServletRequest request) throws Exception {
		setSessionInfo(openLectureVO, request);

		/* 페이징 */
		int currentPage = openLectureVO.getCurrentPage();
		int pageRow = openLectureVO.getPageRow();
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		openLectureVO.setStartNo(String.valueOf(startNo));
		openLectureVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setGubun("T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(subjectVO);

		List<HashMap<String, String>> list = openlectureservice.openlectureList(openLectureVO);
		int listCount = openlectureservice.openlectureListCount(openLectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getWriteData
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 공개강의 등록을 위한 데이터 조회
	 * @param openLectureVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/writeData")
	public JSONObject getWriteData(@ModelAttribute OpenLectureVO openLectureVO, HttpServletRequest request) throws Exception {
		setSessionInfo(openLectureVO, request);

		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setGubun("T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(subjectVO);

		subjectVO.setStartNo("1");
		subjectVO.setEndNo("10000");
		subjectVO.setIsUse("Y");

		List<HashMap<String, String>> subjectlist = subjectservice.subjectList(subjectVO);

		teacherVO.setStartNo("1");
		teacherVO.setEndNo("10000");
		teacherVO.setIsUse("Y");
		List<HashMap<String, String>> teacherlist = teacherservice.teacherList(teacherVO);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);

        Map<String, String> openCodeVO = new HashMap<String, String>();
        openCodeVO.put("SYS_CD", "OPEN_CODE");
        List<HashMap<String, String>> open_code_commoncode = cmmUseService.selectCmmCodeMap(openCodeVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
		result.put("subjectlist", subjectlist);
		result.put("teacherlist", teacherlist);
		result.put("ICON_GUBUNs", ICON_GUBUNs);
		result.put("open_code_commoncode", open_code_commoncode);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 공개강의 상세 조회
	 * @param openLectureVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute OpenLectureVO openLectureVO, HttpServletRequest request) throws Exception {
		setSessionInfo(openLectureVO, request);

		List<HashMap<String, String>> view = openlectureservice.openlectureView(openLectureVO);

		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setGubun("T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");
		subjectVO.setStartNo("1");
		subjectVO.setEndNo("10000");
		subjectVO.setIsUse("Y");

		List<HashMap<String, String>> subjectlist = subjectservice.subjectList(subjectVO);

		teacherVO.setStartNo("1");
		teacherVO.setEndNo("10000");
		teacherVO.setIsUse("Y");
		List<HashMap<String, String>> teacherlist = teacherservice.teacherList(teacherVO);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);

        Map<String, String> openCodeVO = new HashMap<String, String>();
        openCodeVO.put("SYS_CD", "OPEN_CODE");
        List<HashMap<String, String>> open_code_commoncode = cmmUseService.selectCmmCodeMap(openCodeVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("subjectlist", subjectlist);
		result.put("open_code_commoncode", open_code_commoncode);
		result.put("teacherlist", teacherlist);
		result.put("view", view);
		result.put("ICON_GUBUNs", ICON_GUBUNs);
		result.put("rdelyn", "Y");
		result.put("sdelyn", "Y");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : bookList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 교재 목록 조회
	 * @param openLectureVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/bookList")
	public JSONObject bookList(@ModelAttribute OpenLectureVO openLectureVO, HttpServletRequest request) throws Exception {
		setSessionInfo(openLectureVO, request);

		/* 페이징 */
		int currentPage = openLectureVO.getCurrentPage();
		int pageRow = openLectureVO.getPageRow();
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		openLectureVO.setStartNo(String.valueOf(startNo));
		openLectureVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setGubun("T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(subjectVO);

		List<HashMap<String, String>> list = openlectureservice.bookList(openLectureVO);
		int listCount = openlectureservice.bookListCount(openLectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 공개강의 등록
	 * @param openLectureVO
	 * @param request
	 * @param multipartRequest
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@ModelAttribute OpenLectureVO openLectureVO, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		setSessionInfo(openLectureVO, request);
		lecFileProcess(openLectureVO, multipartRequest);

		Calendar cal = Calendar.getInstance();
		String LECCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeLeccodeSeqList = openlectureservice.getBridgeLeccodeSeq(openLectureVO);
		if(getBridgeLeccodeSeqList.size() > 0){
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			openLectureVO.setSeq(Integer.parseInt(getBridgeLeccodeSeqList.get(0).get("SEQ")));
		}else{
			SEQ = "1";
			openLectureVO.setSeq(1);
		}

		String prefix = "O" + cal.get(Calendar.YEAR);
		openLectureVO.setPrefix(prefix);
		List<HashMap<String, String>> getLeccodeList = openlectureservice.getopenLeccode(openLectureVO);
		if(getLeccodeList.size() > 0)
			LECCODE = prefix + getLeccodeList.get(0).get("OPENLECCODE");
		else
			LECCODE = prefix + "00001";
		openLectureVO.setOpenleccode(LECCODE.replace(" ", ""));

		openlectureservice.openlectureInsert(openLectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "공개강의가 등록되었습니다");
		result.put("OPENLECCODE", LECCODE);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 공개강의 수정
	 * @param openLectureVO
	 * @param request
	 * @param multipartRequest
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@ModelAttribute OpenLectureVO openLectureVO, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		setSessionInfo(openLectureVO, request);

		String rootPath = fsResource.getPath();
		lecFileProcess(openLectureVO, multipartRequest);

		if("Y".equals(openLectureVO.getOpenFileDel()) || (openLectureVO.getOpenFile() != null && !"".equals(openLectureVO.getOpenFile())))
			fileUtil.deleteFile(rootPath + openLectureVO.getOpenFileDelnm());

		openlectureservice.openlectureUpdate(openLectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "공개강의가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 공개강의 삭제
	 * @param openLectureVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody OpenLectureVO openLectureVO, HttpServletRequest request) throws Exception {
		setSessionInfo(openLectureVO, request);

		openlectureservice.openlectureDelete(openLectureVO);
		openlectureservice.openlectureBridgeDelete(openLectureVO);
		openlectureservice.openlectureBookDelete(openLectureVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "공개강의가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : lecFileProcess
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 파일처리 프로세스
	 * @param openLectureVO
	 * @param multipartRequest
	 * @return void
	 * @throws Exception
	 */
	private void lecFileProcess(OpenLectureVO openLectureVO, MultipartHttpServletRequest multipartRequest) throws Exception {
		String rootPath = fsResource.getPath();
		String subPath = "openlecture_upload/";

		MultipartFile OPEN_FILE = multipartRequest.getFile("OPEN_FILE");

		if(OPEN_FILE != null && OPEN_FILE.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(OPEN_FILE, rootPath, subPath);
			openLectureVO.setOpenFile(fileMap1.get("fileFullPath").toString());
			Thread.sleep(100);
		}
	}

	/**
	 * @Method Name : setSessionInfo
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 세션 정보 설정
	 * @param openLectureVO
	 * @param request
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setSessionInfo(OpenLectureVO openLectureVO, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
			if(loginInfo != null) {
				openLectureVO.setRegId(loginInfo.get("USER_ID"));
				openLectureVO.setUpdId(loginInfo.get("USER_ID"));
			}
		}
	}

}