package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.academy.common.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.academy.book.service.BookService;
import com.academy.lecture.service.OpenLectureService;
import com.academy.lecture.service.SubjectService;
import com.academy.lecture.service.TeacherService;
import com.academy.productOrder.service.ProductOrderService;

@RestController
@RequestMapping("/api/openlecture")
public class OpenLectureApi extends CORSFilter {

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/list")
	public JSONObject list(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = openlectureservice.openlectureList(params);
		int listCount = openlectureservice.openlectureListCount(params);

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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/writeData")
	public JSONObject getWriteData(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		params.put("startNo", "1");
		params.put("endNo", "10000");
		params.put("ISUSE", "Y");

		List<HashMap<String, String>> subjectlist = subjectservice.subjectList(params);
		List<HashMap<String, String>> teacherlist = teacherservice.teacherList(params);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);

        params.put("SYS_CD", "OPEN_CODE");
        List<HashMap<String, String>> open_code_commoncode = cmmUseService.selectCmmCodeMap(params);

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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		List<HashMap<String, String>> view = openlectureservice.openlectureView(params);

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		params.put("startNo", "1");
		params.put("endNo", "10000");
		params.put("ISUSE", "Y");

		List<HashMap<String, String>> subjectlist = subjectservice.subjectList(params);
		List<HashMap<String, String>> teacherlist = teacherservice.teacherList(params);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);

        params.put("SYS_CD", "OPEN_CODE");
        List<HashMap<String, String>> open_code_commoncode = cmmUseService.selectCmmCodeMap(params);

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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/bookList")
	public JSONObject bookList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = Integer.parseInt(params.get("pageRow"));
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> list = openlectureservice.bookList(params);
		int listCount = openlectureservice.bookListCount(params);

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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@ModelAttribute HashMap<String, String> params, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		setParam(params, request);
		lecFileProcess(params, multipartRequest);

		Calendar cal = Calendar.getInstance();
		String LECCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeLeccodeSeqList = openlectureservice.getBridgeLeccodeSeq(params);
		if(getBridgeLeccodeSeqList.size() > 0){
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "O" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getLeccodeList = openlectureservice.getopenLeccode(params);
		if(getLeccodeList.size() > 0)
			LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("OPENLECCODE");
		else
			LECCODE = params.get("PREFIX") + "00001";
		params.put("OPENLECCODE", LECCODE.replace(" ", ""));

		openlectureservice.openlectureInsert(params);

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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@ModelAttribute HashMap<String, String> params, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest) throws Exception {
		setParam(params, request);

		String rootPath = fsResource.getPath();
		lecFileProcess(params, multipartRequest);

		if("Y".equals(params.get("OPEN_FILE_DEL")) || !"".equals(params.get("OPEN_FILE")))
			fileUtil.deleteFile(rootPath + params.get("OPEN_FILE_DELNM"));

		openlectureservice.openlectureUpdate(params);

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
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		openlectureservice.openlectureDelete(params);
		openlectureservice.openlectureBridgeDelete(params);
		openlectureservice.openlectureBookDelete(params);

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
	 * @param params
	 * @param multipartRequest
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap<String, String> lecFileProcess(HashMap<String, String> params, MultipartHttpServletRequest multipartRequest) throws Exception {
		String rootPath = fsResource.getPath();
		String subPath = "openlecture_upload/";

		MultipartFile OPEN_FILE = multipartRequest.getFile("OPEN_FILE");

		if(OPEN_FILE != null && OPEN_FILE.isEmpty() == false) {
			HashMap<String, Object> fileMap1 = fileUtil.uploadFile(OPEN_FILE, rootPath, subPath);
			params.put("OPEN_FILE", fileMap1.get("fileFullPath").toString());
			Thread.sleep(100);
		}

		return params;
	}

	/**
	 * @Method Name : setParam
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 파라미터 SETTING
	 * @param params
	 * @param request
	 * @return HashMap
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void setParam(HashMap<String, String> params, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
			if(loginInfo != null) {
				params.put("REG_ID", loginInfo.get("USER_ID"));
				params.put("UPD_ID", loginInfo.get("USER_ID"));
			}
		}

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), "15"));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHPAYYN", CommonUtil.isNull(request.getParameter("SEARCHPAYYN"), ""));
		params.put("SEARCHPAYTYPE", CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
		params.put("SEARCHOPENPAGE", CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
		params.put("OPENLECCODE", CommonUtil.isNull(request.getParameter("OPENLECCODE"), ""));
		params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
		params.put("OPENBUNRU", CommonUtil.isNull(request.getParameter("OPENBUNRU"), ""));
		params.put("OPEN_TITLE", CommonUtil.isNull(request.getParameter("OPEN_TITLE"), ""));
		params.put("OPEN_SJT_CD", CommonUtil.isNull(request.getParameter("OPEN_SJT_CD"), ""));
		params.put("OPEN_TEACHER", CommonUtil.isNull(request.getParameter("OPEN_TEACHER"), ""));
		params.put("OPEN_MEMO", CommonUtil.isNull(request.getParameter("OPEN_MEMO"), ""));
		params.put("OPEN_DESC", CommonUtil.isNull(request.getParameter("OPEN_DESC"), ""));
		params.put("OPEN_HIMOVIE_PATH", CommonUtil.isNull(request.getParameter("OPEN_HIMOVIE_PATH"), ""));
		params.put("OPEN_NOMALMOVIE_PATH", CommonUtil.isNull(request.getParameter("OPEN_NOMALMOVIE_PATH"), ""));
		params.put("OPEN_FILE", CommonUtil.isNull(request.getParameter("OPEN_FILE"), ""));
		params.put("OPEN_FILE_DEL", CommonUtil.isNull(request.getParameter("OPEN_FILE_DEL"), ""));
		params.put("OPEN_FILE_DELNM", CommonUtil.isNull(request.getParameter("OPEN_FILE_DELNM"), ""));
		params.put("OPEN_ISUSE", CommonUtil.isNull(request.getParameter("OPEN_ISUSE"), ""));
		params.put("OPEN_PASSWORD", CommonUtil.isNull(request.getParameter("OPEN_PASSWORD"), ""));
		params.put("OPEN_POINT", CommonUtil.isNull(request.getParameter("OPEN_POINT"), ""));
		params.put("OPEN_HIT", CommonUtil.isNull(request.getParameter("OPEN_HIT"), "0"));
	}

}