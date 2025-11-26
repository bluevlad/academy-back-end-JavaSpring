구현 완료 내용

1. MenuVO.java

- 참조 XML의 테이블 구조에 맞춰 필드 추가:
    - 기본 필드: onoffDiv, menuId, menuNm, menuSeq, menuUrl, pMenuid, isuse,
      target, menuInfo
    - Pass 메뉴 추가 필드: code, topImgUrl, leftImgUrl, titlImgUrl,
      subtitlImgUrl
    - 등록/수정 정보: regId, regDt, updId, updDt

2. MenuMapper.java

- CRUD 메서드 인터페이스 추가:
    - 메뉴 트리 조회: getMenuTree(), getpassMenuTree()
    - 메뉴 상세 조회: getDetailMenu(), getpassDetailMenu()
    - 메뉴 등록: menuInsertProcess(), passmenuInsertProcess()
    - 메뉴 수정: menuUpdateProcess(), passmenuUpdateProcess()
    - 메뉴 삭제: menuDeleteProcess(), passmenuDeleteProcess()
    - 유틸리티: menuIdCheck(), getMaxMenuId(), getpassMaxMenuId()

3. menu.xml (MyBatis 매퍼)

- 참조 XML의 SQL 쿼리를 모두 포함:
    - TB_SG_MENU_MST (일반 메뉴) 및 TB_SG_MENU_MST2 (Pass 메뉴) 대상 쿼리
    - SELECT, INSERT, UPDATE, DELETE 모든 기능 구현

4. MenuService.java

- Mapper의 모든 메서드를 호출하는 서비스 레이어 구현

5. MenuApi.java

- RESTful API 엔드포인트 추가:
    - GET: /getMenuTree, /getpassMenuTree, /getDetailMenu, /getpassDetailMenu,
      /menuIdCheck, /getMaxMenuId, /getpassMaxMenuId
    - POST: /menuInsertProcess, /passmenuInsertProcess
    - PUT: /menuUpdateProcess, /passmenuUpdateProcess
    - DELETE: /menuDeleteProcess, /passmenuDeleteProcess