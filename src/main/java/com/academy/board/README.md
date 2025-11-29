# Board Package

게시판(Bulletin Board) 시스템을 관리하는 패키지입니다.

## 패키지 구조

```
board/
├── BoardApi.java           # REST API Controller
└── service/
    ├── BoardService.java   # Service class
    └── BoardVO.java        # Value Object
```

## 주요 컴포넌트

### BoardApi

게시판 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/board`

**Endpoints**:

| Method | Endpoint | Description | Request | Response |
|--------|----------|-------------|---------|----------|
| GET | `/getBoardList` | 게시판 목록 조회 (페이징) | BoardVO | boardList, paginationInfo |
| GET | `/getBoardDetail` | 게시판 상세 조회 | BoardVO | boardDetail |
| POST | `/insertBoard` | 게시판 등록 | BoardVO | retMsg |
| POST | `/updateBoard` | 게시판 수정 | BoardVO | retMsg |
| POST | `/deleteBoard` | 게시판 삭제 | BoardVO | retMsg |

### BoardService

게시판 비즈니스 로직을 처리하는 서비스 클래스입니다.

**Methods**:

```java
// 조회
ArrayList<JSONObject> selectBoardList(BoardVO boardVO)
int selectBoardListTotCnt(BoardVO boardVO)
JSONObject getBoardDetail(BoardVO boardVO)

// 등록/수정/삭제
void insertBoard(BoardVO boardVO)
void updateBoard(BoardVO boardVO)
void deleteBoard(BoardVO boardVO)
```

**Architecture**:
- BoardMapper를 직접 주입하여 사용 (DAO 레이어 없음)
- Constructor-based dependency injection

### BoardVO

게시판 데이터 전송을 위한 Value Object입니다.

**주요 속성**:
- 페이징 정보: `pageIndex`, `pageUnit`, `pageSize`, `firstIndex`, `lastIndex`
- 게시판 정보: 게시글 ID, 제목, 내용, 작성자 등

## 사용 예시

### 1. 게시판 목록 조회

```bash
GET /api/board/getBoardList?pageIndex=1&pageUnit=10&pageSize=10
```

**Response**:
```json
{
  "boardList": [
    {
      "boardId": "1",
      "title": "공지사항",
      "content": "내용...",
      "regDate": "2024-01-01"
    }
  ],
  "paginationInfo": {
    "currentPageNo": 1,
    "totalRecordCount": 100,
    "recordCountPerPage": 10,
    "pageSize": 10
  }
}
```

### 2. 게시판 상세 조회

```bash
GET /api/board/getBoardDetail?boardId=1
```

**Response**:
```json
{
  "boardDetail": {
    "boardId": "1",
    "title": "공지사항",
    "content": "내용...",
    "regDate": "2024-01-01"
  }
}
```

### 3. 게시판 등록

```bash
POST /api/board/insertBoard
Content-Type: application/x-www-form-urlencoded

title=새글제목&content=내용
```

**Response**:
```json
{
  "retMsg": "OK"
}
```

### 4. 게시판 수정

```bash
POST /api/board/updateBoard
Content-Type: application/x-www-form-urlencoded

boardId=1&title=수정된제목&content=수정된내용
```

**Response**:
```json
{
  "retMsg": "OK"
}
```

### 5. 게시판 삭제

```bash
POST /api/board/deleteBoard
Content-Type: application/x-www-form-urlencoded

boardId=1
```

**Response**:
```json
{
  "retMsg": "OK"
}
```

## 데이터베이스

### 관련 테이블

- `TB_BOARD` - 게시판 정보

### Mapper

- **Interface**: `com.academy.mapper.BoardMapper`
- **XML**: `src/main/resources/mapper/BoardMapper.xml`

## 페이징 처리

게시판 목록 조회 시 `PaginationInfo` 클래스를 사용하여 페이징 처리합니다:

1. 요청 파라미터에서 페이징 정보 추출
2. `PaginationInfo` 객체 생성 및 설정
3. `firstIndex`, `lastIndex` 계산
4. Mapper에 전달하여 LIMIT 쿼리 실행
5. 전체 레코드 수 조회 및 페이징 정보 반환

## 에러 처리

모든 CUD(Create, Update, Delete) 작업은 try-catch 블록으로 감싸져 있으며, 예외 발생 시:
- `retMsg`: "FAIL" 반환
- 콘솔에 스택 트레이스 출력

성공 시:
- `retMsg`: "OK" 반환

## 확장 가능성

게시판 기능 확장 시 고려사항:
- 파일 첨부 기능
- 댓글 기능
- 조회수 증가
- 검색 기능 강화
- 카테고리 분류
- 권한 관리

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.PaginationInfo` - 페이징 처리
- `com.academy.mapper.BoardMapper` - 데이터 접근