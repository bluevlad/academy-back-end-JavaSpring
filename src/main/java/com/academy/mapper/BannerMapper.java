package com.academy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.banner.service.BannerItemVO;
import com.academy.banner.service.BannerVO;

/**
 * 배너정보에 관한 데이터 접근 클래스를 정의한다.
 * @author system
 * @since 2025.12.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.12.10         system          최초 생성
 * </pre>
 */
@Mapper
public interface BannerMapper {

    // =====================================================
    // 배너 마스터 (TB_BANNER) 관련
    // =====================================================

    /**
     * 배너 목록 조회
     * @param bannerVO 검색조건
     * @return List 배너 목록정보
     */
    List<JSONObject> selectBannerList(BannerVO bannerVO);

    /**
     * 배너 목록 건수 조회
     * @param bannerVO 검색조건
     * @return int 배너 목록 건수
     */
    int selectBannerListCount(BannerVO bannerVO);

    /**
     * 배너 상세 조회
     * @param bannerVO 검색조건
     * @return JSONObject 배너 상세정보
     */
    JSONObject selectBannerDetail(BannerVO bannerVO);

    /**
     * 배너 등록
     * @param bannerVO 배너정보
     */
    void insertBanner(BannerVO bannerVO);

    /**
     * 배너 수정
     * @param bannerVO 배너정보
     */
    void updateBanner(BannerVO bannerVO);

    /**
     * 배너 타입 일괄 변경
     * @param bannerVO 배너정보
     */
    void updateBannerType(BannerVO bannerVO);

    /**
     * 배너 삭제
     * @param bannerVO 배너정보
     */
    void deleteBanner(BannerVO bannerVO);

    // =====================================================
    // 배너 아이템 (TB_BANNER_ITEM) 관련
    // =====================================================

    /**
     * 배너 아이템 목록 조회
     * @param bannerItemVO 검색조건
     * @return List 배너 아이템 목록정보
     */
    List<JSONObject> selectBannerItemList(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 목록 건수 조회
     * @param bannerItemVO 검색조건
     * @return int 배너 아이템 목록 건수
     */
    int selectBannerItemListCount(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 상세 조회
     * @param bannerItemVO 검색조건
     * @return JSONObject 배너 아이템 상세정보
     */
    JSONObject selectBannerItemDetail(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 등록
     * @param bannerItemVO 배너 아이템 정보
     */
    void insertBannerItem(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    void updateBannerItem(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 순서/사용여부 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    void updateBannerItemOrder(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 삭제
     * @param bannerItemVO 배너 아이템 정보
     */
    void deleteBannerItem(BannerItemVO bannerItemVO);

    /**
     * 배너 아이템 일괄 삭제 (부모 배너 기준)
     * @param bannerVO 배너 정보
     */
    void deleteBannerItemByParent(BannerVO bannerVO);

    /**
     * 카테고리별 배너 존재 여부 확인
     * @param bannerItemVO 검색조건
     * @return int 배너 수
     */
    int selectBannerCountByCategory(BannerItemVO bannerItemVO);

    /**
     * 배너번호 기준 아이템 일괄 등록 (카테고리 복사용)
     * @param bannerItemVO 배너 아이템 정보
     */
    void insertBannerItemByBannerNo(BannerItemVO bannerItemVO);

    /**
     * 최대 G_SEQ 조회
     * @return int 최대 G_SEQ
     */
    int selectMaxGSeq();
}
