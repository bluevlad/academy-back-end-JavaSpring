package com.academy.banner.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
 * 배너 아이템 VO 클래스 (TB_BANNER_ITEM 테이블 매핑)
 * @author system
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일           수정자                수정내용
 *  ---------------    --------------    ---------------------------
 *  2025.12.10         system            배너 아이템 관리 등록
 * </pre>
 */
public class BannerItemVO extends CommonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 배너 아이템 SEQ (Primary Key) */
    private String seq;

    /** 부모 배너 SEQ (TB_BANNER.SEQ) */
    private String pSeq;

    /** 노출 순서 */
    private int rolIdx;

    /** 배너 부제목 */
    private String bannerSubtitle;

    /** 배너 설명 */
    private String bannerNote;

    /** 배너 이미지 경로 */
    private String bannerImage;

    /** 배너 썸네일 이미지 경로 */
    private String bannerThumbnailImage;

    /** 배너 링크 URL */
    private String bannerLink;

    /** 배너 링크 타겟 (_blank, _self 등) */
    private String bannerLinkTarget;

    /** 배너 링크 텍스트 (조회용) */
    private String bannerLinkTxt;

    /** 배너 시작일 */
    private String bannerSdt;

    /** 배너 종료일 */
    private String bannerEdt;

    /** 클릭수 */
    private int clickCnt;

    /** 그룹 SEQ (카테고리 일괄 등록용) */
    private String gSeq;

    /** 배너 타입 (부모에서 가져옴, 조회용) */
    private String bannerTyp;

    /** 온/오프라인 구분 (조회용) */
    private String onoffDiv;

    /** 화면 구분 (조회용) */
    private String screenGubun;

    /** 카테고리 코드 (조회용) */
    private String categoryCd;

    /** 배너 번호 (조회용) */
    private String bannerNo;

    /** 카테고리 코드 목록 (일괄 등록용, / 구분자) */
    private String categoryCds;

    /** 원래 카테고리 코드 목록 (수정용) */
    private String orgCategoryCds;

    /** 검색 조건 - 검색어 */
    private String searchText;

    /** 검색 조건 - 노출순서 */
    private String searchRolIdx;

    /** 검색 조건 - 사용여부 */
    private String searchIsUse;

    /** 검색 조건 - 서브카테고리 */
    private String searchSubCategory;

    /** 메뉴타입 (FM_ROOT: 오프라인, OM_ROOT: 온라인) */
    private String menuType;

    // Getters and Setters
    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getpSeq() {
        return pSeq;
    }

    public void setpSeq(String pSeq) {
        this.pSeq = pSeq;
    }

    public int getRolIdx() {
        return rolIdx;
    }

    public void setRolIdx(int rolIdx) {
        this.rolIdx = rolIdx;
    }

    public String getBannerSubtitle() {
        return bannerSubtitle;
    }

    public void setBannerSubtitle(String bannerSubtitle) {
        this.bannerSubtitle = bannerSubtitle;
    }

    public String getBannerNote() {
        return bannerNote;
    }

    public void setBannerNote(String bannerNote) {
        this.bannerNote = bannerNote;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBannerThumbnailImage() {
        return bannerThumbnailImage;
    }

    public void setBannerThumbnailImage(String bannerThumbnailImage) {
        this.bannerThumbnailImage = bannerThumbnailImage;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }

    public String getBannerLinkTarget() {
        return bannerLinkTarget;
    }

    public void setBannerLinkTarget(String bannerLinkTarget) {
        this.bannerLinkTarget = bannerLinkTarget;
    }

    public String getBannerLinkTxt() {
        return bannerLinkTxt;
    }

    public void setBannerLinkTxt(String bannerLinkTxt) {
        this.bannerLinkTxt = bannerLinkTxt;
    }

    public String getBannerSdt() {
        return bannerSdt;
    }

    public void setBannerSdt(String bannerSdt) {
        this.bannerSdt = bannerSdt;
    }

    public String getBannerEdt() {
        return bannerEdt;
    }

    public void setBannerEdt(String bannerEdt) {
        this.bannerEdt = bannerEdt;
    }

    public int getClickCnt() {
        return clickCnt;
    }

    public void setClickCnt(int clickCnt) {
        this.clickCnt = clickCnt;
    }

    public String getgSeq() {
        return gSeq;
    }

    public void setgSeq(String gSeq) {
        this.gSeq = gSeq;
    }

    public String getBannerTyp() {
        return bannerTyp;
    }

    public void setBannerTyp(String bannerTyp) {
        this.bannerTyp = bannerTyp;
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

    public String getBannerNo() {
        return bannerNo;
    }

    public void setBannerNo(String bannerNo) {
        this.bannerNo = bannerNo;
    }

    public String getCategoryCds() {
        return categoryCds;
    }

    public void setCategoryCds(String categoryCds) {
        this.categoryCds = categoryCds;
    }

    public String getOrgCategoryCds() {
        return orgCategoryCds;
    }

    public void setOrgCategoryCds(String orgCategoryCds) {
        this.orgCategoryCds = orgCategoryCds;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchRolIdx() {
        return searchRolIdx;
    }

    public void setSearchRolIdx(String searchRolIdx) {
        this.searchRolIdx = searchRolIdx;
    }

    public String getSearchIsUse() {
        return searchIsUse;
    }

    public void setSearchIsUse(String searchIsUse) {
        this.searchIsUse = searchIsUse;
    }

    public String getSearchSubCategory() {
        return searchSubCategory;
    }

    public void setSearchSubCategory(String searchSubCategory) {
        this.searchSubCategory = searchSubCategory;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}
