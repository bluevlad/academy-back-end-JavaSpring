package com.academy.productorder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.productorder.service.ProductOrderService;

/**
 * ProductOrder API Controller
 * 상품 주문 관리 RESTful API
 */
@RestController
@RequestMapping("/api/productorder")
public class ProductOrderApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private ProductOrderService productOrderService;

    @Autowired
    public ProductOrderApi(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    /**
     * 온라인 주문 목록 조회
     */
    @GetMapping("/list")
    public JSONObject list(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.getOrderListDB(params);
        int listCount = productOrderService.getOrderListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 0원 주문 목록 조회
     */
    @GetMapping("/list0")
    public JSONObject list0(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.getOrderListDB_0(params);
        int listCount = productOrderService.getOrderListCount_0(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 무료 강의 주문 목록 조회
     */
    @GetMapping("/listFreelec")
    public JSONObject listFreelec(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.getOrderListDB_freelec(params);
        int listCount = productOrderService.getOrderListCount_freelec(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 목록 조회
     */
    @GetMapping("/offList")
    public JSONObject offList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.getOffOrderListDB(params);
        int listCount = productOrderService.getOffOrderListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 주문 상세 조회
     */
    @GetMapping("/view")
    public JSONObject view(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        @SuppressWarnings("rawtypes")
        List approvalsList = productOrderService.getTblApprovalsViewList(params);
        @SuppressWarnings("rawtypes")
        List ordersList = productOrderService.getTblOrdersViewList(params);
        @SuppressWarnings("rawtypes")
        List orderMgntList = productOrderService.getTblOrderMgntNoPopViewList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("approvalsList", approvalsList);
        result.put("ordersList", ordersList);
        result.put("orderMgntList", orderMgntList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 주문 상태 코드 목록
     */
    @GetMapping("/statusCodeList")
    public JSONObject statusCodeList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        @SuppressWarnings("rawtypes")
        List statusList = productOrderService.getOrderStatuscodeList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("statusList", statusList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 결제 수단 목록
     */
    @GetMapping("/paymentList")
    public JSONObject paymentList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        @SuppressWarnings("rawtypes")
        List paymentList = productOrderService.getPaymentList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("paymentList", paymentList);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 온라인 주문 엑셀 다운로드
     */
    @GetMapping("/excelList")
    public JSONObject excelList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> list = productOrderService.getOrderExcelListDB(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 엑셀 다운로드
     */
    @GetMapping("/offExcelList")
    public JSONObject offExcelList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> list = productOrderService.getOffOrderExcelListDB(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 결제 수단 변경
     */
    @PutMapping("/updatePayKind")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updatePayKind(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.setPayKindUpdate(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "결제 수단이 변경되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 입금 상태 업데이트
     */
    @PutMapping("/updateDepositStatus")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateDepositStatus(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.updateDepositStatus1(params);
        productOrderService.updateDepositStatus2(params);
        productOrderService.updateDepositStatus3(params);
        productOrderService.updateDepositStatus4(params);
        productOrderService.updateDepositStatus5(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "입금 상태가 업데이트되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 진도율 업데이트
     */
    @PutMapping("/updateStudyPer")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateStudyPer(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.updateStudy_Per(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "진도율이 업데이트되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 등록
     */
    @PostMapping("/insertOffOrder")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertOffOrder(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.insertOffOrders(params);
        productOrderService.insertOffApprovals(params);
        productOrderService.insertOffOrderMgntNo(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "오프라인 주문이 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 수정
     */
    @PutMapping("/updateOffOrder")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateOffOrder(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.updateOffOrders(params);
        productOrderService.updateOffApprovals(params);
        productOrderService.updateOffMgntNo(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "오프라인 주문이 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 오프라인 주문 삭제
     */
    @DeleteMapping("/deleteOffOrder")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteOffOrder(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.deleteOffOrderMgntNo(params);
        productOrderService.deleteOffMylecture(params);
        productOrderService.deleteOffOrders(params);
        productOrderService.deleteOffApprovals(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "오프라인 주문이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 환불 처리
     */
    @PostMapping("/insertRefund")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertRefund(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.updateOffRefund(params);
        productOrderService.insertRefundOffOrderMgntNo(params);
        productOrderService.updateOffMylecture(params);
        productOrderService.insertOffRefund(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "환불이 처리되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 환불 취소
     */
    @DeleteMapping("/deleteRefund")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteRefund(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.deleteOffCancelOrderMgntNo(params);
        productOrderService.deleteOffCancelRefund(params);
        productOrderService.updateOffCancelMylecture(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "환불이 취소되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 정보 조회
     */
    @GetMapping("/memberView")
    public JSONObject memberView(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        @SuppressWarnings("rawtypes")
        List memberInfo = productOrderService.getTmMember_View(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("memberInfo", memberInfo);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 포인트 히스토리
     */
    @GetMapping("/pointHistory")
    public JSONObject pointHistory(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> list = productOrderService.getTmPointHistory(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 쿠폰 목록
     */
    @GetMapping("/memberCouponList")
    public JSONObject memberCouponList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.getTm_mycoupon_list_admin(params);
        int listCount = productOrderService.getTm_mycoupon_listCount_admin(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 강의 목록 (온라인)
     */
    @GetMapping("/memberClassList")
    public JSONObject memberClassList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.Tm_Class_List(params);
        int listCount = productOrderService.getTmClassListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 회원 강의 목록 (오프라인)
     */
    @GetMapping("/memberOffClassList")
    public JSONObject memberOffClassList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        @SuppressWarnings("rawtypes")
        List list = productOrderService.Off_Class_List(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * CS 게시판 목록
     */
    @GetMapping("/csBoardList")
    public JSONObject csBoardList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.Cs_board_list(params);
        int listCount = productOrderService.getCsBoardListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * CS 게시판 등록
     */
    @PostMapping("/insertCsBoard")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertCsBoard(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.insertBoardCs(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "CS 게시판에 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 메모 목록
     */
    @GetMapping("/memoList")
    public JSONObject memoList(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        int currentPage = Integer.parseInt(params.get("currentPage"));
        int pageRow = Integer.parseInt(params.get("pageRow"));
        int startNo = (currentPage - 1) * pageRow;
        int endNo = startNo + pageRow;

        params.put("startNo", String.valueOf(startNo));
        params.put("endNo", String.valueOf(endNo));

        @SuppressWarnings("rawtypes")
        List list = productOrderService.getMemoList(params);
        int listCount = productOrderService.getMemoListCount(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("totalCount", listCount);
        result.put("totalPage", (int) Math.ceil((double) listCount / pageRow));
        result.put("currentPage", currentPage);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 메모 수정
     */
    @PutMapping("/updateMemo")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject updateMemo(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.updateMemo(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "메모가 수정되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 발급
     */
    @PostMapping("/insertCoupon")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertCoupon(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.insertTmCoupon(params);
        productOrderService.insertMyCoupon(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "쿠폰이 발급되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 쿠폰 삭제
     */
    @DeleteMapping("/deleteCoupon")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject deleteCoupon(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.Coupon_Del(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "쿠폰이 삭제되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 내 강의실 강의 등록
     */
    @PostMapping("/insertMyLecture")
    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public JSONObject insertMyLecture(@ModelAttribute HashMap<String, String> params, HttpServletRequest request) throws Exception {
        setParam(params, request);

        productOrderService.insertMyLecture(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", "success");
        result.put("message", "내 강의실에 강의가 등록되었습니다");

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }

    /**
     * 파라미터 SETTING
     */
    private void setParam(HashMap<String, String> params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        @SuppressWarnings("unchecked")
        HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");

        params.put("REG_ID", loginInfo.get("USER_ID"));
        params.put("UPD_ID", loginInfo.get("USER_ID"));
        params.put("MANAGER_ID", loginInfo.get("USER_ID"));

        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit)));

        // 공통 검색 파라미터
        params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
        params.put("schtype", CommonUtil.isNull(request.getParameter("schtype"), ""));
        params.put("keyword", CommonUtil.isNull(request.getParameter("keyword"), ""));
        params.put("searchkey", CommonUtil.isNull(request.getParameter("searchkey"), ""));
        params.put("searchtype", CommonUtil.isNull(request.getParameter("searchtype"), ""));

        // 날짜 관련 파라미터
        params.put("sdate", CommonUtil.isNull(request.getParameter("sdate"), ""));
        params.put("edate", CommonUtil.isNull(request.getParameter("edate"), ""));
        params.put("SDATE", CommonUtil.isNull(request.getParameter("SDATE"), ""));
        params.put("EDATE", CommonUtil.isNull(request.getParameter("EDATE"), ""));
        params.put("search_date_type", CommonUtil.isNull(request.getParameter("search_date_type"), ""));

        // 주문 관련 파라미터
        params.put("orderstatuscode", CommonUtil.isNull(request.getParameter("orderstatuscode"), ""));
        params.put("code_no", CommonUtil.isNull(request.getParameter("code_no"), ""));
        params.put("sys_cd", CommonUtil.isNull(request.getParameter("sys_cd"), ""));
        params.put("sortfield", CommonUtil.isNull(request.getParameter("sortfield"), ""));
        params.put("sort", CommonUtil.isNull(request.getParameter("sort"), ""));

        // 기타 파라미터
        params.put("ORDER_NO", CommonUtil.isNull(request.getParameter("ORDER_NO"), ""));
        params.put("APPROVAL_NO", CommonUtil.isNull(request.getParameter("APPROVAL_NO"), ""));
        params.put("USER_ID", CommonUtil.isNull(request.getParameter("USER_ID"), ""));
        params.put("MEMBER_ID", CommonUtil.isNull(request.getParameter("MEMBER_ID"), ""));
        params.put("LEC_CD", CommonUtil.isNull(request.getParameter("LEC_CD"), ""));
        params.put("COURSE_CD", CommonUtil.isNull(request.getParameter("COURSE_CD"), ""));
        params.put("CCODE", CommonUtil.isNull(request.getParameter("CCODE"), ""));
        params.put("PRICE", CommonUtil.isNull(request.getParameter("PRICE"), ""));
        params.put("POINT", CommonUtil.isNull(request.getParameter("POINT"), ""));
        params.put("PAY_KIND", CommonUtil.isNull(request.getParameter("PAY_KIND"), ""));
        params.put("DEPOSIT_STATUS", CommonUtil.isNull(request.getParameter("DEPOSIT_STATUS"), ""));
        params.put("REFUND_PRICE", CommonUtil.isNull(request.getParameter("REFUND_PRICE"), ""));
        params.put("REFUND_REASON", CommonUtil.isNull(request.getParameter("REFUND_REASON"), ""));
    }
}