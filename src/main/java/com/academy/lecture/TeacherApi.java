package com.academy.lecture;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.willbes.platform.util.file.FileUtil;
import com.academy.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherApi extends CORSFilter {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

	@Inject
	private FileSystemResource fsResource;
	@Resource(name="fileUtil")
	private FileUtil fileUtil;

	private TeacherService teacherservice;

	@Autowired
	public TeacherApi(TeacherService teacherservice) {
		this.teacherservice = teacherservice;
	}

	/**
	 * @Method Name : list
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 목록 조회
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
		List<HashMap<String, String>> list;
		int listCount = 0;
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

		if(params.get("SEARCHCATEGORY").equals("main")){
			list = teacherservice.teacherMainList(params);
			listCount = teacherservice.teacherMainListCount(params);
		}else if(params.get("SEARCHCATEGORY").equals("intro")){
			list = teacherservice.teacherIntroList(params);
			listCount = teacherservice.teacherIntroListCount(params);
		}else if(params.get("SEARCHCATEGORY").equals("intro_off")){
			list = teacherservice.teacherIntro_offList(params);
			listCount = teacherservice.teacherIntro_offListCount(params);
		}else{
			list = teacherservice.teacherList(params);
			listCount = teacherservice.teacherListCount(params);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("kindlist", kindlist);
		result.put("list", list);
		result.put("totalCount", listCount);
		result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
		result.put("currentPage", currentPage);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : view
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 상세 조회
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/view")
	public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);
		List<HashMap<String, String>> view = teacherservice.teacherView(params);
		params.put("ONOFF_DIV", "");
		params.put("SEARCHGUBN", "T");
		List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);
		params.put("SEARCHGUBN", "E");
		params.put("ONOFF_DIV", "O");
		List<HashMap<String, String>> eonsubjectlist = teacherservice.getSubjectList(params);
		params.put("ONOFF_DIV", "F");
		List<HashMap<String, String>> eoffsubjectlist = teacherservice.getSubjectList(params);
		params.put("SEARCHGUBN", "M");
		params.put("ONOFF_DIV", "O");
		List<HashMap<String, String>> monsubjectlist = teacherservice.getSubjectList(params);
		params.put("ONOFF_DIV", "F");
		List<HashMap<String, String>> moffsubjectlist = teacherservice.getSubjectList(params);
		List<HashMap<String, String>> mkindlist = teacherservice.getKindList(params);
		List<HashMap<String, String>> bookloglist = teacherservice.teacherBookLog(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("view", view);
		result.put("kindlist", kindlist);
		result.put("mkindlist", mkindlist);
		result.put("eonsubjectlist", eonsubjectlist);
		result.put("eoffsubjectlist", eoffsubjectlist);
		result.put("monsubjectlist", monsubjectlist);
		result.put("moffsubjectlist", moffsubjectlist);
		result.put("bookloglist", bookloglist);

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : idCheck
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 ID 중복 확인
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@GetMapping(value="/idCheck")
	public JSONObject idCheck(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

	    int listCount = teacherservice.teacherIdCheck(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
	    if(listCount > 0) {
	    	result.put("available", false);
	    	result.put("message", "이미 사용중인 ID입니다");
	    } else {
	    	result.put("available", true);
	    	result.put("message", "사용 가능한 ID입니다");
	    }

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : save
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 등록
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping(value="/save")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject save(@RequestBody HashMap<String, Object> params, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		setParam(params, request);

		lecFileProcess(params, multipartRequest);

        String[] ORI_CATEGORY_CODE = request.getParameterValues("ORI_CATEGORY_CODE");
        String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
        String[] SETCARR = request.getParameterValues("SUBJECT_CD");
        params.put("ORI_CATEGORY_CODE", ORI_CATEGORY_CODE);
        params.put("CETCARR", CETCARR);
        params.put("SETCARR", SETCARR);

		teacherservice.teacherInsert(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강사가 등록되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : update
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/update")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject update(@RequestBody HashMap<String, Object> params, HttpServletRequest request, MultipartHttpServletRequest multipartRequest) throws Exception {
		setParam(params, request);
		lecFileProcess(params, multipartRequest);

		String rootPath = fsResource.getPath();
		deleteOldFiles(params, rootPath);

        String[] ORI_CATEGORY_CODE = request.getParameterValues("ORI_CATEGORY_CODE");
        String[] CETCARR = request.getParameterValues("CATEGORY_CODE");
        String[] SETCARR = request.getParameterValues("SUBJECT_CD");
        String[] OFF_SETCARR = request.getParameterValues("OFF_SUBJECT_CD");
        params.put("ORI_CATEGORY_CODE", ORI_CATEGORY_CODE);
        params.put("CETCARR", CETCARR);
        params.put("SETCARR", SETCARR);
        params.put("OFF_SETCARR", OFF_SETCARR);

		teacherservice.teacherUpdate(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강사 정보가 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : delete
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/delete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject delete(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

        String[] DEL_ARR = {params.get("USER_ID").toString()};
        params.put("DEL_ARR", DEL_ARR);

		teacherservice.teacherIsUseUpdate(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "강사가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : listDelete
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 목록에서 다중 삭제
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@DeleteMapping(value="/listDelete")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject listDelete(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        if(DEL_ARR != null && DEL_ARR.length > 0){
            params.put("DEL_ARR", DEL_ARR);
			teacherservice.teacherIsUseUpdate(params);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", DEL_ARR.length + "명의 강사가 삭제되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
	}

	/**
	 * @Method Name : seqUpdate
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 강사 순번 수정
	 * @param request
	 * @return JSONObject
	 * @throws Exception
	 */
	@PutMapping(value="/seqUpdate")
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public JSONObject seqUpdate(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
		setParam(params, request);

		String[] SEQARR = request.getParameterValues("SEQ");
        if(SEQARR != null && SEQARR.length > 0){
            params.put("NUM", request.getParameterValues("Num"));
            params.put("SEQ", SEQARR);
            params.put("USER_ID", request.getParameterValues("PROFESSOR_USER_ID"));
            params.put("SUBJECT_CD", request.getParameterValues("SUBJECT_CD"));

			teacherservice.teacherSeqUpdate(params);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");
		result.put("message", "순번이 수정되었습니다");

		JSONObject jObject = new JSONObject(result);
		return jObject;
    }

    /**
     * @Method Name : findTeacherList
     * @작성일 : 2015. 07.
     * @Method 설명 : 강사 검색 목록
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/find")
    public JSONObject findTeacherList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> list = teacherservice.findTeacherList(params);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setParam(HashMap params, HttpServletRequest request) throws Exception {
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
        params.put("SEARCHCATEGORY", CommonUtil.isNull(request.getParameter("SEARCHCATEGORY"), ""));
		params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
		params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("ONOFFDIV", CommonUtil.isNull(request.getParameter("ONOFFDIV"), ""));
		params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
		params.put("USER_NM", CommonUtil.isNull(request.getParameter("USER_NM"), ""));
		params.put("USER_NICKNM", CommonUtil.isNull(request.getParameter("USER_NICKNM"), ""));
		params.put("USER_POSITION", CommonUtil.isNull(request.getParameter("USER_POSITION"), ""));
		params.put("USER_ROLE", CommonUtil.isNull(request.getParameter("USER_ROLE"), "PRF"));
		params.put("USER_PWD", CommonUtil.isNull(request.getParameter("USER_PWD"), ""));
		params.put("BIRTH_DAY", CommonUtil.isNull(request.getParameter("BIRTH_DAY"), ""));
		params.put("EMAIL", CommonUtil.isNull(request.getParameter("EMAIL"), ""));
        params.put("ACCOUNT", CommonUtil.isNull(request.getParameter("ACCOUNT"), ""));
		params.put("PHONE_NO", CommonUtil.isNull(request.getParameter("PHONE_NO"), ""));
		params.put("USER_POINT", CommonUtil.isNull(request.getParameter("USER_POINT"), "0"));
		params.put("ON_OPENYN", CommonUtil.isNull(request.getParameter("ON_OPENYN"), "Y"));
		params.put("OFF_OPENYN", CommonUtil.isNull(request.getParameter("OFF_OPENYN"), "Y"));
		params.put("PRF_BRD_ON", CommonUtil.isNull(request.getParameter("PRF_BRD_ON"), "N"));
		params.put("PRF_BRD_OF", CommonUtil.isNull(request.getParameter("PRF_BRD_OF"), "N"));
        params.put("PAYMENT", CommonUtil.isNull(request.getParameter("PAYMENT"), ""));
        params.put("OFF_PAYMENT", CommonUtil.isNull(request.getParameter("OFF_PAYMENT"), ""));
        params.put("ON_URL", CommonUtil.isNull(request.getParameter("ON_URL"), ""));
        params.put("OFF_URL", CommonUtil.isNull(request.getParameter("OFF_URL"), ""));
        params.put("BRD_YN", CommonUtil.isNull(request.getParameter("BRD_YN"), ""));
        params.put("OFF_BRD_YN", CommonUtil.isNull(request.getParameter("OFF_BRD_YN"), ""));
        params.put("PROFILE", CommonUtil.isNull(request.getParameter("PROFILE"), ""));
        params.put("OFF_PROFILE", CommonUtil.isNull(request.getParameter("OFF_PROFILE"), ""));
		params.put("TITLE", CommonUtil.isNull(request.getParameter("TITLE"), ""));
		params.put("OFF_TITLE", CommonUtil.isNull(request.getParameter("OFF_TITLE"), ""));
		params.put("BOOK_LOG", CommonUtil.isNull(request.getParameter("BOOK_LOG"), ""));
        params.put("OFF_BOOK_LOG", CommonUtil.isNull(request.getParameter("OFF_BOOK_LOG"), ""));
		params.put("YPLAN", CommonUtil.isNull(request.getParameter("YPLAN"), ""));
		params.put("OFF_YPLAN", CommonUtil.isNull(request.getParameter("OFF_YPLAN"), ""));
		params.put("LECINFO", CommonUtil.isNull(request.getParameter("LECINFO"), ""));
		params.put("OFF_LECINFO", CommonUtil.isNull(request.getParameter("OFF_LECINFO"), ""));
        params.put("PROF_HTML", CommonUtil.isNull(request.getParameter("PROF_HTML"), ""));
        params.put("OFF_PROF_HTML", CommonUtil.isNull(request.getParameter("OFF_PROF_HTML"), ""));
        params.put("SEARCHSBJTCD", CommonUtil.isNull(request.getParameter("SEARCHSBJTCD"), ""));
        params.put("SRCHCODE", CommonUtil.isNull(request.getParameter("SRCHCODE"), ""));
        params.put("SRCHTXT", CommonUtil.isNull(request.getParameter("SRCHTXT"), ""));
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap<String, String> lecFileProcess(HashMap params, MultipartHttpServletRequest multipartRequest) throws Exception {
		String rootPath = fsResource.getPath();
		String subPath = "member_upload/";

		String[] picFields = {"PIC1", "PIC2", "PIC3", "PIC4", "PIC5", "PIC6", "PIC7", "PIC8", "PIC9", "PIC10",
							  "OFF_PIC1", "OFF_PIC2", "OFF_PIC3", "OFF_PIC4", "OFF_PIC5", "OFF_PIC6", "OFF_PIC7", "OFF_PIC8", "OFF_PIC9", "OFF_PIC10",
							  "PRF_ONPIC1", "PRF_ONPIC2", "PRF_ONPIC3", "PRF_OFFPIC1", "PRF_OFFPIC2", "PRF_OFFPIC3",
							  "PRF_LISTONBANNER", "PRF_LISTOFFBANNER", "PROF_IMG", "OFF_PROF_IMG", "PRF_TOPONIMG", "PRF_TOPOFFIMG"};

		for(String field : picFields) {
			MultipartFile file = multipartRequest.getFile(field);
			if(file != null && !file.isEmpty()) {
				HashMap<String, Object> fileMap = fileUtil.uploadFile(file, rootPath, subPath);
				params.put(field, fileMap.get("fileFullPath").toString());
				Thread.sleep(100);
			}
		}

		return params;
	}

	/**
	 * @Method Name : deleteOldFiles
	 * @작성일 : 2013. 10.
	 * @Method 설명 : 이전 파일 삭제
	 * @param params
	 * @param rootPath
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes" })
    private void deleteOldFiles(HashMap params, String rootPath) throws Exception {
		String[] deleteFields = {"PIC1_DEL", "PIC2_DEL", "PIC3_DEL", "PIC4_DEL", "PIC5_DEL", "PIC6_DEL", "PIC7_DEL", "PIC8_DEL", "PIC9_DEL", "PIC10_DEL",
								"OFF_PIC1_DEL", "OFF_PIC2_DEL", "OFF_PIC3_DEL", "OFF_PIC4_DEL", "OFF_PIC5_DEL", "OFF_PIC6_DEL", "OFF_PIC7_DEL", "OFF_PIC8_DEL", "OFF_PIC9_DEL", "OFF_PIC10_DEL",
								"PRF_ONPIC1_DEL", "PRF_ONPIC2_DEL", "PRF_ONPIC3_DEL", "PRF_OFFPIC1_DEL", "PRF_OFFPIC2_DEL", "PRF_OFFPIC3_DEL",
								"PRF_LISTONBANNER_DEL", "PRF_LISTOFFBANNER_DEL", "PROF_IMG_DEL", "OFF_PROF_IMG_DEL", "PRF_TOPONIMG_DEL", "PRF_TOPOFFIMG_DEL"};

		for(String field : deleteFields) {
			String delValue = (String)params.get(field);
			String delNmValue = (String)params.get(field.replace("_DEL", "_DELNM"));
			String picValue = (String)params.get(field.replace("_DEL", ""));

			if("Y".equals(delValue) || (picValue != null && !"".equals(picValue))) {
				if(delNmValue != null && !"".equals(delNmValue)) {
					fileUtil.deleteFile(rootPath + delNmValue);
				}
			}
		}
	}

}