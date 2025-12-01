package com.academy.lecture;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
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
import com.academy.lecture.service.CategoryService;

/**
 * @FileName   : CategoryApi.java
 * @Project    :
 * @Date       : 2015. 05. 20.
 * @Author     : miraenet
 * @변경이력    :
 * @프로그램 설명 : 카테고리 관리 API
 */
@RestController
@RequestMapping("/api/category")
public class CategoryApi extends CORSFilter {

    private CategoryService seriesCategoryService;

    @Autowired
    public CategoryApi(CategoryService seriesCategoryService) {
        this.seriesCategoryService = seriesCategoryService;
    }

    /**
     * @Method Name  : getSeriesCateTree
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 트리 조회
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/tree")
    public JSONObject getSeriesCateTree(HttpServletRequest request) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        setParam(params, request);

        List<HashMap<String, Object>> menuList = seriesCategoryService.getSeriesCateTree();

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("menuList", menuList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : getDetail
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 상세 조회
     * @param request
     * @return/
     * @throws Exception
     */
    @GetMapping(value = "/detail")
    public JSONObject getDetail(@ModelAttribute HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        HashMap<String, Object> detail = seriesCategoryService.getDetail(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("detail", detail);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : getMaxOrdr
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 최대 순서 조회
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/maxOrdr")
    public JSONObject getMaxOrdr(@ModelAttribute HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        HashMap<String, Object> maxMenuMap = seriesCategoryService.getMaxOrdr(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        if(maxMenuMap == null){
            String ORDR = CommonUtil.isNull(request.getParameter("ORDR"), "0");
            if(ORDR.equals("0")){ //Root
                result.put("ORDR", 1);
            }else{
                result.put("ORDR", (Integer.parseInt(ORDR) + 1)+"");
            }
        } else{
            result.put("ORDR", maxMenuMap.get("ORDR"));
            result.put("P_NAME", maxMenuMap.get("P_NAME"));
        }
        String CODE = CommonUtil.isNull(request.getParameter("CODE"), "");
        if(CODE!=null && CODE!=""){
            result.put("P_CODE", CODE);
        }

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : deleteProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 삭제
     * @param request
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/delete")
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public JSONObject deleteProcess(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int isDelete = seriesCategoryService.deleteProcess(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("isDelete", isDelete);
        result.put("VIEWCODE", params.get("CODE"));

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : idCheck
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : ID 중복 확인
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/idCheck")
    public JSONObject idCheck(@ModelAttribute HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int idCount = seriesCategoryService.idCheck(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("idCount", idCount);
        if(idCount  > 0){
            result.put("message", "중복된 메뉴ID입니다. 다른 메뉴ID를 사용하여 등록해주십시요");
        }else{
            result.put("message", "등록가능한 메뉴ID입니다");
        }

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }


    /**
     * @Method Name  : insertProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 등록
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insert")
    public JSONObject insertProcess(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int isInsert = seriesCategoryService.insertProcess(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("isInsert", isInsert);
        result.put("VIEWCODE", params.get("CODE"));

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name  : updateProcess
     * @Date         : 2015. 05. 20.
     * @Author       : miraenet
     * @변경이력      :
     * @Method 설명      : 카테고리 수정
     * @param request
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/update")
    public JSONObject updateProcess(@RequestBody HashMap<String, Object> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        seriesCategoryService.updateProcess(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("isUpdate", 1);
        result.put("VIEWCODE", params.get("CODE"));

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
        params.put("CODE", CommonUtil.isNull(request.getParameter("CODE"), ""));
        params.put("NAME", CommonUtil.isNull(request.getParameter("NAME"), ""));
        params.put("USE_ON", CommonUtil.isNull(request.getParameter("USE_ON"), ""));
        params.put("USE_OFF", CommonUtil.isNull(request.getParameter("USE_OFF"), ""));
        params.put("ISUSE", CommonUtil.isNull(request.getParameter("ISUSE"), ""));
        params.put("ORDR", CommonUtil.isNull(request.getParameter("ORDR"), ""));
        params.put("P_CODE", CommonUtil.isNull(request.getParameter("P_CODE"), ""));
        params.put("SRS_CD", CommonUtil.isNull(request.getParameter("SRS_CD"), ""));
        params.put("TYPE_CHOICE", CommonUtil.isNull(request.getParameter("TYPE_CHOICE"), "C"));
    }

}