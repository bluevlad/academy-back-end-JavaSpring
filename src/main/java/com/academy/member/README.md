# Member Package

회원(User/Member) 관리 패키지입니다.

## 패키지 구조

```
member/
├── MemberApi.java           # REST API Controller
└── service/
    ├── MemberService.java   # Service class
    ├── MemberServiceImpl.java
    └── MemberVO.java        # Value Object
```

## 주요 컴포넌트

### MemberApi

회원 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/member`

**Endpoints**:

| Method | Endpoint | Description | Request | Response |
|--------|----------|-------------|---------|----------|
| GET | `/getMemberList` | 회원 목록 조회 (페이징) | MemberVO, curPage | memberList, paginationInfo |
| GET | `/getMemberDetail` | 회원 상세 조회 | MemberVO | memberDetail |
| GET | `/insertMember` | 회원 등록 | MemberVO | retMsg |
| GET | `/updateMember` | 회원 수정 | MemberVO | retMsg |
| GET | `/deleteMember` | 회원 삭제 (@Transactional) | MemberVO | retMsg |

### MemberService

회원 비즈니스 로직을 처리하는 서비스 클래스입니다.

**Methods**:

```java
// 조회
ArrayList<JSONObject> selectMemberList(MemberVO memberVO)
int selectMemberListTotCnt(MemberVO memberVO)
JSONObject selectMemberDetail(MemberVO memberVO)

// 등록/수정/삭제
void insertMember(MemberVO memberVO)
void updateMember(MemberVO memberVO)
void deleteMember(MemberVO memberVO)
```

**Architecture**:
- MemberMapper를 직접 주입하여 사용 (DAO 레이어 없음)
- Constructor-based dependency injection

### MemberVO

회원 데이터 전송을 위한 Value Object입니다.

**주요 속성**:
- 페이징 정보: `pageIndex`, `pageUnit`, `pageSize`, `firstIndex`, `lastIndex`, `recordCountPerPage`
- 회원 정보: 회원 ID, 이름, 이메일, 전화번호, 권한 등

## 사용 예시

### 1. 회원 목록 조회

```bash
GET /api/member/getMemberList?curPage=1
```

**Request Parameters**:
- `curPage`: 현재 페이지 번호 (기본값: 1)

**Response**:
```json
{
  "memberList": [
    {
      "userId": "user001",
      "userName": "홍길동",
      "email": "hong@example.com",
      "phone": "010-1234-5678",
      "regDate": "2024-01-01"
    }
  ],
  "paginationInfo": {
    "currentPageNo": 1,
    "totalRecordCount": 100,
    "recordCountPerPage": 10,
    "pageSize": 10,
    "totalPageCount": 10
  }
}
```

### 2. 회원 상세 조회

```bash
GET /api/member/getMemberDetail?userId=user001
```

**Response**:
```json
{
  "memberDetail": {
    "userId": "user001",
    "userName": "홍길동",
    "email": "hong@example.com",
    "phone": "010-1234-5678",
    "address": "서울시 강남구",
    "regDate": "2024-01-01",
    "updDate": "2024-01-15"
  }
}
```

### 3. 회원 등록

```bash
GET /api/member/insertMember?userId=user002&userName=김철수&email=kim@example.com
```

**Note**: GET 메서드를 사용하지만, POST 메서드로 변경하는 것이 RESTful 권장사항입니다.

**Response**:
```json
{
  "retMsg": "OK"
}
```

**Error Response**:
```json
{
  "retMsg": "FAIL"
}
```

### 4. 회원 수정

```bash
GET /api/member/updateMember?userId=user001&userName=홍길동수정&phone=010-9999-8888
```

**Response**:
```json
{
  "retMsg": "OK"
}
```

### 5. 회원 삭제

```bash
GET /api/member/deleteMember?userId=user001
```

**Response**:
```json
{
  "retMsg": "OK"
}
```

**트랜잭션**:
- `@Transactional(readOnly=false, rollbackFor=Exception.class)` 적용
- 예외 발생 시 자동 롤백

## 데이터베이스

### 관련 테이블

- `TB_MEMBER` - 회원 기본 정보

### Mapper

- **Interface**: `com.academy.mapper.MemberMapper`
- **XML**: `src/main/resources/mapper/MemberMapper.xml`

## 페이징 처리

회원 목록 조회 시 `PaginationInfo` 클래스를 사용하여 페이징 처리합니다:

```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
paginationInfo.setPageSize(memberVO.getPageSize());

memberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
memberVO.setLastIndex(paginationInfo.getLastRecordIndex());
memberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
```

## 에러 처리

모든 CUD(Create, Update, Delete) 작업은 try-catch 블록으로 감싸져 있으며, 예외 발생 시:

```java
try {
    memberService.insertMember(memberVO);
    jsonObject.put("retMsg", "OK");
} catch (Exception e) {
    jsonObject.put("retMsg", "FAIL");
    e.printStackTrace();
}
```

## CommonUtil 사용

`CommonUtil.empty()` 메서드를 사용하여 null 체크 및 기본값 설정:

```java
String curPage = "1";
if(!CommonUtil.empty(commandMap.get("curPage"))){
    curPage = (String)commandMap.get("curPage");
}
memberVO.setPageIndex(CommonUtil.parseInt(curPage));
```

## 주의사항

### RESTful API 개선 권장사항

현재 구현에서는 등록/수정/삭제에 GET 메서드를 사용하고 있으나, RESTful 표준에 따라 다음과 같이 변경하는 것이 권장됩니다:

- `insertMember`: GET → POST
- `updateMember`: GET → PUT
- `deleteMember`: GET → DELETE (이미 @Transactional 적용됨)

### 파라미터 전달

- 현재: Query String으로 전달 (`?userId=user001&userName=홍길동`)
- 권장: POST/PUT의 경우 Request Body로 전달 (JSON 형식)

## 확장 가능성

회원 관리 기능 확장 시 고려사항:
- 회원 등급 관리
- 회원 포인트 관리
- 회원 로그인 이력
- 회원 활동 이력
- 비밀번호 암호화
- 이메일/SMS 인증
- 소셜 로그인 연동

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.PaginationInfo` - 페이징 처리
- `com.academy.common.CommonUtil` - 공통 유틸리티
- `com.academy.mapper.MemberMapper` - 데이터 접근