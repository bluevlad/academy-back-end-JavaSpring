# Common Package

공통 유틸리티 및 설정을 제공하는 패키지입니다.

## 패키지 구조

```
common/
├── CORSFilter.java           # CORS 필터 (모든 API 컨트롤러의 Base Class)
├── JwtUtil.java              # JWT 토큰 유틸리티
├── PaginationInfo.java       # 페이지네이션 정보 클래스
├── CommonUtil.java           # 공통 유틸리티 메서드
├── DBUtil.java               # 데이터베이스 유틸리티
├── Configurations.java       # Spring 설정 빈
├── CommonVO.java             # 공통 Value Object
├── ComDefaultVO.java         # 기본 Value Object
├── ComDefaultCodeVO.java     # 기본 코드 Value Object
└── service/
    ├── CmmUseService.java    # 공통 서비스
    └── CmmnDetailCode.java   # 공통 상세 코드
```

## 주요 컴포넌트

### CORSFilter

모든 REST API 컨트롤러의 기본 클래스로 사용되며, CORS(Cross-Origin Resource Sharing) 정책을 처리합니다.

**사용법**:
```java
@RestController
@RequestMapping("/api/example")
public class ExampleApi extends CORSFilter {
    // API 구현
}
```

**CORS 헤더 설정**:
```java
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: HEAD, GET, POST, PUT, DELETE, OPTIONS
Access-Control-Allow-Headers: x-requested-with, authorization, origin, content-type, accept
Access-Control-Allow-Credentials: true
Access-Control-Max-Age: 3600
```

**특징**:
- Servlet Filter 인터페이스 구현
- OPTIONS 요청 처리 (CORS preflight)
- 모든 도메인 허용 (`*`)
- RESTful HTTP 메서드 모두 지원

### JwtUtil

JWT(JSON Web Token) 토큰 생성, 검증, 추출 기능을 제공합니다.

**주요 메서드**:

```java
// JWT 토큰 생성
String generateToken(String username)

// JWT 토큰에서 사용자명 추출
String extractUsername(String token)

// JWT 토큰 검증
boolean validateToken(String token)
```

**사용 예시**:

```java
@Autowired
private JwtUtil jwtUtil;

// 토큰 생성
String token = jwtUtil.generateToken("user001");

// 토큰 검증
if (jwtUtil.validateToken(token)) {
    // 사용자명 추출
    String username = jwtUtil.extractUsername(token);
}
```

**설정**:
- **SECRET_KEY**: `MySecretKeyForJwtTokenMySecretKeyForJwtToken` (256비트 이상)
- **EXPIRATION_TIME**: 1시간 (3600초)
- **Algorithm**: HS256 (HMAC with SHA-256)

**토큰 구조**:
```json
{
  "sub": "user001",           // Subject (사용자명)
  "iat": 1704067200,          // Issued At (발행 시간)
  "exp": 1704070800           // Expiration (만료 시간)
}
```

**라이브러리**: JJWT 0.11.5

### PaginationInfo

페이지네이션 처리를 위한 헬퍼 클래스입니다.

**주요 속성**:
```java
private int currentPageNo;       // 현재 페이지 번호
private int recordCountPerPage;  // 페이지당 레코드 수
private int pageSize;            // 페이지 사이즈 (페이지 네비게이션 개수)
private int totalRecordCount;    // 전체 레코드 수
```

**주요 메서드**:
```java
// 계산 메서드
int getFirstRecordIndex()   // 첫 번째 레코드 인덱스
int getLastRecordIndex()    // 마지막 레코드 인덱스
int getTotalPageCount()     // 전체 페이지 수
```

**사용 예시**:
```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(1);
paginationInfo.setRecordCountPerPage(10);
paginationInfo.setPageSize(10);

// 전체 레코드 수 설정 (DB 조회 후)
paginationInfo.setTotalRecordCount(totalCount);

// VO에 페이징 정보 설정
vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
vo.setLastIndex(paginationInfo.getLastRecordIndex());
vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
```

**계산 로직**:
- `firstIndex = (currentPageNo - 1) * recordCountPerPage`
- `lastIndex = firstIndex + recordCountPerPage`
- `totalPage = (totalRecordCount + recordCountPerPage - 1) / recordCountPerPage`

### CommonUtil

공통 유틸리티 메서드를 제공합니다.

**주요 메서드**:

```java
// Null 처리
String nullToBlank(Object obj)          // null을 빈 문자열로 변환
String isNull(String str, String def)   // null이면 기본값 반환
boolean empty(Object obj)               // null 또는 빈 값 체크

// 타입 변환
int parseInt(Object obj)                // Object를 int로 변환

// 날짜/시간
String getCurrentYear()                 // 현재 연도 반환 (예: "2024")
String getCurrentDateTime()             // 현재 날짜시간 반환

// IP 주소
String getClientIP(HttpServletRequest request)  // 클라이언트 IP 추출
```

**사용 예시**:
```java
// Null 안전 파라미터 처리
params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));

// 페이지 번호 처리
String curPage = "1";
if(!CommonUtil.empty(commandMap.get("curPage"))){
    curPage = (String)commandMap.get("curPage");
}

// 현재 연도 가져오기
String year = CommonUtil.getCurrentYear();
```

### DBUtil

데이터베이스 직접 연결을 위한 유틸리티입니다.

**용도**:
- MyBatis로 처리하기 어려운 특수한 경우
- 동적 쿼리 실행
- 직접 JDBC 연결이 필요한 경우

**주의**: 일반적으로는 MyBatis Mapper를 사용하는 것이 권장됩니다.

### Configurations

Spring Boot 설정 빈을 정의합니다.

**기능**:
- Bean 등록
- 설정 관리
- 컴포넌트 스캔 설정

### Value Objects

#### CommonVO
공통 속성을 가진 기본 VO입니다.

#### ComDefaultVO
기본 데이터 전송을 위한 VO입니다.

#### ComDefaultCodeVO
코드 정보를 담는 VO입니다.

### CmmUseService

공통 서비스 로직을 제공합니다.

**용도**:
- 여러 모듈에서 공통으로 사용되는 서비스 로직
- 코드 조회
- 공통 데이터 처리

### CmmnDetailCode

공통 코드 상세 정보를 관리합니다.

## 사용 패턴

### 1. API 컨트롤러 기본 패턴

```java
@RestController
@RequestMapping("/api/example")
public class ExampleApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final ExampleService exampleService;

    @Autowired
    public ExampleApi(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/list")
    public JSONObject list(@ModelAttribute HashMap<String, String> params,
                          HttpServletRequest request) throws Exception {
        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(
            CommonUtil.parseInt(params.get("currentPage"))
        );

        // 응답 생성
        HashMap<String, Object> result = new HashMap<>();
        result.put("list", exampleService.getList(params));

        return new JSONObject(result);
    }
}
```

### 2. 세션에서 사용자 정보 추출

```java
public void setParam(HashMap<String, String> params,
                     HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if(session != null) {
        HashMap<String, String> loginInfo =
            (HashMap<String, String>)session.getAttribute("AdmUserInfo");
        if(loginInfo != null) {
            params.put("REG_ID", loginInfo.get("USER_ID"));
            params.put("UPD_ID", loginInfo.get("USER_ID"));
        }
    }
}
```

### 3. 파라미터 안전 처리

```java
params.put("SEARCHTEXT", CommonUtil.isNull(request.getParameter("SEARCHTEXT"), ""));
params.put("SEARCHTYPE", CommonUtil.isNull(request.getParameter("SEARCHTYPE"), ""));
params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit)));
```

## 의존성

### Maven Dependencies

```xml
<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>

<!-- Jakarta Servlet API -->
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
</dependency>
```

## 중요 사항

### 파일 인코딩

**CRITICAL**: 모든 Java 파일은 **UTF-8 without BOM**으로 저장해야 합니다.
- BOM 문자(`\ufeff`)가 포함되면 컴파일 오류 발생
- 과거 `Configurations.java`, `DBUtil.java`에서 이슈 발생 이력

### Jakarta EE (Spring Boot 3.x)

- **항상 사용**: `jakarta.servlet.*`
- **절대 사용 금지**: `javax.*`
- Spring Boot 3.x는 Jakarta EE 9+ 사용

### CORS 정책

- **모든 도메인 허용**: `Access-Control-Allow-Origin: *`
- **프로덕션 환경**: 특정 도메인으로 제한 권장
- **Credentials**: true로 설정 시 와일드카드 사용 불가

### JWT 보안

1. **SECRET_KEY**: 프로덕션 환경에서는 환경변수로 관리
2. **만료 시간**: 1시간 (필요 시 조정 가능)
3. **토큰 갱신**: Refresh Token 패턴 구현 권장
4. **알고리즘**: HS256 (대칭키) 사용 중

## 확장 가능성

### 추가 가능한 유틸리티

1. **FileUtil**: 파일 업로드/다운로드 처리
2. **EmailUtil**: 이메일 발송
3. **SmsUtil**: SMS 발송
4. **ExcelUtil**: 엑셀 다운로드
5. **CryptoUtil**: 암호화/복호화
6. **ValidateUtil**: 유효성 검증

### 개선 권장사항

1. **CORSFilter**: 특정 도메인만 허용하도록 설정
2. **JwtUtil**: Refresh Token 패턴 추가
3. **CommonUtil**: 더 많은 유틸리티 메서드 추가
4. **Logging**: 통합 로깅 유틸리티 추가

## 참고

- 모든 API 컨트롤러는 반드시 `CORSFilter`를 상속해야 합니다
- 페이징이 필요한 경우 `PaginationInfo`를 사용합니다
- JWT 인증이 필요한 경우 `JwtUtil`을 사용합니다
- Null 안전성을 위해 `CommonUtil`의 메서드들을 활용합니다