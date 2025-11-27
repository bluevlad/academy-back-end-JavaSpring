# Lecture Module API Documentation

## 개요

이 모듈은 온라인 교육 플랫폼의 강의 관리 시스템을 위한 RESTful API를 제공합니다.

## 아키텍처

- **프레임워크**: Spring Boot 3.2.0
- **Java**: 17
- **Servlet API**: Jakarta EE (jakarta.servlet.*)
- **응답 형식**: JSON (org.json.simple.JSONObject)
- **인증**: HttpSession 기반
- **파일 업로드**: MultipartHttpServletRequest
- **트랜잭션**: Spring @Transactional
- **CORS**: CORSFilter 상속
- **Property 관리**: Spring @Value 애노테이션

## API 목록

### 1. CategoryApi - 카테고리 관리

**Base URL**: `/api/category`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/tree` | 카테고리 트리 조회 |
| GET | `/detail` | 카테고리 상세 조회 |
| GET | `/maxOrdr` | 최대 순서 조회 |
| GET | `/idCheck` | ID 중복 확인 |
| POST | `/insert` | 카테고리 등록 |
| PUT | `/update` | 카테고리 수정 |
| DELETE | `/delete` | 카테고리 삭제 |

**주요 기능**:
- 계층 구조 카테고리 관리
- 순서 자동 관리
- ID 중복 체크

---

### 2. TeacherApi - 강사 관리

**Base URL**: `/api/teacher`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 강사 목록 조회 |
| GET | `/view` | 강사 상세 조회 |
| GET | `/idCheck` | 강사 ID 중복 확인 |
| GET | `/find` | 강사 검색 |
| POST | `/save` | 강사 등록 |
| PUT | `/update` | 강사 수정 |
| PUT | `/seqUpdate` | 강사 순번 수정 |
| DELETE | `/delete` | 강사 삭제 |
| DELETE | `/listDelete` | 강사 다중 삭제 |

**주요 기능**:
- 강사 정보 관리 (프로필, 경력, 과목 등)
- 이미지 파일 업로드 (최대 32개)
- 카테고리별 강사 분류
- 온라인/오프라인 강의 구분

**파일 업로드 필드**:
- `PIC1~PIC10`: 일반 이미지
- `OFF_PIC1~OFF_PIC10`: 오프라인 이미지
- `PRF_ONPIC1~PRF_ONPIC3`: 온라인 프로필 이미지
- `PRF_OFFPIC1~PRF_OFFPIC3`: 오프라인 프로필 이미지
- `PRF_LISTONBANNER`, `PRF_LISTOFFBANNER`: 배너 이미지
- `PROF_IMG`, `OFF_PROF_IMG`: 강사 이미지
- `PRF_TOPONIMG`, `PRF_TOPOFFIMG`: 상단 이미지

---

### 3. SubjectApi - 과목 관리

**Base URL**: `/api/subject`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 과목 목록 조회 |
| GET | `/view` | 과목 상세 조회 |
| GET | `/codeCheck` | 과목 코드 중복 확인 |
| POST | `/save` | 과목 등록 |
| PUT | `/update` | 과목 수정 |
| DELETE | `/delete` | 과목 삭제 |
| DELETE | `/listDelete` | 과목 다중 삭제 |

**주요 기능**:
- 과목 CRUD
- 카테고리 연결
- 과목명 중복 확인

---

### 4. FormApi - 학습형태 관리

**Base URL**: `/api/form`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 학습형태 목록 조회 |
| GET | `/view` | 학습형태 상세 조회 |
| GET | `/codeCheck` | 코드 중복 확인 |
| POST | `/save` | 학습형태 등록 |
| PUT | `/update` | 학습형태 수정 |
| DELETE | `/delete` | 학습형태 삭제 |
| DELETE | `/listDelete` | 학습형태 다중 삭제 |

**주요 기능**:
- 학습형태 CRUD (온라인, 오프라인, 혼합 등)
- 코드 관리

---

### 5. KindApi - 직종 관리

**Base URL**: `/api/kind`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 직종 목록 조회 |
| GET | `/view` | 직종 상세 조회 |
| GET | `/seqUpdateList` | 순서 업데이트용 전체 목록 |
| GET | `/codeCheck` | 코드 중복 확인 |
| POST | `/save` | 직종 등록 |
| PUT | `/update` | 직종 수정 |
| PUT | `/seqUpdate` | 직종 순번 수정 |
| DELETE | `/delete` | 직종 삭제 |
| DELETE | `/listDelete` | 직종 다중 삭제 |

**주요 기능**:
- 직종 카테고리 관리
- 순서 관리

---

### 6. SeriesApi - 직렬 관리

**Base URL**: `/api/series`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 직렬 목록 조회 |
| GET | `/view` | 직렬 상세 조회 |
| GET | `/kindCodes` | Kind 코드 목록 조회 |
| GET | `/codeCheck` | 코드 중복 확인 |
| POST | `/save` | 직렬 등록 |
| PUT | `/update` | 직렬 수정 |
| DELETE | `/delete` | 직렬 삭제 |
| DELETE | `/listDelete` | 직렬 다중 삭제 |

**주요 기능**:
- 직렬 관리
- Kind와 연결

---

### 7. LectureApi - 강의 관리

**Base URL**: `/api/lecture`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 강의 목록 조회 |
| GET | `/view` | 강의 상세 조회 |
| GET | `/bookList` | 교재 목록 조회 |
| GET | `/bookView` | 교재 상세 조회 |
| GET | `/couponList` | 쿠폰 목록 조회 |
| GET | `/moCouponList` | 모바일 쿠폰 목록 조회 |
| POST | `/save` | 강의 등록 |
| PUT | `/update` | 강의 수정 |
| PUT | `/onOffStatus` | 강의 개설여부 수정 |
| DELETE | `/delete` | 강의 삭제 |
| DELETE | `/listDelete` | 강의 다중 삭제 |

**주요 기능**:
- 단과 강의 CRUD
- 브릿지 테이블 관리 (TB_LEC_BRIDGE)
- 교재 연결 (주교재, 부교재, 수강생교재)
- 강의 코드 자동 생성 (예: D202400001, N202400001)
- 강의 개설여부 관리

**코드 생성 규칙**:
- PREFIX: LEC_TYPE_CHOICE + YEAR (예: D2024, N2024)
- LECCODE: PREFIX + 5자리 순번
- BRIDGE_LECCODE: R + YEAR + 5자리 순번

---

### 8. LectureMstApi - 강의 마스터 관리

**Base URL**: `/api/lecturemst`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 강의 마스터 목록 조회 |
| GET | `/view` | 강의 마스터 상세 조회 |
| GET | `/subjectTeacherList` | 과목(강사) 검색 목록 |
| GET | `/dataViewList` | 데이터 뷰 리스트 조회 |
| POST | `/save` | 강의 마스터 등록 |
| PUT | `/update` | 강의 마스터 수정 |
| DELETE | `/delete` | 강의 마스터 삭제 |

**주요 기능**:
- 강의 마스터 정보 관리
- 교재 관리
- 브릿지 테이블 관리
- 코드 자동 생성 (MSTCODE: M + YEAR + 5자리)

---

### 9. MacAddressManagerApi - MAC 주소 관리

**Base URL**: `/api/macaddressmanager`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | MAC 주소 관리 목록 조회 |
| GET | `/view` | MAC 주소 상세 조회 |
| GET | `/deviceList` | 디바이스 목록 조회 |
| PUT | `/update` | MAC 주소 업데이트 |
| PUT | `/resetMobile` | 모바일 초기화 |

**주요 기능**:
- MAC 주소 관리
- 디바이스 관리
- 모바일 디바이스 초기화

---

### 10. OpenLectureApi - 공개강의 관리

**Base URL**: `/api/openlecture`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 공개강의 목록 조회 |
| GET | `/view` | 공개강의 상세 조회 |
| GET | `/writeData` | 등록을 위한 기본 데이터 조회 |
| GET | `/bookList` | 교재 목록 조회 |
| POST | `/save` | 공개강의 등록 |
| PUT | `/update` | 공개강의 수정 |
| DELETE | `/delete` | 공개강의 삭제 |

**주요 기능**:
- 공개강의 CRUD
- 파일 업로드 (openlecture_upload/)
- 코드 자동 생성 (OPENLECCODE: O + YEAR + 5자리)
- 아이콘 구분 코드 관리

**파일 업로드**:
- `OPEN_FILE`: 강의 첨부 파일

---

### 11. ProductEventApi - 강의 이벤트 관리

**Base URL**: `/api/productevent`

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/list` | 강의 이벤트 목록 조회 |
| GET | `/view` | 강의 이벤트 상세 조회 |
| GET | `/subjectList` | 이벤트에 추가할 강의 목록 |
| POST | `/insert` | 강의 이벤트 등록 |
| POST | `/addLecture` | 이벤트에 강의 추가 |
| PUT | `/update` | 강의 이벤트 수정 |
| DELETE | `/deleteLecture` | 이벤트에서 강의 삭제 |

**주요 기능**:
- 강의 이벤트 CRUD
- 이벤트에 강의 연결
- 다중 강의 추가/삭제
- 카테고리, 과목, 학습형태별 강의 검색

---

## 공통 기능

### 페이징

모든 목록 조회 API는 페이징을 지원합니다.

**요청 파라미터**:
- `currentPage`: 현재 페이지 (기본값: 1)
- `pageRow`: 페이지당 행 수 (기본값: application.properties의 pageUnit 설정값, 기본값 10)

**응답 구조**:
```json
{
  "list": [...],
  "totalCount": 100,
  "totalPage": 10,
  "currentPage": 1
}
```

### 인증

모든 API는 HttpSession 기반 인증을 사용합니다.

**세션 정보**:
- `AdmUserInfo.USER_ID`: 사용자 ID
- 자동으로 `REG_ID`, `UPD_ID`에 설정됨

### 응답 형식

**성공 응답**:
```json
{
  "result": "success",
  "message": "작업이 완료되었습니다"
}
```

**에러 응답**:
트랜잭션 롤백 처리

### CORS

모든 API는 `CORSFilter`를 상속하여 CORS를 지원합니다.

---

## 데이터베이스 구조

### 주요 테이블

- `TB_LEC_MST`: 강의 마스터
- `TB_LEC_BRIDGE`: 강의 브릿지 (강의 연결 정보)
- `TB_PLUS_CA_BOOK`: 교재 정보
- `TB_OPEN_LEC_MST`: 공개강의
- `TB_CATEGORY`: 카테고리
- `TB_SUBJECT`: 과목
- `TB_TEACHER`: 강사

### Mapper XML

- `src/main/resources/mapper/lecture*.xml`
- MyBatis Mapper를 사용한 SQL 관리

---

## 파일 업로드 처리

### 업로드 경로

- 강사: `member_upload/`
- 공개강의: `openlecture_upload/`

### 파일 처리 방식

1. `MultipartHttpServletRequest`로 파일 수신
2. `FileUtil.uploadFile()`로 파일 저장
3. 파일 경로를 DB에 저장
4. 수정 시 기존 파일 삭제 처리

---

## 개발 가이드

### API 추가 방법

1. Controller 클래스 생성 (xxxApi.java)
2. `@RestController` 어노테이션 추가
3. `@RequestMapping("/api/xxx")` 설정
4. `CORSFilter` 상속
5. Service 주입 (생성자 방식)
6. 메서드에 적절한 HTTP 메서드 매핑 (`@GetMapping`, `@PostMapping` 등)
7. JSONObject로 응답 반환

### 예제

```java
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private ExampleService exampleService;

    @Autowired
    public ExampleApi(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/list")
    public JSONObject list(@ModelAttribute HashMap<String, String> params,
                          HttpServletRequest request) throws Exception {
        setParam(params, request);

        List<HashMap<String, String>> list = exampleService.getList(params);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);

        JSONObject jObject = new JSONObject(result);
        return jObject;
    }
}
```

---

## 테스트

### API 테스트 도구

- Postman
- cURL
- Swagger (추후 추가 예정)

### 테스트 예제

```bash
# 강의 목록 조회
curl -X GET "http://localhost:8080/api/lecture/list?currentPage=1&pageRow=10" \
  -H "Cookie: JSESSIONID=xxx"

# 강의 등록
curl -X POST "http://localhost:8080/api/lecture/save" \
  -H "Content-Type: application/json" \
  -H "Cookie: JSESSIONID=xxx" \
  -d '{"SUBJECT_TITLE":"강의명","CATEGORY_CD":["001"]}'
```

---

## 버전 히스토리

- v1.1 (2024): Spring Boot 3.x 마이그레이션
  - Spring Boot 3.2.0으로 업그레이드
  - javax.* → jakarta.* 패키지 변경
  - EgovPropertyService → @Value 애노테이션으로 변경
  - Apache Commons Lang3 의존성 추가

- v1.0 (2024): 초기 API 구현
  - CategoryApi, TeacherApi, SubjectApi 등 11개 API 구현
  - Controller → Api 전환
  - RESTful API 구조 적용

---

## 참고사항

### 기존 Controller와의 차이점

| 항목 | Controller | Api |
|------|-----------|-----|
| 어노테이션 | @Controller | @RestController |
| 반환 타입 | String (View) | JSONObject |
| URL 패턴 | /xxx/yyy.do | /api/xxx/yyy |
| CORS | 미지원 | 지원 (CORSFilter) |
| HTTP 메서드 | @RequestMapping | @GetMapping, @PostMapping 등 |

### 마이그레이션 가이드

기존 JSP 기반 화면에서 API를 호출하는 방식으로 전환:

1. 기존 form submit → AJAX 호출
2. redirect → API 응답 처리 후 페이지 이동
3. ModelMap → JSON 응답 처리

---

## 문의

API 관련 문의사항은 개발팀으로 연락해주세요.