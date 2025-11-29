# BookOrder Package

교재 주문(Book Order) 관리 패키지입니다.

## 패키지 구조

```
bookOrder/
├── BookOrderController.java    # Controller (API 아님)
└── service/
    ├── BookOrderService.java   # Service class
    └── BookOrderServiceImpl.java
```

## 주요 컴포넌트

### BookOrderController

교재 주문 관리를 위한 컨트롤러입니다.

**Note**: RestController가 아닌 일반 Controller를 사용합니다.

### BookOrderService

교재 주문 비즈니스 로직을 처리하는 서비스 클래스입니다.

**주요 기능**:
- 교재 주문 목록 조회
- 교재 주문 상세 조회
- 교재 주문 등록
- 교재 주문 수정
- 교재 주문 취소/환불

## 데이터베이스

### 관련 테이블

- `TB_BOOK_ORDER` - 교재 주문 정보
- `TB_BOOK_ORDER_ITEM` - 교재 주문 상세 정보
- `TB_BOOK_ORDER_PAY` - 교재 주문 결제 정보

### Mapper

- **Interface**: `com.academy.mapper.BookOrderMapper`
- **XML**: `src/main/resources/mapper/BookOrderMapper.xml`

## 주요 기능

### 주문 프로세스

1. 주문 정보 생성
2. 주문 상세 정보 등록
3. 결제 정보 등록
4. 배송 정보 등록

### 주문 상태 관리

- 주문 접수
- 결제 완료
- 배송 준비
- 배송 중
- 배송 완료
- 주문 취소
- 환불 처리

## 의존성

- `com.academy.mapper.BookOrderMapper` - 데이터 접근

## 참고사항

이 패키지는 REST API가 아닌 일반 MVC 패턴을 사용합니다.
교재 주문 기능은 별도의 주문 시스템으로 관리됩니다.