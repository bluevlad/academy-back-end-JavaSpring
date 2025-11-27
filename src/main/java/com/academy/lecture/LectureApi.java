package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.academy.common.service.CmmUseService;
import com.academy.common.CommonUtil;
import com.academy.book.service.BookService;
import com.academy.lecture.service.LectureService;
import com.academy.lecture.service.SubjectService;
import com.academy.lecture.service.TeacherService;
import com.academy.productOrder.service.ProductOrderService;

@RestController
@RequestMapping("/api/lecture")
public class LectureApi extends CORSFilter {

	@Value("${pageUnit:10}")
	private int pageUnit;

	private BookService bookservice;
	private LectureService lectureservice;
	private TeacherService teacherservice;
	private CmmUseService cmmUseService;
	private SubjectService subjectService;
	private ProductOrderService productOrderService;

	@Autowired
	public LectureApi(BookService bookservice, LectureService lectureservice,
			TeacherService teacherservice, CmmUseService cmmUseService,
			SubjectService subjectService, ProductOrderService productOrderService) {
		this.bookservice = bookservice;
		this.lectureservice = lectureservice;
		this.teacherservice = teacherservice;
		this.cmmUseService = cmmUseService;
		this.subjectService = subjectService;
		this.productOrderService = productOrderService;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 목록 조회
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
		List<HashMap<String, String>> list = lectureservice.lectureList(params);
		int listCount = lectureservice.lectureListCount(params);

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
	 * @Method Name : view
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 상세 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		List<HashMap<String, String>> view = lectureservice.lectureView(params);
		List<HashMap<String, String>> viewlist = lectureservice.lectureViewList(params);
		List<HashMap<String, String>> viewbooklist = lectureservice.lectureViewBookList(params);

		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHCODEISUSE", "Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(params);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(params);
		int lectureOrderCount = lectureservice.lectureDeleteCheck(params);

        Map<String, String> vo = new HashMap<String, String>();
        vo.put("SYS_CD", "ICON_GUBUN");
        List<HashMap<String, String>> ICON_GUBUNs = cmmUseService.selectCmmCodeMap(vo);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("lectureOrderCount", lectureOrderCount);
		result.put("kindlist", kindlist);
		result.put("formlist", formlist);
		result.put("subjectteacherlist", subjectteacherlist);
		result.put("view", view);
		result.put("viewlist", viewlist);
		result.put("viewbooklist", viewbooklist);
		result.put("ICON_GUBUNs", ICON_GUBUNs);

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
		List<HashMap<String, String>> list = lectureservice.bookList(params);
		int listCount = lectureservice.bookListCount(params);

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
	 * @Method Name : bookView
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 교재 상세 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/bookView")
	public JSONObject bookView(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		List<HashMap<String, String>> view = lectureservice.bookView(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("view", view);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getCouponList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 쿠폰 목록 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/couponList")
	public JSONObject getCouponList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = 4;
		params.put("pageRow", "4");
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = productOrderService.getTmCouponList(params);
		int listCount = productOrderService.getTmCouponCount(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getMoCouponList
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 모바일 쿠폰 목록 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/moCouponList")
	public JSONObject getMoCouponList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		/* 페이징 */
		int currentPage = Integer.parseInt(params.get("currentPage"));
		int pageRow = 4;
		params.put("pageRow", "4");
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		params.put("startNo", String.valueOf(startNo));
		params.put("endNo", String.valueOf(endNo));
		/* 페이징 */

		List<HashMap<String, String>> list = productOrderService.getTmMoCouponList(params);
		int listCount = productOrderService.getTmMoCouponCount(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : modifyLectureOnOff
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 개설여부 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/onOffStatus")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject modifyLectureOnOff(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		String flag = CommonUtil.isNull(request.getParameter("flag"), "");
		String flag2 = CommonUtil.isNull(request.getParameter("flag2"), "");

		params.put("flag2", flag2);

		if(flag.equals("ON")){
			params.put("FLAG", "Y");
		}else{
			params.put("FLAG", "N");
		}

		if(flag2.equals("list")){  // 단과 강의관리 리스트에서 개설여부 수정시
			params.put("RCODE", CommonUtil.isNull(request.getParameter("Rcode"), ""));
			List<HashMap<String, String>> BridgeLeccode = lectureservice.BridgeLeccode(params);

			if(BridgeLeccode.size() > 0){
				for(int i = 0; i < BridgeLeccode.size(); i++){
					String GET_CODE = String.valueOf(BridgeLeccode.get(i)).substring(9, 19);
					params.put("GET_CODE", GET_CODE);
					lectureservice.Modify_Lecture_On_Off(params);
				}
			}
		}else if(flag2.equals("jlist") || flag2.equals("ylist")){ // 종합반,패키지,연회원패키지 강의관리 리스트에서 개설여부 수정시
			params.put("GET_CODE", CommonUtil.isNull(request.getParameter("Rcode"), ""));
			lectureservice.Modify_Lecture_On_Off(params);
		}else{  // 단과 강의관리 상세페이지에서 개설여부 수정시
			params.put("GET_CODE", CommonUtil.isNull(request.getParameter("Rcode"), ""));
			lectureservice.Modify_Lecture_On_Off(params);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("flag", flag);
		result.put("message", "개설여부가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 등록
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
		String BRIDGE_LECCODE = "";
		String LECCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeLeccodeSeqList = lectureservice.getBridgeLeccodeSeq(params);
		if(getBridgeLeccodeSeqList.size() > 0){
			SEQ = getBridgeLeccodeSeqList.get(0).get("SEQ");
			params.put("SEQ", getBridgeLeccodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			params.put("SEQ", "1");
		}

		params.put("PREFIX", "R" + cal.get(Calendar.YEAR));
		List<HashMap<String, String>> getBridgeLeccodeList = lectureservice.getBridgeLeccode(params);

		if(getBridgeLeccodeList.size() > 0)
			BRIDGE_LECCODE = params.get("PREFIX") + getBridgeLeccodeList.get(0).get("BRIDGE_LECCODE");
		else
			BRIDGE_LECCODE = params.get("PREFIX") + "00001";
		params.put("BRIDGE_LECCODE", BRIDGE_LECCODE.replace(" ", ""));

		params.put("PREFIX", params.get("LEC_TYPE_CHOICE").toString() + cal.get(Calendar.YEAR));
		for(int i=0; i<CATEGORY_CD_ARR.length; i++){
			params.put("CATEGORY_CD", CATEGORY_CD_ARR[i]);

			for(int j=0; j<LEARNING_CD_ARR.length; j++){
				params.put("LEARNING_CD", LEARNING_CD_ARR[j]);

				List<HashMap<String, String>> getLeccodeList = lectureservice.getLeccode(params);
				if(getLeccodeList.size() > 0)
					LECCODE = params.get("PREFIX") + getLeccodeList.get(0).get("LECCODE");
				else
					LECCODE = params.get("PREFIX") + "00001";
				params.put("LECCODE", LECCODE.replace(" ", ""));

				lectureservice.lectureInsert(params);
				params.put("SEQ", SEQ);
				lectureservice.lectureBridgeInsert(params);

				if(JU_RSC_ID_ARR != null){
					for(int k=0; k<JU_RSC_ID_ARR.length; k++){
						params.put("RSC_ID", JU_RSC_ID_ARR[k]);
						params.put("FLAG","J");
						lectureservice.lectureBookInsert2(params);
					}
				}
				if(BU_RSC_ID_ARR != null){
					for(int k=0; k<BU_RSC_ID_ARR.length; k++){
						params.put("RSC_ID", BU_RSC_ID_ARR[k]);
						params.put("FLAG","B");
						lectureservice.lectureBookInsert2(params);
					}
				}
				if(SU_RSC_ID_ARR != null){
					for(int k=0; k<SU_RSC_ID_ARR.length; k++){
						params.put("RSC_ID", SU_RSC_ID_ARR[k]);
						params.put("FLAG","S");
						lectureservice.lectureBookInsert2(params);
					}
				}
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 단과 강의 수정
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

		String SUBJECT_DESC = params.get("SUBJECT_DESC");
		if(SUBJECT_DESC != null) {
			SUBJECT_DESC = SUBJECT_DESC.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");
			params.put("SUBJECT_DESC", SUBJECT_DESC);
		}

		lectureservice.lectureBookDelete(params);
		lectureservice.lectureUpdate(params);

		String[] JU_RSC_ID_ARR = request.getParameterValues("JU_RSC_ID");
		String[] BU_RSC_ID_ARR = request.getParameterValues("BU_RSC_ID");
		String[] SU_RSC_ID_ARR = request.getParameterValues("SU_RSC_ID");

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", JU_RSC_ID_ARR[k]);
				params.put("FLAG","J");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lectureservice.lectureBookInsert(params);
				}else{
					lectureservice.lectureBookInsert2(params);
				}
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", BU_RSC_ID_ARR[k]);
				params.put("FLAG","B");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lectureservice.lectureBookInsert(params);
				}else{
					lectureservice.lectureBookInsert2(params);
				}
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){
				params.put("RSC_ID", SU_RSC_ID_ARR[k]);
				params.put("FLAG","S");
				if(params.get("UPDATE_FLAG").equals("ALL")){
					lectureservice.lectureBookInsert(params);
				}else{
					lectureservice.lectureBookInsert2(params);
				}
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		lectureservice.lectureDelete(params);
		lectureservice.lectureBridgeDelete(params);
		lectureservice.lectureBookDelete(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2013. 11.
	 * @Method 설명 : 강의 다중 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/listDelete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject listDelete(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
		if(DEL_ARR != null){
			for(int i=0; i<DEL_ARR.length; i++){
				String Lcode = DEL_ARR[i].split("/")[0];
				String Bcode = DEL_ARR[i].split("/")[1];

				params.put("LECCODE", Lcode);
				params.put("BRIDGE_LECCODE", Bcode);
				params.put("BRIDGE_LEC", Bcode);

				lectureservice.lectureDelete(params);
				lectureservice.lectureBridgeDelete(params);
				lectureservice.lectureBookDelete(params);
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 강의가 삭제되었습니다" : "삭제된 강의가 없습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : setParam
	 * @작성일 : 2013. 11.
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
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit)));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
		params.put("SEARCHPAYYN", CommonUtil.isNull(request.getParameter("SEARCHPAYYN"), ""));
		params.put("SEARCHPAYTYPE", CommonUtil.isNull(request.getParameter("SEARCHPAYTYPE"), ""));
		params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
		params.put("SEARCHFORM", CommonUtil.isNull(request.getParameter("SEARCHFORM"), ""));
		params.put("SEARCHYEAR", CommonUtil.isNull(request.getParameter("SEARCHYEAR"), ""));
		params.put("SEARCHOPENPAGE", CommonUtil.isNull(request.getParameter("SEARCHOPENPAGE"), ""));
		params.put("LECCODE", CommonUtil.isNull(request.getParameter("LECCODE"), ""));
		params.put("BRIDGE_LECCODE", CommonUtil.isNull(request.getParameter("BRIDGE_LECCODE"), ""));
		params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
		params.put("LEARNING_CD", CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
		params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(request.getParameter("LEC_TYPE_CHOICE"), "D"));
		params.put("PLAN", CommonUtil.isNull(request.getParameter("PLAN"), ""));
		params.put("SUBJECT_TEACHER", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER"), ""));
		params.put("SUBJECT_TEACHER_PAYMENT", CommonUtil.isNull(request.getParameter("SUBJECT_TEACHER_PAYMENT"), ""));
		params.put("SUBJECT_TITLE", CommonUtil.isNull(request.getParameter("SUBJECT_TITLE"), ""));
		params.put("SUBJECT_DESC", CommonUtil.isNull(request.getParameter("SUBJECT_DESC"), ""));
		params.put("SUBJECT_MEMO", CommonUtil.isNull(request.getParameter("SUBJECT_MEMO"), ""));
		params.put("SUBJECT_KEYWORD", CommonUtil.isNull(request.getParameter("SUBJECT_KEYWORD"), ""));
		params.put("SUBJECT_PERIOD", CommonUtil.isNull(request.getParameter("SUBJECT_PERIOD"), ""));
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
		params.put("RSC_ID", CommonUtil.isNull(request.getParameter("RSC_ID"), ""));
	}

}