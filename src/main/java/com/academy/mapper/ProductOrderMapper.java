package com.academy.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * ProductOrder Mapper Interface
 */
@Mapper
public interface ProductOrderMapper {

    @SuppressWarnings({ "rawtypes" })
    List getOrderStatuscodeList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getPaymentList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOrderListDB_0(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getOrderListCount_0(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOrderListDB_freelec(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getOrderListCount_freelec(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOrderListDB(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getOrderListCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblOrderMgntListDB(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List<HashMap<String, String>> getOrderExcelListDB(HashMap<String, String> params);

    @SuppressWarnings({ "rawtypes" })
    int setPayKindUpdate(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDepositStatus1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getDepositStatusCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDepositStatus2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDepositStatus3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDepositStatus4(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDepositStatus5(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateBookStockPlus(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getPmpdownloadListPop(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getMobiledownloadListPop(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblOrderList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOrderListPopDB(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCourse_type_code(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getPlayyn(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getPoint(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblOrderMgntNoPopViewList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getTblOrderMgntNoPopViewCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getPrice_Sum(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getOldRefundViewCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getRefund_Point(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOldRefundView(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblDeliver_refund_list(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblApprovalsViewList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getTblDeliversViewListCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblDeliversViewList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTblOrdersViewList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getLecMstViewList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDelivers(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateApprovals(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateDeliversWmv(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMoney1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateStudy_Per(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getMoneySum(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMoney2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int refund_money_delete(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getMylecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture4(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateEndDateMyLecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getMylectureCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture5(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertMylecture6(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOrderMgntNo1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOrderMgntNo2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOrderMgntNo3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOrderMgntNo2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateApprovals2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertMileageHistory(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertMileageHistory3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMaMember(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture_1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture_2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOrderMgntNo1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMileageHistory(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMaMember2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateApprovals3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOrderMgntNo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMaMember3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateApprovals4(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertMileageHistory2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMylecture_3(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateApprovals5(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertSendMsgMultiSendUser(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTmMember_View(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCsccode(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List Cs_board_list(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getCsBoardListCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTm_mycoupon_list_admin(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getTm_mycoupon_listCount_admin(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List Tm_Class_List(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List Off_Class_List(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getTmClassListCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getMemoList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getMemoListCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertBoardCs(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateMemo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getSelectBefore_Point(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTmCoupon(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTmCouponList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getTmMoCouponList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getTmCouponCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getTmMoCouponCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getCouponCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertTmCoupon(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertMyCoupon(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOffOrderListDB(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getOffOrderListCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getOffTblOrderMgntListDB(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List<HashMap<String, String>> getOffOrderExcelListDB(HashMap<String, String> params);

    @SuppressWarnings({ "rawtypes" })
    List getIdChk(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCaCatCdList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getVwMenuMstTree_lec(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCaSubjectCdList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getCbLecMstFreeOrderList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int getCbLecMstListFreeOrderCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getChangePrice(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getUCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertCart(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getSubCode2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteCart(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteAllCart(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getMCount(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOffOrders(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOffApprovals(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOffOrderMgntNo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOffMylecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int modifyOffMylecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOffMylecture_N(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getUpdateDetail(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getSubCodeUp(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getSubList(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffOrders(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int Coupon_Del(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffApprovals(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffMgntNo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffMgntNo_N(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOffOrderMgntNo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOffMylecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOffOrders(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOffApprovals(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    List getPrintPop(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updatePrintOffOrders1(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updatePrintOffOrders2(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffRefund(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertRefundOffOrderMgntNo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffMylecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffMylectureRefund(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int insertOffRefund(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOffCancelOrderMgntNo(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int deleteOffCancelRefund(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int updateOffCancelMylecture(Map keyName);

    @SuppressWarnings({ "rawtypes" })
    int Delete_Year_Package_Point(Map keyName);

    List<HashMap<String, String>> getTmPointHistory(HashMap<String, String> params);

    void insertTmBoard(Map<String, Object> searchMap);

    List<HashMap<String, String>> getTmBoardList(Map<String, Object> searchMap);

    List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params);

    List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params);

    List<HashMap<String, String>> getOffApprovalsCount(Map<String, String> searchMap);

    List<HashMap<String, String>> getCcode(HashMap<String, String> params);

    void insertMyLecture(Map<String, String> params);

    void insertMyLectureN(Map<String, String> params);

    @SuppressWarnings({ "rawtypes" })
    int getPlusPoint(Map keyName);

    void BookPointDel(Map<String, Object> params);

    void BookPointIns(Map<String, Object> params);

    void insertOffOrderLog(Map<String, Object> params);
}