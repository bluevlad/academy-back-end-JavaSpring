# Common (공통) 패키지

애플리케이션 전체에서 사용되는 공통 컴포넌트 및 유틸리티를 제공합니다.

## 패키지 구조

```
common/
├── ApiInfoApi.java              # API 정보 조회 REST 컨트롤러
├── ApiLoggingInterceptor.java   # API 요청/응답 로깅 인터셉터
├── CORSFilter.java              # CORS 필터 베이스 클래스
├── ComDefaultCodeVO.java        # 기본 코드 Value Object
├── ComDefaultVO.java            # 기본 Value Object
├── CommonUtil.java              # 공통 유틸리티
├── CommonVO.java                # 공통 Value Object 베이스 클래스
├── Configurations.java          # 애플리케이션 설정
├── DBUtil.java                  # 데이터베이스 유틸리티
├── EgovWebUtil.java             # 전자정부 웹 유틸리티
├── FileMngUtil.java             # 파일 관리 유틸리티
├── FileVO.java                  # 파일 Value Object
├── JwtUtil.java                 # JWT 토큰 유틸리티
├── MirProperties.java           # 프로퍼티 관리
├── PaginationInfo.java          # 페이징 정보
├── file/
│   ├── FileUtil.java                        # 파일 처리 유틸리티
│   ├── service/
│   │   ├── MultipartFileService.java        # 멀티파트 파일 서비스 인터페이스
│   │   └── impl/
│   │       ├── MultipartFileDAO.java        # 멀티파트 파일 DAO
│   │       └── MultipartFileServiceImpl.java # 멀티파트 파일 서비스 구현체
│   ├── view/
│   │   └── FileDownloadView.java            # 파일 다운로드 뷰
│   └── web/
│       └── FileDownloadController.java      # 파일 다운로드 컨트롤러
└── service/
    ├── CmmUseService.java               # 공통 사용 서비스 (@Service)
    └── CmmnDetailCode.java              # 공통 상세 코드 VO
```

## 주요 기능

### ApiInfoApi
- **GET** `/api/info/getApiList` - 모든 API 엔드포인트 목록 조회
- **GET** `/api/info/getApiSummary` - 모듈별 API 요약 정보 조회
- 런타임에 등록된 모든 REST API 목록 제공
- 모듈별 API 개수 통계 제공

### ApiLoggingInterceptor
- 모든 API 요청에 대한 로깅 처리
- 요청 URL, HTTP 메서드, 파라미터, 헤더 정보 기록
- 민감한 정보(비밀번호 등) 마스킹 처리
- 일별 로그 파일 생성 (`api_log_YYYY-MM-DD.log`)
- 클라이언트 IP 주소 추출 (프록시 헤더 지원)
- OPTIONS 요청 제외

### CORSFilter
- CORS(Cross-Origin Resource Sharing) 설정
- 모든 API 컨트롤러의 베이스 클래스
- 크로스 도메인 요청 처리

### CommonVO
- 모든 VO(Value Object)의 베이스 클래스
- 페이징 관련 공통 필드 제공
  - currentPage, pageRow, startNo, endNo
  - searchType, searchText, searchKind
- 등록/수정 정보 공통 필드
  - regId, regDt, updId, updDt

### CommonUtil
- Null 체크 및 기본값 처리
- 문자열 처리 유틸리티
- 데이터 변환 유틸리티

### FileUtil & FileMngUtil
- 파일 업로드 처리
- 파일 다운로드 처리
- 파일 삭제 처리
- 파일명 생성 및 검증

### Configurations
- CORS 설정
- 애플리케이션 전역 설정
- Bean 등록 및 관리

## 사용 예시

```java
// CORSFilter 상속
@RestController
@RequestMapping("/api/book")
public class BookApi extends CORSFilter {
    // API 메소드 구현
}

// CommonVO 상속
public class BookVO extends CommonVO implements Serializable {
    private String rscId;
    private String rscNm;
    // 추가 필드
}

// CommonUtil 사용
String value = CommonUtil.isNull(request.getParameter("param"), "defaultValue");

// FileUtil 사용
fileUtil.uploadFile(multipartFile, uploadPath);
```

## 참고사항

- 모든 VO는 CommonVO를 상속해야 합니다
- 모든 API 컨트롤러는 CORSFilter를 상속해야 합니다
- 파일 업로드 경로는 application.properties에서 설정합니다
- CORS 설정은 Configurations에서 관리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
