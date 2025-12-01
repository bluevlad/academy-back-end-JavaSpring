package com.academy.lecture;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.academy.book.service.BookService;
import com.academy.lecture.service.LectureService;
import com.academy.lecture.service.ProductEventService;
import com.academy.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RestController
@RequestMapping("/api/productevent")
public class ProductEventApi extends CORSFilter {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	private ProductEventService productevent;
	private LectureService lectureservice;
	private BookService bookservice;
	private TeacherService teacherservice;

	@Autowired
	public ProductEventApi(ProductEventService productevent, LectureService lectureservice,
			BookService bookservice, TeacherService teacherservice) {
		this.productevent = productevent;
		this.lectureservice = lectureservice;
		this.bookservice = bookservice;
		this.teacherservice = teacherservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 목록 조회
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

		List<HashMap<String, String>> list = productevent.list(params);
		int listCount = productevent.listCount(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 상세 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		HashMap<String, String> item = productevent.getOne(params);
		List<HashMap<String, String>> list_prd = productevent.list_prd(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("item", item);
		result.put("list_prd", list_prd);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : insert
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 등록
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/insert")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject insert(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		productevent.insert(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "이벤트가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		productevent.update(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "이벤트가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : getSubjectList
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 선택을 위한 강의 목록 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/subjectList")
	public JSONObject getSubjectList(@ModelAttribute Map<String, Object> params, HttpServletRequest request) throws UnsupportedEncodingException {
		String keyword = CommonUtil.isNull(request.getParameter("keyword"), "");

		int currentPage = Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		int pageRow = Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));

		String s_cat_cd = CommonUtil.isNull(request.getParameter("s_cat_cd"), "");
		String s_sjt_cd = CommonUtil.isNull(request.getParameter("s_sjt_cd"), "");
		String s_menu_id = CommonUtil.isNull(request.getParameter("s_menu_id"), "");
		String search_type = CommonUtil.isNull(request.getParameter("search_type"), "");
		String search_keyword = CommonUtil.isNull(request.getParameter("search_keyword"), "");
		String EVENT_ID = CommonUtil.isNull(request.getParameter("EVENT_ID"), "");

		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("keyword", URLDecoder.decode(keyword,"UTF-8"));
		searchMap.put("currentPage", currentPage);
		searchMap.put("pageRow", pageRow);
		searchMap.put("s_cat_cd", s_cat_cd);
		searchMap.put("s_sjt_cd", s_sjt_cd);
		searchMap.put("s_menu_id", s_menu_id);
		searchMap.put("search_type", search_type);
		searchMap.put("search_keyword", search_keyword);
		searchMap.put("startNo", String.valueOf(startNo));
		searchMap.put("endNo", String.valueOf(endNo));

		// 카테고리 셀렉트박스 리스트
		List category_list = productevent.getCaCatCdList(searchMap);

		// 학습형태 셀렉트박스 리스트
		List lec_list = productevent.getVwMenuMstTree_lec(searchMap);

		// 과목 셀렉트박스 리스트
		List subject_list = productevent.getCaSubjectCdList(searchMap);

		// 강의선택 팝업 리스트
		List list = productevent.getCbLecMstFreeOrderList(searchMap);

		// 강의선택 팝업 카운트
		int listCount = productevent.getCbLecMstListFreeOrderCount(searchMap);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("searchMap", searchMap);
		result.put("category_list", category_list);
		result.put("lec_list", lec_list);
		result.put("subject_list", subject_list);
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);
		result.put("EVENT_ID", EVENT_ID);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : addLecture
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트에 강의 추가
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/addLecture")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject addLecture(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		String[] v_leccode = request.getParameterValues("v_leccode");
		String event_id = params.get("EVENT_ID");

		params.put("EVENT_ID", String.valueOf(event_id));

		int addedCount = 0;
		if (v_leccode != null && !"".equals(v_leccode)) {
			for(int i=0; i<v_leccode.length; i++){
				params.put("LECCODE", v_leccode[i]);
				productevent.lec_insert(params);
				addedCount++;
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", addedCount + "개의 강의가 추가되었습니다");
		result.put("addedCount", addedCount);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : deleteLecture
	 * @작성일 : 2014. 04.
	 * @Method 설명 : 강의 이벤트에서 강의 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/deleteLecture")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject deleteLecture(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		String[] i_leccode = request.getParameterValues("i_leccode");
		String event_id = params.get("EVENT_ID");

		params.put("EVENT_ID", String.valueOf(event_id));

		int deletedCount = 0;
		if (i_leccode != null && !"".equals(i_leccode)) {
			for(int i=0; i<i_leccode.length; i++){
				params.put("LECCODE", i_leccode[i]);
				productevent.lec_delete(params);
				deletedCount++;
			}
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", deletedCount + "개의 강의가 삭제되었습니다");
		result.put("deletedCount", deletedCount);

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
				params.put("REG_ID", loginInfo.get("USER_ID"));
				params.put("UPD_ID", loginInfo.get("USER_ID"));
			}
		}

		params.put("EVENT_ID", CommonUtil.isNull(request.getParameter("EVENT_ID"), ""));
		params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
		params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
		params.put("EVENT_NM", CommonUtil.isNull(request.getParameter("EVENT_NM"), ""));
		params.put("EVENT_TYPE", CommonUtil.isNull(request.getParameter("EVENT_TYPE"), ""));
		params.put("EVENT_AMOUNT", CommonUtil.isNull(request.getParameter("EVENT_AMOUNT"), ""));
		params.put("ST_DATE", CommonUtil.isNull(request.getParameter("ST_DATE"), ""));
		params.put("ED_DATE", CommonUtil.isNull(request.getParameter("ED_DATE"), ""));
		params.put("EVENT_YN", CommonUtil.isNull(request.getParameter("EVENT_YN"), ""));
	}

}