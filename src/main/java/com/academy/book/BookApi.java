package com.academy.book;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.book.service.BookService;
import com.academy.lecture.service.TeacherService;

/**
 * Book API Controller
 * 교재 관리 RESTful API
 */
@RestController
@RequestMapping("/api/book")
public class BookApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private BookService bookService;
    private TeacherService teacherService;

    @Autowired
    public BookApi(BookService bookService, TeacherService teacherService) {
        this.bookService = bookService;
        this.teacherService = teacherService;
    }

    /**
     * @Method Name : list
     * @작성일 : 2024
     * @Method 설명 : 교재 목록 조회
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/list")
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
        List<HashMap<String, String>> kindlist = teacherService.getKindList(params);
        List<HashMap<String, String>> list = bookService.bookList(params);
        int listCount = bookService.bookListCount(params);

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
     * @작성일 : 2024
     * @Method 설명 : 교재 상세 조회
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> view = bookService.bookView(params);
        List<HashMap<String, String>> viewlist = bookService.bookViewList(params);
        String rdelyn = "Y";        // 현재 선택글 삭제가능여부
        String sdelyn = "Y";        // 관련글 삭제 가능여부

        int checkCnt = bookService.bookUseCheck(params);
        if(checkCnt > 0)
            rdelyn = "N";

        HashMap<String, String> vparams = new HashMap<String, String>();
        for (int j = 0; j < viewlist.size(); j++) {
            vparams.put("RSC_ID", viewlist.get(j).get("RSC_ID"));
            checkCnt = bookService.bookUseCheck(vparams);
            if(checkCnt > 0)
                sdelyn = "N";
        }

        params.put("SEARCHGUBN", "T");
        List<HashMap<String, String>> kindlist = teacherService.getKindList(params);
        params.put("SEARCHCODEISUSE", "Y");
        List<HashMap<String, String>> formlist = bookService.getLearningFormList(params);
        List<HashMap<String, String>> subjectteacherlist = bookService.getCaSubjectTeacherList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("kindlist", kindlist);
        result.put("formlist", formlist);
        result.put("subjectteacherlist", subjectteacherlist);
        result.put("view", view);
        result.put("viewlist", viewlist);
        result.put("rdelyn", rdelyn);
        result.put("sdelyn", sdelyn);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : writeData
     * @작성일 : 2024
     * @Method 설명 : 교재 등록을 위한 기본 데이터 조회
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/writeData")
    public JSONObject writeData(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        params.put("SEARCHGUBN", "T");
        List<HashMap<String, String>> kindlist = teacherService.getKindList(params);
        params.put("SEARCHCODEISUSE", "Y");
        List<HashMap<String, String>> formlist = bookService.getLearningFormList(params);
        List<HashMap<String, String>> subjectteacherlist = bookService.getCaSubjectTeacherList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("kindlist", kindlist);
        result.put("formlist", formlist);
        result.put("subjectteacherlist", subjectteacherlist);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : save
     * @작성일 : 2024
     * @Method 설명 : 교재 등록
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping("/save")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject save(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        String[] SUBJECT_SJT_CD_ARR = request.getParameterValues("SUBJECT_SJT_CD");
        String[] CATEGORY_CD_ARR = request.getParameterValues("CATEGORY_CD");
        String[] LEARNING_CD_ARR = request.getParameterValues("LEARNING_CD");

        params.put("SEQ", String.valueOf(bookService.getCaBookSeq(params)));
        for(int j=0; j<CATEGORY_CD_ARR.length; j++){
            params.put("CATEGORY_CD", CATEGORY_CD_ARR[j]);

            for(int k=0; k<LEARNING_CD_ARR.length; k++){
                params.put("LEARNING_CD", LEARNING_CD_ARR[k]);
                String SUBJECT_SJT_CD = "";

                for(int i=0; i<SUBJECT_SJT_CD_ARR.length; i++){
                    if(!"".equals(SUBJECT_SJT_CD)){
                        SUBJECT_SJT_CD += ",";
                    }
                    SUBJECT_SJT_CD += SUBJECT_SJT_CD_ARR[i];
                }
                params.put("SUBJECT_SJT_CD", SUBJECT_SJT_CD);
                params.put("RSC_ID", bookService.getCaBookRscId(params));
                bookService.bookInsert(params);
            }
        }

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재가 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : update
     * @작성일 : 2024
     * @Method 설명 : 교재 수정
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping("/update")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject update(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        String[] SUBJECT_SJT_CD_ARR = request.getParameterValues("SUBJECT_SJT_CD");
        params.put("UPDATE_FLAG", CommonUtil.isNull(request.getParameter("UPDATE_FLAG"), ""));
        String SUBJECT_SJT_CD = "";
        for(int i=0; i<SUBJECT_SJT_CD_ARR.length; i++){
            if(!"".equals(SUBJECT_SJT_CD)){
                SUBJECT_SJT_CD += ",";
            }
            SUBJECT_SJT_CD += SUBJECT_SJT_CD_ARR[i];
        }
        params.put("SUBJECT_SJT_CD", SUBJECT_SJT_CD);
        bookService.bookUpdate(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재가 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : delete
     * @작성일 : 2024
     * @Method 설명 : 교재 삭제
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/delete")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject delete(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);
        bookService.bookDelete(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "교재가 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : deleteAll
     * @작성일 : 2024
     * @Method 설명 : 교재 관련도서 전체 삭제
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/deleteAll")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteAll(@RequestBody HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);
        params.put("GUBN","all");
        bookService.bookDelete(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "관련 도서가 모두 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : sellList
     * @작성일 : 2024
     * @Method 설명 : 교재 판매 목록 조회
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/sellList")
    public JSONObject sellList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = bookService.bookSellList(params);
        int listCount = bookService.bookSellListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : sellListExcel
     * @작성일 : 2024
     * @Method 설명 : 교재 판매 목록 엑셀 데이터
     * @param params
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/sellListExcel")
    public JSONObject sellListExcel(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        /* 페이징 */
        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;
        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));
        /* 페이징 */

        List<HashMap<String, String>> list = bookService.bookSellListExcel(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * @Method Name : setParam
     * @작성일 : 2024
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
        params.put("SEARCHKIND", CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

        params.put("SEQ", CommonUtil.isNull(request.getParameter("SEQ"), ""));
        params.put("RSC_ID", CommonUtil.isNull(request.getParameter("RSC_ID"), ""));
        params.put("CATEGORY_CD", CommonUtil.isNull(request.getParameter("CATEGORY_CD"), ""));
        params.put("LEARNING_CD", CommonUtil.isNull(request.getParameter("LEARNING_CD"), ""));
        params.put("BOOK_NM", CommonUtil.isNull(request.getParameter("BOOK_NM"), ""));
        params.put("BOOK_INFO", CommonUtil.isNull(request.getParameter("BOOK_INFO"), ""));
        params.put("BOOK_MEMO", CommonUtil.isNull(request.getParameter("BOOK_MEMO"), ""));
        params.put("BOOK_KEYWORD", CommonUtil.isNull(request.getParameter("BOOK_KEYWORD"), ""));
        params.put("ISSUE_DATE", CommonUtil.isNull(request.getParameter("ISSUE_DATE"), ""));
        params.put("COVER_TYPE", CommonUtil.isNull(request.getParameter("COVER_TYPE"), ""));
        params.put("BOOK_CONTENTS", CommonUtil.isNull(request.getParameter("BOOK_CONTENTS"), ""));
        params.put("PRICE", CommonUtil.isNull(request.getParameter("PRICE"), ""));
        params.put("DISCOUNT", CommonUtil.isNull(request.getParameter("DISCOUNT"), ""));
        params.put("DISCOUNT_PRICE", CommonUtil.isNull(request.getParameter("DISCOUNT_PRICE"), ""));
        params.put("POINT", CommonUtil.isNull(request.getParameter("POINT"), ""));
        params.put("BOOK_PUBLISHERS", CommonUtil.isNull(request.getParameter("BOOK_PUBLISHERS"), ""));
        params.put("BOOK_AUTHOR", CommonUtil.isNull(request.getParameter("BOOK_AUTHOR"), ""));
        params.put("BOOK_SUPPLEMENTDATA", CommonUtil.isNull(request.getParameter("BOOK_SUPPLEMENTDATA"), ""));
        params.put("BOOK_PRINTINGDATE", CommonUtil.isNull(request.getParameter("BOOK_PRINTINGDATE"), ""));
        params.put("BOOK_MAIN", CommonUtil.isNull(request.getParameter("BOOK_MAIN"), ""));
        params.put("BOOK_SUB", CommonUtil.isNull(request.getParameter("BOOK_SUB"), ""));
        params.put("BOOK_STUDENTBOOK", CommonUtil.isNull(request.getParameter("BOOK_STUDENTBOOK"), ""));
        params.put("BOOK_STOCK", CommonUtil.isNull(request.getParameter("BOOK_STOCK"), ""));
        params.put("FREE_POST", CommonUtil.isNull(request.getParameter("FREE_POST"), ""));
        params.put("BOOK_DATE", CommonUtil.isNull(request.getParameter("BOOK_DATE"), ""));
        params.put("NEW_BOOK", CommonUtil.isNull(request.getParameter("NEW_BOOK"), ""));
        params.put("MAIN_VIEW", CommonUtil.isNull(request.getParameter("MAIN_VIEW"), ""));
        params.put("USE_YN", CommonUtil.isNull(request.getParameter("USE_YN"), ""));
        params.put("BOOK_PAGE", CommonUtil.isNull(request.getParameter("BOOK_PAGE"), ""));
        params.put("BOOK_FORMAT", CommonUtil.isNull(request.getParameter("BOOK_FORMAT"), ""));
    }
}
