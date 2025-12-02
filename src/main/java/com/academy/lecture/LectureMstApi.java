package com.academy.lecture;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
import com.academy.common.file.FileUtil;
import com.academy.book.service.BookService;
import com.academy.lecture.service.LectureMstService;
import com.academy.lecture.service.LectureMstVO;
import com.academy.lecture.service.SubjectVO;
import com.academy.lecture.service.TeacherService;
import com.academy.lecture.service.TeacherVO;

@RestController
@RequestMapping("/api/lecturemst")
public class LectureMstApi extends CORSFilter {

	private final FileUtil fileUtil;
	private final BookService bookservice;
	private final LectureMstService lecturemstservice;
	private final TeacherService teacherservice;

	@Autowired
	public LectureMstApi(FileUtil fileUtil,
			BookService bookservice, LectureMstService lecturemstservice,
			TeacherService teacherservice) {
		this.fileUtil = fileUtil;
		this.bookservice = bookservice;
		this.lecturemstservice = lecturemstservice;
		this.teacherservice = teacherservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 목록 조회
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/list")
	public JSONObject list(@ModelAttribute LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		/* 페이징 */
		int currentPage = lectureMstVO.getCurrentPage();
		int pageRow = lectureMstVO.getPageRow();
		int startNo = (currentPage - 1) * pageRow;
		int endNo = startNo + pageRow;
		lectureMstVO.setStartNo(String.valueOf(startNo));
		lectureMstVO.setEndNo(String.valueOf(endNo));
		/* 페이징 */

		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setGubun("T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(teacherVO);

		SubjectVO subjectVO = new SubjectVO();
		subjectVO.setIsUse("Y");
		List<HashMap<String, String>> formlist = bookservice.getLearningFormList(subjectVO);

		List<HashMap<String, String>> list = lecturemstservice.lecturemstList(lectureMstVO);
		int listCount = lecturemstservice.lecturemstListCount(lectureMstVO);

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
	 * @작성일 : 2025.11
	 * @Method 설명 : 과목(강사) 검색 목록
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/subjectTeacherList")
	public JSONObject getSubjectTeacherList(@ModelAttribute LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(lectureMstVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("subjectteacherlist", subjectteacherlist);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 상세 조회
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		List<HashMap<String, String>> view = lecturemstservice.lectureView(lectureMstVO);
		List<HashMap<String, String>> viewbooklist = lecturemstservice.lectureViewBookList(lectureMstVO);
		List<HashMap<String, String>> subjectteacherlist = bookservice.getCaSubjectTeacherList(lectureMstVO);

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
	 * @작성일 : 2025.11
	 * @Method 설명 : 데이터 뷰 리스트 조회
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/dataViewList")
	public JSONObject getDataViewList(@ModelAttribute LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		List<HashMap<String, String>> memolist = lecturemstservice.lectureDataMemoViewList(lectureMstVO);
		List<HashMap<String, String>> list = lecturemstservice.lectureDataViewList(lectureMstVO);
		List<HashMap<String, String>> wmv = lecturemstservice.lectureWMV(lectureMstVO);
		List<HashMap<String, String>> down_count = lecturemstservice.lectureDown_Count(lectureMstVO);

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
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 등록
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@RequestBody LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		Calendar cal = Calendar.getInstance();
		String[] CATEGORY_CD_ARR = lectureMstVO.getCategoryCdArr();
		String[] LEARNING_CD_ARR = lectureMstVO.getLearningCdArr();
		String[] JU_RSC_ID_ARR = lectureMstVO.getJuRscIdArr();
		String[] BU_RSC_ID_ARR = lectureMstVO.getBuRscIdArr();
		String[] SU_RSC_ID_ARR = lectureMstVO.getSuRscIdArr();
		String BRIDGE_MSTCODE = "";
		String MSTCODE = "";
		String SEQ = "";

		List<HashMap<String, String>> getBridgeMstcodeSeqList = lecturemstservice.getBridgeMstcodeSeq(lectureMstVO);
		if(getBridgeMstcodeSeqList.size() > 0){
			SEQ = getBridgeMstcodeSeqList.get(0).get("SEQ");
			lectureMstVO.setJseq(getBridgeMstcodeSeqList.get(0).get("SEQ"));
		}else{
			SEQ = "1";
			lectureMstVO.setJseq("1");
		}

		String prefix = "R" + cal.get(Calendar.YEAR);
		lectureMstVO.setPrefix(prefix);
		List<HashMap<String, String>> getBridgeMstcodeList = lecturemstservice.getBridgeMstcode(lectureMstVO);

		if(getBridgeMstcodeList.size() > 0)
			BRIDGE_MSTCODE = prefix + getBridgeMstcodeList.get(0).get("BRIDGE_MSTCODE");
		else
			BRIDGE_MSTCODE = prefix + "00001";
		lectureMstVO.setBridgeMstcode(BRIDGE_MSTCODE.replace(" ", ""));

		prefix = "M" + cal.get(Calendar.YEAR);
		lectureMstVO.setPrefix(prefix);

		List<HashMap<String, String>> getLeccodeList = lecturemstservice.getMstcode(lectureMstVO);
		if(getLeccodeList.size() > 0)
			MSTCODE = prefix + getLeccodeList.get(0).get("MSTCODE");
		else
			MSTCODE = prefix + "00001";
		lectureMstVO.setMstcode(MSTCODE.replace(" ", ""));

		lecturemstservice.lecturemstInsert(lectureMstVO);
		lectureMstVO.setJseq(SEQ);
		lecturemstservice.lectureBridgeInsert(lectureMstVO);

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){
				lectureMstVO.setRscId(JU_RSC_ID_ARR[k]);
				lectureMstVO.setFlag("J");
				lecturemstservice.lectureBookInsert2(lectureMstVO);
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){
				lectureMstVO.setRscId(BU_RSC_ID_ARR[k]);
				lectureMstVO.setFlag("B");
				lecturemstservice.lectureBookInsert2(lectureMstVO);
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){
				lectureMstVO.setRscId(SU_RSC_ID_ARR[k]);
				lectureMstVO.setFlag("S");
				lecturemstservice.lectureBookInsert2(lectureMstVO);
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
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 수정
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@RequestBody LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		lecturemstservice.lectureBookDelete(lectureMstVO);
		lecturemstservice.lecturemstUpdate(lectureMstVO);

		String[] JU_RSC_ID_ARR = lectureMstVO.getJuRscIdArr();
		String[] BU_RSC_ID_ARR = lectureMstVO.getBuRscIdArr();
		String[] SU_RSC_ID_ARR = lectureMstVO.getSuRscIdArr();

		if(JU_RSC_ID_ARR != null){
			for(int k=0; k<JU_RSC_ID_ARR.length; k++){
				lectureMstVO.setRscId(JU_RSC_ID_ARR[k]);
				lectureMstVO.setFlag("J");
				if("ALL".equals(lectureMstVO.getUpdateFlag())){
					lecturemstservice.lectureBookInsert(lectureMstVO);
				}else{
					lecturemstservice.lectureBookInsert2(lectureMstVO);
				}
			}
		}
		if(BU_RSC_ID_ARR != null){
			for(int k=0; k<BU_RSC_ID_ARR.length; k++){
				lectureMstVO.setRscId(BU_RSC_ID_ARR[k]);
				lectureMstVO.setFlag("B");
				if("ALL".equals(lectureMstVO.getUpdateFlag())){
					lecturemstservice.lectureBookInsert(lectureMstVO);
				}else{
					lecturemstservice.lectureBookInsert2(lectureMstVO);
				}
			}
		}
		if(SU_RSC_ID_ARR != null){
			for(int k=0; k<SU_RSC_ID_ARR.length; k++){
				lectureMstVO.setRscId(SU_RSC_ID_ARR[k]);
				lectureMstVO.setFlag("S");
				if("ALL".equals(lectureMstVO.getUpdateFlag())){
					lecturemstservice.lectureBookInsert(lectureMstVO);
				}else{
					lecturemstservice.lectureBookInsert2(lectureMstVO);
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
	 * @작성일 : 2025.11
	 * @Method 설명 : 강의 마스터 삭제
	 * @param lectureMstVO
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		setSessionInfo(lectureMstVO, request);

		lecturemstservice.lectureDelete(lectureMstVO);
		lecturemstservice.lectureBridgeDelete(lectureMstVO);
		lecturemstservice.lectureBookDelete(lectureMstVO);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강의 마스터가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : setSessionInfo
	 * @작성일 : 2025.11
	 * @Method 설명 : 세션 정보 설정
	 * @param lectureMstVO
	 * @param request
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setSessionInfo(LectureMstVO lectureMstVO, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
			if(loginInfo != null) {
				lectureMstVO.setRegId(loginInfo.get("USER_ID"));
				lectureMstVO.setUpdId(loginInfo.get("USER_ID"));
			}
		}
	}

}