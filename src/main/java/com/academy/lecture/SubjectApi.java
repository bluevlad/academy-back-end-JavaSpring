package com.academy.lecture;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

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
import com.academy.lecture.service.SubjectService;
import com.academy.lecture.service.TeacherService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RestController
@RequestMapping("/api/subject")
public class SubjectApi extends CORSFilter {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    private SubjectService subjectservice;
    private TeacherService teacherservice;

    @Autowired
    public SubjectApi(SubjectService subjectservice, TeacherService teacherservice) {
        this.subjectservice = subjectservice;
        this.teacherservice = teacherservice;
    }

    /**
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 목록 조회
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

        List<HashMap<String, String>> list = subjectservice.subjectList(params);
        int listCount = subjectservice.subjectListCount(params);

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
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 상세 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/view")
    public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> view = subjectservice.subjectView(params);
        List<HashMap<String, String>> kindlist = teacherservice.getKindList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);
        result.put("kindlist", kindlist);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : codeCheck
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 코드 중복 체크
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/codeCheck")
    public JSONObject codeCheck(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        params.put("SUBJECT_NM", URLDecoder.decode(params.get("SUBJECT_NM"),"UTF-8"));
        int listCount = subjectservice.subjectCheck(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("available", listCount == 0);
        result.put("message", listCount > 0 ? "이미 사용중인 과목명입니다" : "사용 가능한 과목명입니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : save
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 등록
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping(value="/save")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject save(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        subjectservice.subjectInsert(params);

        String[] CATEGORY_CODE = request.getParameterValues("CATEGORY_CODE");
        if(CATEGORY_CODE != null){
            for(int i=0; i<CATEGORY_CODE.length; i++){
                params.put("CATEGORY_CODE", CATEGORY_CODE[i]);
                subjectservice.subjectCategoryInsert(params);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "과목이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : update
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 수정
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/update")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject update(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        subjectservice.subjectUpdate(params);
        subjectservice.subjectCategoryDelete(params);

        String[] CATEGORY_CODE = request.getParameterValues("CATEGORY_CODE");
        if(CATEGORY_CODE != null){
            for(int i=0; i<CATEGORY_CODE.length; i++){
                params.put("CATEGORY_CODE", CATEGORY_CODE[i]);
                subjectservice.subjectCategoryInsert(params);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "과목이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 삭제
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/delete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject delete(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        subjectservice.subjectDelete(params);
        subjectservice.subjectCategoryDelete(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "과목이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : listDelete
     * @작성일 : 2013. 10.
     * @Method 설명 : 과목 다중 삭제
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
                params.put("SUBJECT_CD", DEL_ARR[i]);
                subjectservice.subjectDelete(params);
                subjectservice.subjectCategoryDelete(params);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 과목이 삭제되었습니다" : "삭제된 과목이 없습니다");

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
        params.put("ONOFF_DIV", CommonUtil.isNull(request.getParameter("ONOFF_DIV"), ""));
        params.put("GUBUN", CommonUtil.isNull(request.getParameter("GUBUN"), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"), ""));
        params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), "Y"));
        params.put("SEARCHGUBN", CommonUtil.isNull(request.getParameter("SEARCHGUBN"), "T"));
    }

}