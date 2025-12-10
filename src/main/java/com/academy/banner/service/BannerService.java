package com.academy.banner.service;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.BannerMapper;

/**
 * 배너 관리 Service 클래스
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
@Service
public class BannerService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BannerMapper bannerMapper;

    public BannerService(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    // =====================================================
    // 배너 마스터 (TB_BANNER) 관련
    // =====================================================

    /**
     * 배너 목록 조회
     * @param bannerVO 검색조건
     * @return List 배너 목록
     */
    public List<JSONObject> selectBannerList(BannerVO bannerVO) {
        return bannerMapper.selectBannerList(bannerVO);
    }

    /**
     * 배너 목록 건수 조회
     * @param bannerVO 검색조건
     * @return int 배너 목록 건수
     */
    public int selectBannerListCount(BannerVO bannerVO) {
        return bannerMapper.selectBannerListCount(bannerVO);
    }

    /**
     * 배너 상세 조회
     * @param bannerVO 검색조건
     * @return JSONObject 배너 상세정보
     */
    public JSONObject selectBannerDetail(BannerVO bannerVO) {
        return bannerMapper.selectBannerDetail(bannerVO);
    }

    /**
     * 배너 등록
     * @param bannerVO 배너정보
     */
    public void insertBanner(BannerVO bannerVO) {
        bannerMapper.insertBanner(bannerVO);
    }

    /**
     * 배너 수정
     * @param bannerVO 배너정보
     */
    public void updateBanner(BannerVO bannerVO) {
        bannerMapper.updateBanner(bannerVO);
    }

    /**
     * 배너 타입 일괄 변경
     * @param bannerVOList 배너정보 목록
     */
    public void updateBannerTypeList(List<BannerVO> bannerVOList) {
        if (bannerVOList != null && !bannerVOList.isEmpty()) {
            for (BannerVO bannerVO : bannerVOList) {
                bannerMapper.updateBannerType(bannerVO);
                // 배너 타입 변경 시 연결된 아이템 삭제
                bannerMapper.deleteBannerItemByParent(bannerVO);
            }
        }
    }

    /**
     * 배너 삭제 (연결된 아이템도 함께 삭제)
     * @param bannerVO 배너정보
     */
    public void deleteBanner(BannerVO bannerVO) {
        // 연결된 아이템 먼저 삭제
        bannerMapper.deleteBannerItemByParent(bannerVO);
        // 배너 삭제
        bannerMapper.deleteBanner(bannerVO);
    }

    // =====================================================
    // 배너 아이템 (TB_BANNER_ITEM) 관련
    // =====================================================

    /**
     * 배너 아이템 목록 조회
     * @param bannerItemVO 검색조건
     * @return List 배너 아이템 목록
     */
    public List<JSONObject> selectBannerItemList(BannerItemVO bannerItemVO) {
        return bannerMapper.selectBannerItemList(bannerItemVO);
    }

    /**
     * 배너 아이템 목록 건수 조회
     * @param bannerItemVO 검색조건
     * @return int 배너 아이템 목록 건수
     */
    public int selectBannerItemListCount(BannerItemVO bannerItemVO) {
        return bannerMapper.selectBannerItemListCount(bannerItemVO);
    }

    /**
     * 배너 아이템 상세 조회
     * @param bannerItemVO 검색조건
     * @return JSONObject 배너 아이템 상세정보
     */
    public JSONObject selectBannerItemDetail(BannerItemVO bannerItemVO) {
        return bannerMapper.selectBannerItemDetail(bannerItemVO);
    }

    /**
     * 배너 아이템 등록 (카테고리 일괄 등록 지원)
     * @param bannerItemVO 배너 아이템 정보
     */
    public void insertBannerItem(BannerItemVO bannerItemVO) {
        // G_SEQ 설정
        int gSeq = bannerMapper.selectMaxGSeq();
        bannerItemVO.setgSeq(String.valueOf(gSeq));

        // 서브페이지이고 카테고리 목록이 있는 경우 일괄 등록
        if ("S".equals(bannerItemVO.getScreenGubun()) &&
            bannerItemVO.getCategoryCds() != null && !bannerItemVO.getCategoryCds().isEmpty()) {

            String[] categoryCdArr = bannerItemVO.getCategoryCds().split("/");
            for (String categoryCd : categoryCdArr) {
                bannerItemVO.setCategoryCd(categoryCd);

                // 해당 카테고리에 배너가 존재하는지 확인
                int count = bannerMapper.selectBannerCountByCategory(bannerItemVO);
                if (count > 0) {
                    // 기존 배너에 아이템 추가
                    bannerMapper.insertBannerItemByBannerNo(bannerItemVO);
                } else {
                    // 일반 등록
                    bannerMapper.insertBannerItem(bannerItemVO);
                }
            }
        } else {
            // 일반 등록
            bannerMapper.insertBannerItem(bannerItemVO);
        }
    }

    /**
     * 배너 아이템 수정
     * @param bannerItemVO 배너 아이템 정보
     */
    public void updateBannerItem(BannerItemVO bannerItemVO) {
        bannerMapper.updateBannerItem(bannerItemVO);
    }

    /**
     * 배너 아이템 순서/사용여부 일괄 수정
     * @param bannerItemVOList 배너 아이템 정보 목록
     */
    public void updateBannerItemOrderList(List<BannerItemVO> bannerItemVOList) {
        if (bannerItemVOList != null && !bannerItemVOList.isEmpty()) {
            for (BannerItemVO bannerItemVO : bannerItemVOList) {
                bannerMapper.updateBannerItemOrder(bannerItemVO);
            }
        }
    }

    /**
     * 배너 아이템 삭제
     * @param bannerItemVO 배너 아이템 정보
     */
    public void deleteBannerItem(BannerItemVO bannerItemVO) {
        bannerMapper.deleteBannerItem(bannerItemVO);
    }

    /**
     * 배너 아이템 일괄 삭제
     * @param bannerItemVOList 배너 아이템 정보 목록
     */
    public void deleteBannerItemList(List<BannerItemVO> bannerItemVOList) {
        if (bannerItemVOList != null && !bannerItemVOList.isEmpty()) {
            for (BannerItemVO bannerItemVO : bannerItemVOList) {
                bannerMapper.deleteBannerItem(bannerItemVO);
            }
        }
    }
}
