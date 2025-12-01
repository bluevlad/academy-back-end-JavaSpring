package com.academy.lecture;

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
import com.academy.lecture.service.KindService;
import com.academy.lecture.service.SeriesService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RestController
@RequestMapping("/api/series")
public class SeriesApi extends CORSFilter {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    private SeriesService seriesService;
    private KindService kindservice;

    @Autowired
    public SeriesApi(SeriesService seriesService, KindService kindservice) {
        this.seriesService = seriesService;
        this.kindservice = kindservice;
    }

    /**
     * @Method Name : list
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 목록 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/list")
    public JSONObject list(@ModelAttribute HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage").toString());
        int pageRow = Integer.parseInt(params.get("pageRow").toString());
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = seriesService.seriesList(params);
        int listCount = seriesService.seriesListCount(params);

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
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 상세 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/view")
    public JSONObject view(@ModelAttribute HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> view = seriesService.seriesView(params);
        List<HashMap<String, String>> cat_list = kindservice.selectKindCode();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);
        result.put("cat_list", cat_list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : getKindCodeList
     * @작성일 : 2015. 05.
     * @Method 설명 : Kind 코드 목록 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/kindCodes")
    public JSONObject getKindCodeList(HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        List<HashMap<String, String>> list = kindservice.selectKindCode();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("cat_list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : codeCheck
     * @작성일 : 2015. 05.
     * @Method 설명 : 코드 중복 체크
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/codeCheck")
    public JSONObject codeCheck(@ModelAttribute HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int listCount = seriesService.seriesCheck(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("available", listCount == 0);
        result.put("message", listCount > 0 ? "이미 사용중인 코드입니다" : "사용 가능한 코드입니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : save
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 등록
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping(value="/save")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject save(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        String[] CAT_CDs = request.getParameterValues("CAT_CD");
        params.put("CAT_CDs", CAT_CDs);

        seriesService.seriesInsert(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "직렬이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : update
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬관리 수정
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/update")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject update(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        String[] CAT_CDs = request.getParameterValues("CAT_CD");
        params.put("CAT_CDs", CAT_CDs);

        seriesService.seriesUpdate(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "직렬이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬 삭제
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/delete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject delete(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        seriesService.seriesDelete(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "직렬이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : listDelete
     * @작성일 : 2015. 05.
     * @Method 설명 : 직렬 다중 삭제
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping(value="/listDelete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject listDelete(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        String[] DEL_ARR = request.getParameterValues("DEL_ARR");
        params.put("SRS_CDs", DEL_ARR);

        seriesService.seriesDelete(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", DEL_ARR != null ? DEL_ARR.length + "개의 직렬이 삭제되었습니다" : "삭제된 직렬이 없습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2015. 05.
     * @Method 설명 : 파라미터 SETTING
     * @param params
     * @param request
     * @return HashMap
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setParam(HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if(loginInfo != null) {
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
            }
        }

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SRS_CD", CommonUtil.isNull(request.getParameter("SRS_CD"), ""));
        params.put("SRS_NAME", CommonUtil.isNull(request.getParameter("SRS_NAME"), ""));
        params.put("SRS_YN", CommonUtil.isNull(request.getParameter("SRS_YN"), "Y"));
        params.put("SRS_MEMO", CommonUtil.isNull(request.getParameter("SRS_MEMO"), ""));
        params.put("USE_YN", CommonUtil.isNull(request.getParameter("USE_YN"), "Y"));
    }

}