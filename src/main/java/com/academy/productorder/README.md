# ProductOrder Package

상품 주문(Product Order) 및 쿠폰 관리 패키지입니다.

## 패키지 구조

```
productorder/
├── CouponMngApi.java           # Coupon Management REST API Controller
├── ProductOrderApi.java        # Product Order REST API Controller
└── service/
    ├── CouponMngService.java        # Coupon Service
    ├── CouponMngServiceImpl.java
    ├── ProductOrderService.java     # Product Order Service
    └── ProductOrderServiceImpl.java
```

## 주요 컴포넌트

### CouponMngApi

쿠폰 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/coupon`

**주요 기능**:
- 쿠폰 목록 조회
- 쿠폰 상세 조회
- 쿠폰 등록
- 쿠폰 수정
- 쿠폰 삭제
- 쿠폰 발급
- 쿠폰 사용

### ProductOrderApi

상품 주문 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/productorder`

**주요 기능**:
- 주문 목록 조회 (페이징)
- 주문 상세 조회
- 주문 등록
- 주문 수정
- 주문 취소
- 결제 처리
- 배송 관리

## 주요 기능

### 쿠폰 관리

#### 쿠폰 유형
- 정액 할인 쿠폰
- 정률 할인 쿠폰
- 무료 배송 쿠폰
- 사은품 쿠폰

#### 쿠폰 발급 방식
- 자동 발급
- 수동 발급
- 이벤트 발급
- 코드 입력 발급

### 상품 주문 프로세스

#### 1. 주문 생성
```
상품 선택 → 장바구니 → 주문서 작성 → 주문 생성
```

#### 2. 결제 처리
```
결제 정보 입력 → 결제 승인 → 결제 완료
```

#### 3. 배송 처리
```
배송 준비 → 배송 중 → 배송 완료
```

#### 4. 주문 상태
- `101`: 장바구니
- `102`: 주문 대기
- `103`: 주문 확인
- `104`: 결제 완료
- `105`: 배송 준비
- `106`: 배송 중
- `107`: 배송 완료
- `108`: 주문 취소
- `109`: 환불 완료

## 데이터베이스

### 관련 테이블

- `TB_PRODUCT_ORDER` - 주문 정보
- `TB_PRODUCT_ORDER_ITEM` - 주문 상세 정보
- `TB_PRODUCT_ORDER_PAY` - 결제 정보
- `TB_PRODUCT_ORDER_DELIVERY` - 배송 정보
- `TB_COUPON` - 쿠폰 정보
- `TB_COUPON_ISSUE` - 쿠폰 발급 내역

### Mapper

- **Interface**: `com.academy.mapper.ProductOrderMapper`, `com.academy.mapper.CouponMngMapper`
- **XML**: `src/main/resources/mapper/ProductOrderMapper.xml`, `src/main/resources/mapper/CouponMngMapper.xml`

## 사용 예시

### 1. 주문 목록 조회

```bash
GET /api/productorder/list?currentPage=1&pageRow=10
```

**Response**:
```json
{
  "list": [
    {
      "orderNo": "P202400001",
      "userId": "user001",
      "orderDate": "2024-01-01",
      "totalAmount": 50000,
      "statusCd": "105"
    }
  ],
  "totalCount": 50,
  "totalPage": 5,
  "currentPage": 1
}
```

### 2. 쿠폰 발급

```bash
POST /api/coupon/issue
Content-Type: application/json

{
  "couponId": "CPN001",
  "userId": "user001",
  "issueDate": "2024-01-01"
}
```

### 3. 주문 등록

```bash
POST /api/productorder/save
Content-Type: application/json

{
  "userId": "user001",
  "items": [
    {
      "productId": "P001",
      "quantity": 2,
      "price": 25000
    }
  ],
  "couponId": "CPN001",
  "deliveryAddress": "서울시 강남구..."
}
```

## 페이징 처리

```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(currentPage);
paginationInfo.setRecordCountPerPage(pageRow);
```

## 트랜잭션 처리

주문 및 결제 관련 작업은 트랜잭션으로 보호됩니다:

```java
@Transactional(readOnly=false, rollbackFor=Exception.class)
public void createOrder(HashMap<String, String> params) {
    // 주문 생성 → 결제 처리 → 재고 차감
}
```

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.PaginationInfo` - 페이징 처리
- `com.academy.common.CommonUtil` - 공통 유틸리티
- `com.academy.mapper.ProductOrderMapper` - 데이터 접근
- `com.academy.mapper.CouponMngMapper` - 데이터 접근

## 참고사항

1. **주문번호**: P + 연도 + 시퀀스 (예: P202400001)
2. **쿠폰 코드**: 영문 대문자 + 숫자 조합
3. **결제 연동**: PG사 연동 필요 시 추가 개발
4. **재고 관리**: 주문 시 재고 차감 처리
5. **포인트**: 포인트 적립/사용 기능