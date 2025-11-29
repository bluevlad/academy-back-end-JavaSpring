# Exam Package

시험(Examination) 관리 패키지입니다.

## 패키지 구조

```
exam/
├── ExamApi.java           # REST API Controller
├── ExamBankApi.java       # Exam Bank REST API Controller
└── service/
    ├── ExamService.java        # Exam Service
    ├── ExamServiceImpl.java
    ├── ExamBankService.java    # Exam Bank Service
    ├── ExamBankServiceImpl.java
    ├── ExamVO.java             # Exam Value Object
    └── ExamReqVO.java          # Exam Request Value Object
```

## 주요 컴포넌트

### ExamApi

시험 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/exam`

**Endpoints**:

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| GET | `/getExamList` | 시험 목록 조회 (페이징) | examList, paginationInfo |
| POST | `/getExamReq` | 시험 신청 확인 | retMsg (Y/N/조회실패) |
| GET | `/getExamView` | 시험 상세 조회 | examDetail, QueList |

### ExamBankApi

시험 문제은행 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/exambank`

## 사용 예시

### 1. 시험 목록 조회

```bash
GET /api/exam/getExamList?pageIndex=1&pageUnit=10&pageSize=10
```

**Response**:
```json
{
  "examList": [
    {
      "examId": "EXAM001",
      "examTitle": "중간고사",
      "examDate": "2024-03-15",
      "examTime": "90",
      "totalScore": "100"
    }
  ],
  "paginationInfo": {
    "currentPageNo": 1,
    "totalRecordCount": 50,
    "recordCountPerPage": 10,
    "pageSize": 10
  }
}
```

### 2. 시험 신청 확인

```bash
POST /api/exam/getExamReq
Content-Type: application/x-www-form-urlencoded

userId=user001&examId=EXAM001
```

**Response**:
```json
{
  "retMsg": "Y"  // Y: 신청완료, N: 미신청, 조회실패: 에러
}
```

### 3. 시험 상세 조회

```bash
GET /api/exam/getExamView?examId=EXAM001
```

**Response**:
```json
{
  "examDetail": {
    "examId": "EXAM001",
    "examTitle": "중간고사",
    "examDate": "2024-03-15",
    "examTime": "90",
    "description": "중간고사 설명"
  },
  "QueList": [
    {
      "queNo": 1,
      "queText": "문제 내용",
      "queType": "객관식",
      "queScore": 5
    }
  ]
}
```

## 데이터베이스

### 관련 테이블

- `TB_EXAM` - 시험 기본 정보
- `TB_EXAM_QUE` - 시험 문제 정보
- `TB_EXAM_REQ` - 시험 신청 정보

### Mapper

- **Interface**: `com.academy.mapper.ExamMapper`, `com.academy.mapper.ExamBankMapper`
- **XML**: `src/main/resources/mapper/ExamMapper.xml`, `src/main/resources/mapper/ExamBankMapper.xml`

## 페이징 처리

```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(examVO.getPageIndex());
paginationInfo.setRecordCountPerPage(examVO.getPageUnit());
paginationInfo.setPageSize(examVO.getPageSize());
```

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.PaginationInfo` - 페이징 처리
- `com.academy.common.CommonUtil` - 공통 유틸리티