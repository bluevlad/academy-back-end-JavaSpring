# BookCmmt Package

교재 댓글(Book Comment) 관리 패키지입니다.

## 패키지 구조

```
bookCmmt/
├── BookCmmtController.java    # Controller (API 아님)
└── service/
    ├── BookCmmtService.java   # Service class
    └── BookCmmtServiceImpl.java
```

## 주요 컴포넌트

### BookCmmtController

교재 댓글 관리를 위한 컨트롤러입니다.

**Note**: RestController가 아닌 일반 Controller를 사용합니다.

### BookCmmtService

교재 댓글 비즈니스 로직을 처리하는 서비스 클래스입니다.

**주요 기능**:
- 교재 댓글 목록 조회
- 교재 댓글 등록
- 교재 댓글 수정
- 교재 댓글 삭제

## 데이터베이스

### 관련 테이블

- `TB_BOOK_COMMENT` - 교재 댓글 정보

### Mapper

- **Interface**: `com.academy.mapper.BookCmmtMapper`
- **XML**: `src/main/resources/mapper/BookCmmtMapper.xml`

## 의존성

- `com.academy.mapper.BookCmmtMapper` - 데이터 접근

## 참고사항

이 패키지는 REST API가 아닌 일반 MVC 패턴을 사용합니다.