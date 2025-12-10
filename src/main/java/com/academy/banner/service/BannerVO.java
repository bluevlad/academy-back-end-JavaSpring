package com.academy.banner.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
 * 배너 VO 클래스 (TB_BANNER 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            배너 관리 등록
 * </pre>
 */
public class BannerVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 배너 SEQ (Primary Key) */
    private String seq;

    /** 온/오프라인 구분 (O: 온라인, F: 오프라인) */
    private String onoffDiv;

    /** 화면 구분 (M: 메인홈, H: 모바일홈, S: 서브페이지) */
    private String screenGubun;

    /** 카테고리 코드 */
    private String categoryCd;

    /** 카테고리명 (조회용) */
    private String categoryNm;

    /** 배너 번호 */
    private String bannerNo;

    /** 배너 제목 */
    private String bannerTitle;

    /** 배너 타입 (I: 이미지, L: 강의, B: 게시판, P: 사람, T: 모의고사) */
    private String bannerTyp;

    /** 게시 시작일 */
    private String openStartdate;

    /** 게시 종료일 */
    private String openEnddate;

    /** 조회수 */
    private int viewCount;

    /** 연결된 배너아이템 수 (조회용) */
    private int linkCount;

    /** 검색 조건 - 메뉴타입 (FM_ROOT: 오프라인, OM_ROOT: 온라인) */
    private String menuType;

    /** 검색 조건 - 카테고리 */
    private String searchCategory;

    /** 검색 조건 - 배너번호 */
    private String searchBannerNo;

    /** 검색 조건 - 사용여부 */
    private String searchIsUse;

    // Getters and Setters
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getOnoffDiv() {
        return onoffDiv;
    }

    public void setOnoffDiv(String onoffDiv) {
        this.onoffDiv = onoffDiv;
    }

    public String getScreenGubun() {
        return screenGubun;
    }

    public void setScreenGubun(String screenGubun) {
        this.screenGubun = screenGubun;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public String getCategoryNm() {
        return categoryNm;
    }

    public void setCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }

    public String getBannerNo() {
        return bannerNo;
    }

    public void setBannerNo(String bannerNo) {
        this.bannerNo = bannerNo;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerTyp() {
        return bannerTyp;
    }

    public void setBannerTyp(String bannerTyp) {
        this.bannerTyp = bannerTyp;
    }

    public String getOpenStartdate() {
        return openStartdate;
    }

    public void setOpenStartdate(String openStartdate) {
        this.openStartdate = openStartdate;
    }

    public String getOpenEnddate() {
        return openEnddate;
    }

    public void setOpenEnddate(String openEnddate) {
        this.openEnddate = openEnddate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(int linkCount) {
        this.linkCount = linkCount;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchBannerNo() {
        return searchBannerNo;
    }

    public void setSearchBannerNo(String searchBannerNo) {
        this.searchBannerNo = searchBannerNo;
    }

    public String getSearchIsUse() {
        return searchIsUse;
    }

    public void setSearchIsUse(String searchIsUse) {
        this.searchIsUse = searchIsUse;
    }
}
