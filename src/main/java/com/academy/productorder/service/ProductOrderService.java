package com.academy.productorder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.academy.mapper.ProductOrderMapper;

/**
 * ProductOrder Service
 * LoginService 패턴 적용 - ServiceImpl 없이 @Service 클래스로 직접 구현
 * last modified 2014-08-20
 */
@Service
public class ProductOrderService {

	private ProductOrderMapper productOrderMapper;

	public ProductOrderService(ProductOrderMapper productOrderMapper) {
		this.productOrderMapper = productOrderMapper;
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOrderStatuscodeList(Map keyName) {
		return productOrderMapper.getOrderStatuscodeList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getPaymentList(Map keyName) {
		return productOrderMapper.getPaymentList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOrderListDB_0(Map keyName) {
		return productOrderMapper.getOrderListDB_0(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getOrderListCount_0(Map keyName) {
		return productOrderMapper.getOrderListCount_0(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOrderListDB_freelec(Map keyName) {
		return productOrderMapper.getOrderListDB_freelec(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getOrderListCount_freelec(Map keyName) {
		return productOrderMapper.getOrderListCount_freelec(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOrderListDB(Map keyName) {
		return productOrderMapper.getOrderListDB(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getOrderListCount(Map keyName) {
		return productOrderMapper.getOrderListCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblOrderMgntListDB(Map keyName) {
		return productOrderMapper.getTblOrderMgntListDB(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List<HashMap<String, String>> getOrderExcelListDB(HashMap<String, String> params) {
		return productOrderMapper.getOrderExcelListDB(params);
	}

	@SuppressWarnings({ "rawtypes" })
	public int setPayKindUpdate(Map keyName) {
		return productOrderMapper.setPayKindUpdate(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus1(Map keyName) {
		return productOrderMapper.updateDepositStatus1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getDepositStatusCount(Map keyName) {
		return productOrderMapper.getDepositStatusCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus2(Map keyName) {
		return productOrderMapper.updateDepositStatus2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus3(Map keyName) {
		return productOrderMapper.updateDepositStatus3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus4(Map keyName) {
		return productOrderMapper.updateDepositStatus4(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus5(Map keyName) {
		return productOrderMapper.updateDepositStatus5(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateBookStockPlus(Map keyName) {
		return productOrderMapper.updateBookStockPlus(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getPmpdownloadListPop(Map keyName) {
		return productOrderMapper.getPmpdownloadListPop(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getMobiledownloadListPop(Map keyName) {
		return productOrderMapper.getMobiledownloadListPop(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblOrderList(Map keyName) {
		return productOrderMapper.getTblOrderList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOrderListPopDB(Map keyName) {
		return productOrderMapper.getOrderListPopDB(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getCourse_type_code(Map keyName) {
		return productOrderMapper.getCourse_type_code(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getPlayyn(Map keyName) {
		return productOrderMapper.getPlayyn(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getPoint(Map keyName) {
		return productOrderMapper.getPoint(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblOrderMgntNoPopViewList(Map keyName) {
		return productOrderMapper.getTblOrderMgntNoPopViewList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getTblOrderMgntNoPopViewCount(Map keyName) {
		return productOrderMapper.getTblOrderMgntNoPopViewCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getPrice_Sum(Map keyName) {
		return productOrderMapper.getPrice_Sum(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getOldRefundViewCount(Map keyName) {
		return productOrderMapper.getOldRefundViewCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getRefund_Point(Map keyName) {
		return productOrderMapper.getRefund_Point(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOldRefundView(Map keyName) {
		return productOrderMapper.getOldRefundView(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblDeliver_refund_list(Map keyName) {
		return productOrderMapper.getTblDeliver_refund_list(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblApprovalsViewList(Map keyName) {
		return productOrderMapper.getTblApprovalsViewList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getTblDeliversViewListCount(Map keyName) {
		return productOrderMapper.getTblDeliversViewListCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblDeliversViewList(Map keyName) {
		return productOrderMapper.getTblDeliversViewList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTblOrdersViewList(Map keyName) {
		return productOrderMapper.getTblOrdersViewList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getLecMstViewList(Map keyName) {
		return productOrderMapper.getLecMstViewList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDelivers(Map keyName) {
		return productOrderMapper.updateDelivers(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateApprovals(Map keyName) {
		return productOrderMapper.updateApprovals(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateDeliversWmv(Map keyName) {
		return productOrderMapper.updateDeliversWmv(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMoney1(Map keyName) {
		return productOrderMapper.updateMoney1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateStudy_Per(Map keyName) {
		return productOrderMapper.updateStudy_Per(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getMoneySum(Map keyName) {
		return productOrderMapper.getMoneySum(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMoney2(Map keyName) {
		return productOrderMapper.updateMoney2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int refund_money_delete(Map keyName) {
		return productOrderMapper.refund_money_delete(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getMylecture(Map keyName) {
		return productOrderMapper.getMylecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture1(Map keyName) {
		return productOrderMapper.updateMylecture1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture2(Map keyName) {
		return productOrderMapper.updateMylecture2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture3(Map keyName) {
		return productOrderMapper.updateMylecture3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture4(Map keyName) {
		return productOrderMapper.updateMylecture4(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateEndDateMyLecture(Map keyName) {
		return productOrderMapper.updateEndDateMyLecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getMylectureCount(Map keyName) {
		return productOrderMapper.getMylectureCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture5(Map keyName) {
		return productOrderMapper.updateMylecture5(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertMylecture6(Map keyName) {
		return productOrderMapper.insertMylecture6(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOrderMgntNo1(Map keyName) {
		return productOrderMapper.insertOrderMgntNo1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOrderMgntNo2(Map keyName) {
		return productOrderMapper.updateOrderMgntNo2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOrderMgntNo3(Map keyName) {
		return productOrderMapper.updateOrderMgntNo3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOrderMgntNo2(Map keyName) {
		return productOrderMapper.insertOrderMgntNo2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateApprovals2(Map keyName) {
		return productOrderMapper.updateApprovals2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertMileageHistory(Map keyName) {
		return productOrderMapper.insertMileageHistory(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertMileageHistory3(Map keyName) {
		return productOrderMapper.insertMileageHistory3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMaMember(Map keyName) {
		return productOrderMapper.updateMaMember(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture_1(Map keyName) {
		return productOrderMapper.updateMylecture_1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture_2(Map keyName) {
		return productOrderMapper.updateMylecture_2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOrderMgntNo1(Map keyName) {
		return productOrderMapper.updateOrderMgntNo1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMileageHistory(Map keyName) {
		return productOrderMapper.updateMileageHistory(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMaMember2(Map keyName) {
		return productOrderMapper.updateMaMember2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateApprovals3(Map keyName) {
		return productOrderMapper.updateApprovals3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOrderMgntNo(Map keyName) {
		return productOrderMapper.deleteOrderMgntNo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMaMember3(Map keyName) {
		return productOrderMapper.updateMaMember3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateApprovals4(Map keyName) {
		return productOrderMapper.updateApprovals4(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertMileageHistory2(Map keyName) {
		return productOrderMapper.insertMileageHistory2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMylecture_3(Map keyName) {
		return productOrderMapper.updateMylecture_3(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateApprovals5(Map keyName) {
		return productOrderMapper.updateApprovals5(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertSendMsgMultiSendUser(Map keyName) {
		return productOrderMapper.insertSendMsgMultiSendUser(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTmMember_View(Map keyName) {
		return productOrderMapper.getTmMember_View(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getCsccode(Map keyName) {
		return productOrderMapper.getCsccode(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List Cs_board_list(Map keyName) {
		return productOrderMapper.Cs_board_list(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getCsBoardListCount(Map keyName) {
		return productOrderMapper.getCsBoardListCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTm_mycoupon_list_admin(Map keyName) {
		return productOrderMapper.getTm_mycoupon_list_admin(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getTm_mycoupon_listCount_admin(Map keyName) {
		return productOrderMapper.getTm_mycoupon_listCount_admin(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List Tm_Class_List(Map keyName) {
		return productOrderMapper.Tm_Class_List(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List Off_Class_List(Map keyName) {
		return productOrderMapper.Off_Class_List(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getTmClassListCount(Map keyName) {
		return productOrderMapper.getTmClassListCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getMemoList(Map keyName) {
		return productOrderMapper.getMemoList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getMemoListCount(Map keyName) {
		return productOrderMapper.getMemoListCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertBoardCs(Map keyName) {
		return productOrderMapper.insertBoardCs(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateMemo(Map keyName) {
		return productOrderMapper.updateMemo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getSelectBefore_Point(Map keyName) {
		return productOrderMapper.getSelectBefore_Point(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTmCoupon(Map keyName) {
		return productOrderMapper.getTmCoupon(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTmCouponList(Map keyName) {
		return productOrderMapper.getTmCouponList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getTmMoCouponList(Map keyName) {
		return productOrderMapper.getTmMoCouponList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getTmCouponCount(Map keyName) {
		return productOrderMapper.getTmCouponCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getTmMoCouponCount(Map keyName) {
		return productOrderMapper.getTmMoCouponCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getCouponCount(Map keyName) {
		return productOrderMapper.getCouponCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertTmCoupon(Map keyName) {
		return productOrderMapper.insertTmCoupon(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertMyCoupon(Map keyName) {
		return productOrderMapper.insertMyCoupon(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOffOrderListDB(Map keyName) {
		return productOrderMapper.getOffOrderListDB(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getOffOrderListCount(Map keyName) {
		return productOrderMapper.getOffOrderListCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getOffTblOrderMgntListDB(Map keyName) {
		return productOrderMapper.getOffTblOrderMgntListDB(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List<HashMap<String, String>> getOffOrderExcelListDB(HashMap<String, String> params) {
		return productOrderMapper.getOffOrderExcelListDB(params);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getIdChk(Map keyName) {
		return productOrderMapper.getIdChk(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getCaCatCdList(Map keyName) {
		return productOrderMapper.getCaCatCdList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getVwMenuMstTree_lec(Map keyName) {
		return productOrderMapper.getVwMenuMstTree_lec(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getCaSubjectCdList(Map keyName) {
		return productOrderMapper.getCaSubjectCdList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getCbLecMstFreeOrderList(Map keyName) {
		return productOrderMapper.getCbLecMstFreeOrderList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getCbLecMstListFreeOrderCount(Map keyName) {
		return productOrderMapper.getCbLecMstListFreeOrderCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getChangePrice(Map keyName) {
		return productOrderMapper.getChangePrice(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getUCount(Map keyName) {
		return productOrderMapper.getUCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertCart(Map keyName) {
		return productOrderMapper.insertCart(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getSubCode2(Map keyName) {
		return productOrderMapper.getSubCode2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteCart(Map keyName) {
		return productOrderMapper.deleteCart(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteAllCart(Map keyName) {
		return productOrderMapper.deleteAllCart(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getMCount(Map keyName) {
		return productOrderMapper.getMCount(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOffOrders(Map keyName) {
		return productOrderMapper.insertOffOrders(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOffApprovals(Map keyName) {
		return productOrderMapper.insertOffApprovals(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOffOrderMgntNo(Map keyName) {
		return productOrderMapper.insertOffOrderMgntNo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOffMylecture(Map keyName) {
		return productOrderMapper.insertOffMylecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int modifyOffMylecture(Map keyName) {
		return productOrderMapper.modifyOffMylecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOffMylecture_N(Map keyName) {
		return productOrderMapper.insertOffMylecture_N(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getUpdateDetail(Map keyName) {
		return productOrderMapper.getUpdateDetail(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getSubCodeUp(Map keyName) {
		return productOrderMapper.getSubCodeUp(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getSubList(Map keyName) {
		return productOrderMapper.getSubList(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffOrders(Map keyName) {
		return productOrderMapper.updateOffOrders(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int Coupon_Del(Map keyName) {
		return productOrderMapper.Coupon_Del(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffApprovals(Map keyName) {
		return productOrderMapper.updateOffApprovals(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffMgntNo(Map keyName) {
		return productOrderMapper.updateOffMgntNo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffMgntNo_N(Map keyName) {
		return productOrderMapper.updateOffMgntNo_N(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOffOrderMgntNo(Map keyName) {
		return productOrderMapper.deleteOffOrderMgntNo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOffMylecture(Map keyName) {
		return productOrderMapper.deleteOffMylecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOffOrders(Map keyName) {
		return productOrderMapper.deleteOffOrders(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOffApprovals(Map keyName) {
		return productOrderMapper.deleteOffApprovals(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getPrintPop(Map keyName) {
		return productOrderMapper.getPrintPop(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updatePrintOffOrders1(Map keyName) {
		return productOrderMapper.updatePrintOffOrders1(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updatePrintOffOrders2(Map keyName) {
		return productOrderMapper.updatePrintOffOrders2(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffRefund(Map keyName) {
		return productOrderMapper.updateOffRefund(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertRefundOffOrderMgntNo(Map keyName) {
		return productOrderMapper.insertRefundOffOrderMgntNo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffMylecture(Map keyName) {
		return productOrderMapper.updateOffMylecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffMylectureRefund(Map keyName) {
		return productOrderMapper.updateOffMylectureRefund(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertOffRefund(Map keyName) {
		return productOrderMapper.insertOffRefund(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOffCancelOrderMgntNo(Map keyName) {
		return productOrderMapper.deleteOffCancelOrderMgntNo(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int deleteOffCancelRefund(Map keyName) {
		return productOrderMapper.deleteOffCancelRefund(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int updateOffCancelMylecture(Map keyName) {
		return productOrderMapper.updateOffCancelMylecture(keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int Delete_Year_Package_Point(Map keyName) {
		return productOrderMapper.Delete_Year_Package_Point(keyName);
	}

	public List<HashMap<String, String>> getTmPointHistory(HashMap<String, String> params) {
		return productOrderMapper.getTmPointHistory(params);
	}

	public void insertTmBoard(Map<String, Object> searchMap) {
		productOrderMapper.insertTmBoard(searchMap);
	}

	public List<HashMap<String, String>> getTmBoardList(Map<String, Object> searchMap) {
		return productOrderMapper.getTmBoardList(searchMap);
	}

	public List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params) {
		return productOrderMapper.getVOCCODEList(params);
	}

	public List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params) {
		return productOrderMapper.getDUTYCODEList(params);
	}

	public List<HashMap<String, String>> getOffApprovalsCount(Map<String, String> searchMap) {
		return productOrderMapper.getOffApprovalsCount(searchMap);
	}

	public List<HashMap<String, String>> getCcode(HashMap<String, String> params) {
		return productOrderMapper.getCcode(params);
	}

	public void insertMyLecture(Map<String, String> params) {
		productOrderMapper.insertMyLecture(params);
	}

	public void insertMyLectureN(Map<String, String> params) {
		productOrderMapper.insertMyLectureN(params);
	}

	@SuppressWarnings({ "rawtypes" })
	public int getPlusPoint(Map keyName) {
		return productOrderMapper.getPlusPoint(keyName);
	}

	public void BookPointDel(Map<String, Object> params) {
		productOrderMapper.BookPointDel(params);
	}

	public void BookPointIns(Map<String, Object> params) {
		productOrderMapper.BookPointIns(params);
	}

	public void insertOffOrderLog(Map<String, Object> params) {
		productOrderMapper.insertOffOrderLog(params);
	}
}