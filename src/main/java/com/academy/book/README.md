# Book Package

교재(Textbook/Course Materials) 관리 패키지입니다.

## 패키지 구조

```
book/
├── BookApi.java           # REST API Controller
└── service/
    ├── BookService.java   # Service class
    └── BookServiceImpl.java
```

## 주요 컴포넌트

### BookApi

교재 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/book`

**Endpoints**:

| Method | Endpoint | Description | Transaction |
|--------|----------|-------------|-------------|
| GET | `/list` | 교재 목록 조회 (페이징) | - |
| GET | `/view` | 교재 상세 조회 | - |
| GET | `/writeData` | 교재 등록을 위한 기본 데이터 조회 | - |
| POST | `/save` | 교재 등록 | @Transactional |
| PUT | `/update` | 교재 수정 | @Transactional |
| DELETE | `/delete` | 교재 단일 삭제 | @Transactional |
| DELETE | `/deleteAll` | 교재 관련도서 전체 삭제 | @Transactional |
| GET | `/sellList` | 교재 판매 목록 조회 | - |
| GET | `/sellListExcel` | 교재 판매 목록 엑셀 데이터 | - |

### BookService

교재 비즈니스 로직을 처리하는 서비스 클래스입니다.

**Key Methods**:

```java
// 조회
List<HashMap<String, String>> bookList(HashMap<String, String> params)
int bookListCount(HashMap<String, String> params)
List<HashMap<String, String>> bookView(HashMap<String, String> params)
List<HashMap<String, String>> bookViewList(HashMap<String, String> params)

// 등록/수정/삭제
void bookInsert(HashMap<String, String> params)
void bookUpdate(HashMap<String, String> params)
void bookDelete(HashMap<String, String> params)

// 시퀀스 및 ID 생성
int getCaBookSeq(HashMap<String, String> params)
String getCaBookRscId(HashMap<String, String> params)

// 판매 관련
List<HashMap<String, String>> bookSellList(HashMap<String, String> params)
int bookSellListCount(HashMap<String, String> params)
List<HashMap<String, String>> bookSellListExcel(HashMap<String, String> params)

// 사용 체크
int bookUseCheck(HashMap<String, String> params)

// 기타 참조 데이터
List<HashMap<String, String>> getLearningFormList(HashMap<String, String> params)
List<HashMap<String, String>> getCaSubjectTeacherList(HashMap<String, String> params)
```

## 주요 기능

### 1. 교재 목록 조회

```bash
GET /api/book/list?currentPage=1&pageRow=10&SEARCHKIND=&SEARCHTYPE=&SEARCHTEXT=
```

**Response**:
```json
{
  "kindlist": [...],
  "list": [
    {
      "SEQ": "1",
      "RSC_ID": "T202400001",
      "BOOK_NM": "교재명",
      "PRICE": "30000",
      "DISCOUNT_PRICE": "27000"
    }
  ],
  "totalCount": 50,
  "totalPage": 5,
  "currentPage": 1
}
```

### 2. 교재 상세 조회

```bash
GET /api/book/view?SEQ=1&RSC_ID=T202400001
```

**Response**:
```json
{
  "kindlist": [...],
  "formlist": [...],
  "subjectteacherlist": [...],
  "view": [{...}],
  "viewlist": [{...}],
  "rdelyn": "Y",  // 현재 선택글 삭제가능여부
  "sdelyn": "Y"   // 관련글 삭제가능여부
}
```

### 3. 교재 등록

복수의 카테고리, 강의형태에 대해 일괄 등록 가능합니다.

```bash
POST /api/book/save
Content-Type: application/json

{
  "BOOK_NM": "새 교재",
  "BOOK_INFO": "교재 설명",
  "PRICE": "30000",
  "DISCOUNT_PRICE": "27000",
  "SUBJECT_SJT_CD": ["SJT001", "SJT002"],
  "CATEGORY_CD": ["CAT001", "CAT002"],
  "LEARNING_CD": ["LRN001", "LRN002"]
}
```

**처리 로직**:
1. 교재 시퀀스(SEQ) 생성
2. 카테고리 배열 순회
3. 강의형태 배열 순회
4. 각 조합별로 교재 리소스 ID(RSC_ID) 생성
5. 교재 등록

**Response**:
```json
{
  "result": "success",
  "message": "교재가 등록되었습니다"
}
```

### 4. 교재 수정

```bash
PUT /api/book/update
Content-Type: application/json

{
  "RSC_ID": "T202400001",
  "BOOK_NM": "수정된 교재명",
  "PRICE": "35000",
  "UPDATE_FLAG": "Y",
  "SUBJECT_SJT_CD": ["SJT001", "SJT002"]
}
```

### 5. 교재 삭제

**단일 삭제**:
```bash
DELETE /api/book/delete
Content-Type: application/json

{
  "RSC_ID": "T202400001"
}
```

**관련 도서 전체 삭제**:
```bash
DELETE /api/book/deleteAll
Content-Type: application/json

{
  "SEQ": "1"
}
```

### 6. 교재 판매 목록

```bash
GET /api/book/sellList?currentPage=1&pageRow=10
```

## 주요 파라미터

### setParam 메서드에서 처리하는 파라미터

```java
// 페이징
currentPage, pageRow

// 검색
SEARCHKIND, SEARCHTYPE, SEARCHTEXT

// 교재 기본 정보
SEQ, RSC_ID, CATEGORY_CD, LEARNING_CD
BOOK_NM, BOOK_INFO, BOOK_MEMO, BOOK_KEYWORD

// 가격 정보
PRICE, DISCOUNT, DISCOUNT_PRICE, POINT

// 출판 정보
BOOK_PUBLISHERS, BOOK_AUTHOR, ISSUE_DATE
BOOK_PRINTINGDATE, BOOK_PAGE, BOOK_FORMAT
COVER_TYPE

// 교재 상세 정보
BOOK_CONTENTS, BOOK_SUPPLEMENTDATA
BOOK_MAIN, BOOK_SUB, BOOK_STUDENTBOOK

// 재고 및 배송
BOOK_STOCK, FREE_POST

// 상태 플래그
NEW_BOOK, MAIN_VIEW, USE_YN, BOOK_DATE
```

## 데이터베이스

### 관련 테이블

- `TB_BOOK` - 교재 기본 정보
- `TB_BOOK_CATEGORY` - 교재 카테고리 연결
- `TB_SUBJECT` - 과목 정보
- `TB_TEACHER` - 강사 정보

### Mapper

- **Interface**: `com.academy.mapper.BookMapper`
- **XML**: `src/main/resources/mapper/BookMapper.xml`

## 트랜잭션 처리

교재의 등록/수정/삭제 작업은 `@Transactional` 어노테이션으로 보호됩니다:
- `rollbackFor=Exception.class` 설정으로 모든 예외 발생 시 롤백

## 삭제 가능 여부 체크

교재 상세 조회 시 삭제 가능 여부를 확인합니다:

```java
int checkCnt = bookService.bookUseCheck(params);
if(checkCnt > 0)
    rdelyn = "N";  // 사용 중인 교재는 삭제 불가
```

## 교재 ID 생성 규칙

교재 리소스 ID는 다음 규칙으로 생성됩니다:
- 형식: `T + 년도 + 시퀀스`
- 예시: `T202400001`, `T202400002`

## 세션 처리

모든 요청에서 세션의 사용자 정보를 추출하여 등록자/수정자 ID로 설정합니다:

```java
HashMap<String, String> loginInfo = (HashMap<String, String>)session.getAttribute("AdmUserInfo");
params.put("REG_ID", loginInfo.get("USER_ID"));
params.put("UPD_ID", loginInfo.get("USER_ID"));
```

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.CommonUtil` - 공통 유틸리티
- `com.academy.mapper.BookMapper` - 데이터 접근
- `com.academy.lecture.service.TeacherService` - 강사 정보 조회

## 참고사항

1. **복수 등록**: 교재 등록 시 카테고리와 강의형태의 모든 조합에 대해 교재를 생성
2. **엑셀 다운로드**: 판매 목록을 엑셀로 다운로드 가능
3. **삭제 제한**: 사용 중인 교재는 삭제 불가 (사용 체크 수행)
4. **가격 관리**: 정가, 할인율, 할인가, 포인트 관리
5. **재고 관리**: 재고 수량 및 무료배송 여부 관리