package com.academy.book;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.book.service.BookService;
import com.academy.book.service.BookVO;
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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/list")
    public JSONObject list(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/writeData")
    public JSONObject writeData(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @PostMapping("/save")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject save(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @PutMapping("/update")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject update(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/delete")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject delete(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);
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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @DeleteMapping("/deleteAll")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteAll(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);
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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/sellList")
    public JSONObject sellList(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @return JSONObject
     * @throws Exception
     */
    @GetMapping("/sellListExcel")
    public JSONObject sellListExcel(@ModelAttribute("BookVO") BookVO bookVO, @RequestParam Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        setParam(bookVO, commandMap, request);

        // Convert BookVO to HashMap for service layer
        HashMap<String, String> params = convertToHashMap(bookVO);

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
     * @param bookVO
     * @param commandMap
     * @param request
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setParam(BookVO bookVO, Map<?, ?> commandMap, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
            if(loginInfo != null) {
                bookVO.setRegId(loginInfo.get("USER_ID"));
                bookVO.setUpdId(loginInfo.get("USER_ID"));
            }
        }

        // Set pagination parameters (using CommonVO fields)
        bookVO.setPageIndex(Integer.parseInt(CommonUtil.isNull(request.getParameter("currentPage"), "1")));
        bookVO.setPageUnit(Integer.parseInt(CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit))));

        // Set search parameters (using CommonVO fields)
        bookVO.setSearchKind(CommonUtil.isNull(request.getParameter("SEARCHKIND"), ""));
        bookVO.setSearchType(CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        bookVO.setSearchText(CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
    }

    /**
     * @Method Name : convertToHashMap
     * @작성일 : 2024
     * @Method 설명 : BookVO를 HashMap으로 변환 (Service Layer 호환성)
     * @param bookVO
     * @return HashMap<String, String>
     * @throws Exception
     */
    private HashMap<String, String> convertToHashMap(BookVO bookVO) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();

        // Basic fields
        if(bookVO.getSeq() != null) params.put("SEQ", String.valueOf(bookVO.getSeq()));
        if(bookVO.getRscId() != null) params.put("RSC_ID", bookVO.getRscId());
        if(bookVO.getSubjectSjtCd() != null) params.put("SUBJECT_SJT_CD", bookVO.getSubjectSjtCd());
        if(bookVO.getCategoryCd() != null) params.put("CATEGORY_CD", bookVO.getCategoryCd());
        if(bookVO.getLearningCd() != null) params.put("LEARNING_CD", bookVO.getLearningCd());
        if(bookVO.getBookNm() != null) params.put("BOOK_NM", bookVO.getBookNm());
        if(bookVO.getBookInfo() != null) params.put("BOOK_INFO", bookVO.getBookInfo());
        if(bookVO.getBookMemo() != null) params.put("BOOK_MEMO", bookVO.getBookMemo());
        if(bookVO.getBookKeyword() != null) params.put("BOOK_KEYWORD", bookVO.getBookKeyword());
        if(bookVO.getIssueDate() != null) params.put("ISSUE_DATE", bookVO.getIssueDate());
        if(bookVO.getCoverType() != null) params.put("COVER_TYPE", bookVO.getCoverType());
        if(bookVO.getBookContents() != null) params.put("BOOK_CONTENTS", bookVO.getBookContents());
        if(bookVO.getPrice() != null) params.put("PRICE", String.valueOf(bookVO.getPrice()));
        if(bookVO.getDiscount() != null) params.put("DISCOUNT", String.valueOf(bookVO.getDiscount()));
        if(bookVO.getDiscountPrice() != null) params.put("DISCOUNT_PRICE", String.valueOf(bookVO.getDiscountPrice()));
        if(bookVO.getPoint() != null) params.put("POINT", String.valueOf(bookVO.getPoint()));
        if(bookVO.getBookPublishers() != null) params.put("BOOK_PUBLISHERS", bookVO.getBookPublishers());
        if(bookVO.getBookAuthor() != null) params.put("BOOK_AUTHOR", bookVO.getBookAuthor());
        if(bookVO.getBookSupplementdata() != null) params.put("BOOK_SUPPLEMENTDATA", bookVO.getBookSupplementdata());
        if(bookVO.getBookPrintingdate() != null) params.put("BOOK_PRINTINGDATE", bookVO.getBookPrintingdate());
        if(bookVO.getBookMain() != null) params.put("BOOK_MAIN", bookVO.getBookMain());
        if(bookVO.getBookSub() != null) params.put("BOOK_SUB", bookVO.getBookSub());
        if(bookVO.getBookStudentbook() != null) params.put("BOOK_STUDENTBOOK", bookVO.getBookStudentbook());
        if(bookVO.getBookStock() != null) params.put("BOOK_STOCK", String.valueOf(bookVO.getBookStock()));
        if(bookVO.getFreePost() != null) params.put("FREE_POST", bookVO.getFreePost());
        if(bookVO.getBookDate() != null) params.put("BOOK_DATE", bookVO.getBookDate());
        if(bookVO.getNewBook() != null) params.put("NEW_BOOK", bookVO.getNewBook());
        if(bookVO.getMainView() != null) params.put("MAIN_VIEW", bookVO.getMainView());
        if(bookVO.getUseYn() != null) params.put("USE_YN", bookVO.getUseYn());
        if(bookVO.getBookPage() != null) params.put("BOOK_PAGE", bookVO.getBookPage());
        if(bookVO.getBookFormat() != null) params.put("BOOK_FORMAT", bookVO.getBookFormat());

        // Audit fields
        if(bookVO.getRegId() != null) params.put("REG_ID", bookVO.getRegId());
        if(bookVO.getUpdId() != null) params.put("UPD_ID", bookVO.getUpdId());

        // Search and pagination fields (using CommonVO methods)
        params.put("currentPage", String.valueOf(bookVO.getCurrentPage()));  // alias for pageIndex
        params.put("pageRow", String.valueOf(bookVO.getPageRow()));  // alias for pageUnit
        if(bookVO.getStartNo() != null && !bookVO.getStartNo().isEmpty()) params.put("startNo", bookVO.getStartNo());
        if(bookVO.getEndNo() != null && !bookVO.getEndNo().isEmpty()) params.put("endNo", bookVO.getEndNo());
        if(bookVO.getSearchKind() != null && !bookVO.getSearchKind().isEmpty()) params.put("SEARCHKIND", bookVO.getSearchKind());
        if(bookVO.getSearchType() != null && !bookVO.getSearchType().isEmpty()) params.put("SEARCHTYPE", bookVO.getSearchType());
        if(bookVO.getSearchText() != null && !bookVO.getSearchText().isEmpty()) params.put("SEARCHTEXT", bookVO.getSearchText());
        if(bookVO.getSearchGubn() != null && !bookVO.getSearchGubn().isEmpty()) params.put("SEARCHGUBN", bookVO.getSearchGubn());
        if(bookVO.getSearchCodeIsUse() != null && !bookVO.getSearchCodeIsUse().isEmpty()) params.put("SEARCHCODEISUSE", bookVO.getSearchCodeIsUse());
        if(bookVO.getUpdateFlag() != null && !bookVO.getUpdateFlag().isEmpty()) params.put("UPDATE_FLAG", bookVO.getUpdateFlag());
        if(bookVO.getGubn() != null && !bookVO.getGubn().isEmpty()) params.put("GUBN", bookVO.getGubn());

        return params;
    }
}