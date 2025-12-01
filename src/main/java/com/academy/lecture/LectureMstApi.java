package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.academy.book.service.BookService;
import com.academy.lecture.service.LectureMstService;
import com.academy.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RestController
@RequestMapping("/api/lecturemst")
public class LectureMstApi extends CORSFilter {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;

	private BookService bookservice;
	private LectureMstService lecturemstservice;
	private TeacherService teacherservice;

	@Autowired
	public LectureMstApi(BookService bookservice, LectureMstService lecturemstservice,
			TeacherService teacherservice) {
		this.bookservice = bookservice;
		this.lecturemstservice = lecturemstservice;
		this.teacherservice = teacherservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 마스터 목록 조회
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
		List<HashMap<String, String>> list = lecturemstservice.lecturemstList(params);
		int listCount = lecturemstservice.lecturemstListCount(params);

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
	 * @Method Name : getSubjectTeacherList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 과목(강사) 검색 목록
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/subjectTeacherList")
	public JSONObject getSubjectTeacherList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("subjectteacherlist", subjectteacherlist);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 마스터 상세 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		List<HashMap<String, String>> view = lecturemstservice.lectureView(params);
		List<HashMap<String, String>> viewbooklist = lecturemstservice.lectureViewBookList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("subjectteacherlist", subjectteacherlist);
		result.put("view", view);
		result.put("viewbooklist", viewbooklist);
		result.put("rdelyn", "Y");
		result.put("sdelyn", "Y");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getDataViewList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 데이터 뷰 리스트 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/dataViewList")
	public JSONObject getDataViewList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("ORDERID", CommonUtil.isNull(request.getParameter("ORDERID"), ""));

		List<HashMap<String, String>> memolist = lecturemstservice.lectureDataMemoViewList(params);
		List<HashMap<String, String>> list = lecturemstservice.lectureDataViewList(params);
		List<HashMap<String, String>> wmv = lecturemstservice.lectureWMV(params);
		List<HashMap<String, String>> down_count = lecturemstservice.lectureDown_Count(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("memolist", memolist);
		result.put("list", list);
		result.put("wmv", wmv);
		result.put("down_count", down_count);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 마스터 등록
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		Calendar cal = Calendar.getInstance();
		String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
		String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");
		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");
		String BRIDGE_MSTCODE = "";
		String MSTCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeMstcodeSeqList = lecturemstservice.getBridgeMstcodeSeq(params);
		if(getBridgeMstcodeSeqList.size() > 0){
			SEQ = getBridgeMstcodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeMstcodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "R" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getBridgeMstcodeList = lecturemstservice.getBridgeMstcode(params);

		if(getBridgeMstcodeList.size() > 0)
			BRIDGE_MSTCODE = params.get("PREFIX") + getBridgeMstcodeList.get(0).get("BRIDGE_MSTCODE");
		else
			BRIDGE_MSTCODE = params.get("PREFIX") + "00001";
		params.put("BRIDGE_MSTCODE", BRIDGE_MSTCODE.replace(" ", ""));

		params.put("PREFIX", "M" + cal.get(Calendar.YEAR));

		List<HashMap<String, String>> getLeccodeList = lecturemstservice.getMstcode(params);
		if(getLeccodeList.size() > 0)
			MSTCODE = params.get("PREFIX") + getLeccodeList.get(0).get("MSTCODE");
		else
			MSTCODE = params.get("PREFIX") + "00001";
		params.put("MSTCODE", MSTCODE.replace(" ", ""));

		lecturemstservice.lecturemstInsert(params);
		params.put("SEQ", SEQ);
		lecturemstservice.lectureBridgeInsert(params);

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", JU_RSC_ID_ARR[k]);
				params.put("FLAG","J");
				lecturemstservice.lectureBookInsert2(params);
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", BU_RSC_ID_ARR[k]);
				params.put("FLAG","B");
				lecturemstservice.lectureBookInsert2(params);
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", SU_RSC_ID_ARR[k]);
				params.put("FLAG","S");
				lecturemstservice.lectureBookInsert2(params);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 등록되었습니다");
		result.put("MSTCODE", MSTCODE);
		result.put("BRIDGE_MSTCODE", BRIDGE_MSTCODE);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 마스터 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		params.put("BRIDGE_LEC", CommonUtil.isNull(request.getParameter("BRIDGE_LEC"), ""));
		params.put("UPDATE_FLAG", CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));

		lecturemstservice.lectureBookDelete(params);
		lecturemstservice.lecturemstUpdate(params);

		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", JU_RSC_ID_ARR[k]);
				params.put("FLAG","J");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lecturemstservice.lectureBookInsert(params);
				}else{
					lecturemstservice.lectureBookInsert2(params);
				}
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", BU_RSC_ID_ARR[k]);
				params.put("FLAG","B");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lecturemstservice.lectureBookInsert(params);
				}else{
					lecturemstservice.lectureBookInsert2(params);
				}
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", SU_RSC_ID_ARR[k]);
				params.put("FLAG","S");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lecturemstservice.lectureBookInsert(params);
				}else{
					lecturemstservice.lectureBookInsert2(params);
				}
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 마스터 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		lecturemstservice.lectureDelete(params);
		lecturemstservice.lectureBridgeDelete(params);
		lecturemstservice.lectureBookDelete(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
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
				params.put("REG_ID",loginInfo.get("USER_ID"));
				params.put("UPD_ID",loginInfo.get("USER_ID"));
			}
		}

		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHPAYYN", CommonUtil.isNull(request.getParameter("SEARCHPAYYN"), ""));
		params.put("SEARCHPAYTYPE", CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
		params.put("SEARCHOPENPAGE", CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
		params.put("ADDBOOKAREA", CommonUtil.isNull(request.getParameter("ADDBOOKAREA"), ""));
		params.put("ADDAREA", CommonUtil.isNull(request.getParameter("ADDAREA"), ""));
		params.put("JSEQ", CommonUtil.isNull(request.getParameter("JSEQ"), ""));
		params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
		params.put("BRIDGE_MSTCODE", CommonUtil.isNull(request.getParameter("BRIDGE_MSTCODE"), ""));
		params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
		params.put("LEARNING_CD", CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
		params.put("MSTCODE", CommonUtil.isNull(request.getParameter("MSTCODE"), ""));
		params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(request.getParameter("LEC_TYPE_CHOICE"), "D"));
		params.put("PLAN", CommonUtil.isNull(request.getParameter("PLAN"), ""));
		params.put("SUBJECT_TEACHER", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER"), ""));
		params.put("SUBJECT_TEACHER_PAYMENT", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER_PAYMENT"), ""));
		params.put("SUBJECT_TITLE", CommonUtil.isNull(request.getParameter("SUBJECT_TITLE"), ""));
		params.put("SUBJECT_DESC", CommonUtil.isNull(request.getParameter("SUBJECT_DESC"), ""));
		params.put("SUBJECT_MEMO", CommonUtil.isNull(request.getParameter("SUBJECT_MEMO"), ""));
		params.put("SUBJECT_KEYWORD", CommonUtil.isNull(request.getParameter("SUBJECT_KEYWORD"), ""));
		params.put("SUBJECT_PERIOD", CommonUtil.isNull(request.getParameter("SUBJECT_PERIOD"), ""));
		params.put("SUBJECT_OFF_OPEN_YEAR", CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_YEAR"), ""));
		params.put("SUBJECT_OFF_OPEN_MONTH", CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_MONTH"), ""));
		params.put("SUBJECT_OFF_OPEN_DAY", CommonUtil.isNull(request.getParameter("SUBJECT_OFF_OPEN_DAY"), ""));
		params.put("SUBJECT_DISCOUNT", CommonUtil.isNull(request.getParameter("SUBJECT_DISCOUNT"), ""));

		if("N".equals(params.get("LEC_TYPE_CHOICE"))){
			params.put("SUBJECT_PRICE", CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), ""));
		}else{
			params.put("SUBJECT_PRICE", CommonUtil.isNull(request.getParameter("SUBJECT_PRICE"), "0"));
		}

		params.put("SUBJECT_POINT", CommonUtil.isNull(request.getParameter("SUBJECT_POINT"), "0"));
		params.put("SUBJECT_MOVIE", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE"), "0"));
		params.put("SUBJECT_PMP", CommonUtil.isNull(request.getParameter("SUBJECT_PMP"), "0"));
		params.put("SUBJECT_MOVIE_PMP", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_PMP"), "0"));
		params.put("SUBJECT_MOVIE_MP4", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_MP4"), "0"));
		params.put("SUBJECT_MOVIE_VOD_MP4", CommonUtil.isNull(request.getParameter("SUBJECT_MOVIE_VOD_MP4"), "0"));
		params.put("SUBJECT_OPTION", CommonUtil.isNull(request.getParameter("SUBJECT_OPTION"), ""));
		params.put("SUBJECT_ISUSE", CommonUtil.isNull(request.getParameter("SUBJECT_ISUSE"), ""));
		params.put("SUBJECT_SJT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_SJT_CD"), ""));
		params.put("SUBJECT_VOD_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_VOD_DEFAULT_PATH"), ""));
		params.put("SUBJECT_WIDE_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_WIDE_DEFAULT_PATH"), ""));
		params.put("SUBJECT_MP4_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_MP4_DEFAULT_PATH"), ""));
		params.put("SUBJECT_PMP_DEFAULT_PATH", CommonUtil.isNull(request.getParameter("SUBJECT_PMP_DEFAULT_PATH"), ""));
		params.put("SUBJECT_PASS", CommonUtil.isNull(request.getParameter("SUBJECT_PASS"), ""));
		params.put("SUBJECT_JANG", CommonUtil.isNull(request.getParameter("SUBJECT_JANG"), ""));
		params.put("RE_COURSE", CommonUtil.isNull(request.getParameter("RE_COURSE"), ""));
		params.put("LEC_SCHEDULE", CommonUtil.isNull(request.getParameter("LEC_SCHEDULE"), ""));
		params.put("NO", CommonUtil.isNull(request.getParameter("NO"), "0"));
		params.put("ADD_LOW", CommonUtil.isNull(request.getParameter("ADD_LOW"), ""));
		params.put("ADD_MOVIE_ORDER1", CommonUtil.isNull(request.getParameter("ADD_MOVIE_ORDER1"), ""));
		params.put("ADD_MOVIE_ORDER2", CommonUtil.isNull(request.getParameter("ADD_MOVIE_ORDER2"), ""));
		params.put("ADD_MOVIE_NAME", CommonUtil.isNull(request.getParameter("ADD_MOVIE_NAME"), ""));
		params.put("ADD_MOVIE_DESC", CommonUtil.isNull(request.getParameter("ADD_MOVIE_DESC"), ""));
		params.put("ADD_MOVIE_URL", CommonUtil.isNull(request.getParameter("ADD_MOVIE_URL"), ""));
		params.put("ADD_WIDE_URL", CommonUtil.isNull(request.getParameter("ADD_WIDE_URL"), ""));
		params.put("ADD_MOVIE_FILENAME1", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME1"), ""));
		params.put("ADD_MP4_URL", CommonUtil.isNull(request.getParameter("ADD_MP4_URL"), ""));
		params.put("ADD_MOVIE_FILENAME2", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME2"), ""));
		params.put("ADD_PMP_URL", CommonUtil.isNull(request.getParameter("ADD_PMP_URL"), ""));
		params.put("ADD_MOVIE_FILENAME3", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME3"), ""));
		params.put("ADD_MOVIE_FILENAME4", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FILENAME4"), ""));
		params.put("ADD_PMP_FILENAME", CommonUtil.isNull(request.getParameter("ADD_PMP_FILENAME"), ""));
		params.put("ADD_MOVIE_TIME", CommonUtil.isNull(request.getParameter("ADD_MOVIE_TIME"), ""));
		params.put("ADD_MOVIE_FREE_FLAG", CommonUtil.isNull(request.getParameter("ADD_MOVIE_FREE_FLAG"), "N"));
		params.put("FILE_DEL_NO", CommonUtil.isNull(request.getParameter("FILE_DEL_NO"), ""));
		params.put("FILE_DEL_NAME", CommonUtil.isNull(request.getParameter("FILE_DEL_NAME"), ""));
		params.put("MOVIE_ORDER1", CommonUtil.isNull(request.getParameter("MOVIE_ORDER1"), ""));
		params.put("MOVIE_ORDER2", CommonUtil.isNull(request.getParameter("MOVIE_ORDER2"), ""));
		params.put("MST_TEXT", CommonUtil.isNull(request.getParameter("MST_TEXT"), ""));
		params.put("POSITION", CommonUtil.isNull(request.getParameter("POSITION"), ""));
		params.put("LEC_GUBUN", CommonUtil.isNull(request.getParameter("LEC_GUBUN"), ""));
		params.put("REC_GUBUN", CommonUtil.isNull(request.getParameter("REC_GUBUN"), ""));
		params.put("MOV_ING", CommonUtil.isNull(request.getParameter("MOV_ING"), ""));
		params.put("MST_USE_YN", CommonUtil.isNull(request.getParameter("MST_USE_YN"), ""));
		params.put("TEACHER_NM", CommonUtil.isNull(request.getParameter("TEACHER_NM"), ""));
		params.put("TEACHER_SUBJECT", CommonUtil.isNull(request.getParameter("TEACHER_SUBJECT"), ""));
		params.put("ORDERNO", CommonUtil.isNull(request.getParameter("ORDERNO"), ""));
		params.put("ORDERID", CommonUtil.isNull(request.getParameter("ORDERID"), ""));
	}

}