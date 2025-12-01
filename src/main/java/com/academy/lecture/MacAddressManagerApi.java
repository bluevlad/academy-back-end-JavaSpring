package com.academy.lecture;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.lecture.service.MacAddressManagerService;

import egovframework.rte.fdl.property.EgovPropertyService;

@RestController
@RequestMapping("/api/macaddressmanager")
public class MacAddressManagerApi extends CORSFilter {

    @Resource(name="propertiesService")
    protected EgovPropertyService propertiesService;

    private MacAddressManagerService macaddressmanagerservice;

    @Autowired
    public MacAddressManagerApi(MacAddressManagerService macaddressmanagerservice) {
        this.macaddressmanagerservice = macaddressmanagerservice;
    }

    /**
     * @Method Name : list
     * @작성일 : 2013. 10.
     * @Method 설명 : MAC 주소 관리 목록 조회
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

        List<HashMap<String, String>> list = macaddressmanagerservice.macaddressmanagerList(params);
        int listCount = macaddressmanagerservice.macaddressmanagerListCount(params);

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
     * @Method 설명 : MAC 주소 상세 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/view")
    public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> view = macaddressmanagerservice.macaddressView(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("view", view);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : deviceList
     * @작성일 : 2013. 10.
     * @Method 설명 : 디바이스 목록 조회
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping(value="/deviceList")
    public JSONObject deviceList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> list = macaddressmanagerservice.devicelist(params);
        List<HashMap<String, String>> view = macaddressmanagerservice.macaddressView(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("view", view);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : updateMacAddress
     * @작성일 : 2013. 10.
     * @Method 설명 : MAC 주소 업데이트
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/update")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject updateMacAddress(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        macaddressmanagerservice.macaddressmanagerUpdate(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "MAC 주소가 업데이트되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : resetMobile
     * @작성일 : 2013. 10.
     * @Method 설명 : 모바일 초기화
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping(value="/resetMobile")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject resetMobile(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        macaddressmanagerservice.macaddressmanagerUpdate1(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "모바일이 초기화되었습니다");

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

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), propertiesService.getInt("pageUnit")+""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(request.getParameter("SUBJECT_CD"),""));
        params.put("SUBJECT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_NM"),""));
        params.put("SUBJECT_SHORT_NM", CommonUtil.isNull(request.getParameter("SUBJECT_SHORT_NM"),""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("V_USER_ID", CommonUtil.isNull(request.getParameter("V_USER_ID"), ""));
        params.put("SEARCHGUBN", "T");
    }

}