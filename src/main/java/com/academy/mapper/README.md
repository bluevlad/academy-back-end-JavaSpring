# Mapper Package

MyBatis Mapper 인터페이스를 모아놓은 패키지입니다.

## 패키지 구조

```
mapper/
├── BoardMapper.java
├── BookMapper.java
├── CategoryMapper.java
├── CouponMngMapper.java
├── DashBoardMapper.java
├── ExamBankMapper.java
├── ExamMapper.java
├── FormMapper.java
├── KindMapper.java
├── LectureMapper.java
├── LectureMstMapper.java
├── LockerMapper.java
├── LoginMapper.java
├── MacAddressManagerMapper.java
├── MainMapper.java
├── MemberMapper.java
├── MenuMapper.java
├── OpenLectureMapper.java
├── ProductEventMapper.java
├── ProductOrderMapper.java
├── SeriesMapper.java
├── SubjectMapper.java
└── TeacherMapper.java
```

## Mapper 아키텍처

### 직접 Mapper 사용 패턴

이 프로젝트는 **DAO 레이어 없이 Mapper를 직접 사용**합니다:

```
Controller (Api)
    ↓
Service (ServiceImpl)
    ↓
Mapper (Interface)
    ↓
XML Mapper
    ↓
Database
```

### Mapper 인터페이스 패턴

모든 Mapper는 `@Mapper` 어노테이션을 사용합니다:

```java
package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface ExampleMapper {
    List<HashMap<String, String>> exampleList(HashMap<String, String> params);
    int exampleListCount(HashMap<String, String> params);
    HashMap<String, String> exampleView(HashMap<String, String> params);
    void exampleInsert(HashMap<String, String> params);
    void exampleUpdate(HashMap<String, String> params);
    void exampleDelete(HashMap<String, String> params);
}
```

### XML Mapper 연결

각 Mapper 인터페이스는 `src/main/resources/mapper/` 디렉토리의 XML 파일과 매핑됩니다:

**위치**: `src/main/resources/mapper/ExampleMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.academy.mapper.ExampleMapper">

    <select id="exampleList" parameterType="hashMap" resultType="hashMap">
        SELECT *
        FROM TB_EXAMPLE
        WHERE 1=1
        LIMIT #{startNo}, #{pageRow}
    </select>

    <select id="exampleListCount" parameterType="hashMap" resultType="int">
        SELECT COUNT(*)
        FROM TB_EXAMPLE
        WHERE 1=1
    </select>

    <insert id="exampleInsert" parameterType="hashMap">
        INSERT INTO TB_EXAMPLE (
            COLUMN1, COLUMN2, REG_ID, REG_DT
        ) VALUES (
            #{column1}, #{column2}, #{regId}, NOW()
        )
    </insert>

</mapper>
```

## 주요 Mapper 목록

### 1. BoardMapper
- 게시판 CRUD
- 목록 조회 및 카운트

### 2. BookMapper
- 교재 관리
- 교재 판매 목록
- 교재 사용 체크

### 3. LectureMapper
- 강의 CRUD
- 강의 검색
- 강의 코드 생성

### 4. TeacherMapper
- 강사 관리
- 강사 이미지 처리 (최대 32개)

### 5. MemberMapper
- 회원 관리
- 회원 인증

### 6. LoginMapper
- 로그인 처리
- 토큰 관리

### 7. LockerMapper
- 사물함 대여 관리
- 주문 처리
- 환불 처리

### 8. ExamMapper
- 시험 관리
- 시험 문제 관리

### 9. ProductOrderMapper
- 상품 주문 관리
- 쿠폰 관리

### 10. DashBoardMapper
- 대시보드 데이터 조회

## Mapper 메서드 명명 규칙

| 작업 | 메서드명 패턴 | 예시 |
|------|-------------|------|
| 목록 조회 | `[module]List` | `boardList`, `lectureList` |
| 개수 조회 | `[module]ListCount` | `boardListCount` |
| 상세 조회 | `[module]View` 또는 `get[Module]` | `boardView`, `getBoard` |
| 등록 | `[module]Insert` | `boardInsert` |
| 수정 | `[module]Update` | `boardUpdate` |
| 삭제 | `[module]Delete` | `boardDelete` |

## 파라미터 타입

### HashMap 사용

이 프로젝트는 파라미터와 결과를 **HashMap**으로 처리합니다:

```java
// 파라미터
HashMap<String, String> params = new HashMap<>();
params.put("userId", "user001");
params.put("startNo", "0");
params.put("pageRow", "10");

// 조회
List<HashMap<String, String>> list = mapper.exampleList(params);
HashMap<String, String> detail = mapper.exampleView(params);

// CUD
mapper.exampleInsert(params);
mapper.exampleUpdate(params);
mapper.exampleDelete(params);
```

### VO 사용

일부 Mapper는 VO를 사용합니다:

```java
BoardVO boardVO = new BoardVO();
boardVO.setFirstIndex(0);
boardVO.setLastIndex(10);

List<JSONObject> list = mapper.selectBoardList(boardVO);
```

## XML Mapper 주요 패턴

### 1. 동적 쿼리 (조건부 WHERE)

```xml
<select id="exampleList" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM TB_EXAMPLE
    WHERE 1=1
    <if test="searchKeyword != null and searchKeyword != ''">
        AND NAME LIKE CONCAT('%', #{searchKeyword}, '%')
    </if>
    <if test="searchType != null and searchType != ''">
        AND TYPE = #{searchType}
    </if>
    ORDER BY REG_DT DESC
    LIMIT #{startNo}, #{pageRow}
</select>
```

### 2. 페이징 쿼리

```xml
<select id="exampleList" parameterType="hashMap" resultType="hashMap">
    SELECT *
    FROM TB_EXAMPLE
    WHERE 1=1
    ORDER BY REG_DT DESC
    LIMIT #{startNo}, #{pageRow}
</select>
```

### 3. INSERT with Auto-generated Key

```xml
<insert id="exampleInsert" parameterType="hashMap"
        useGeneratedKeys="true" keyProperty="id">
    INSERT INTO TB_EXAMPLE (
        TITLE, CONTENT, REG_ID, REG_DT
    ) VALUES (
        #{title}, #{content}, #{regId}, NOW()
    )
</insert>
```

### 4. Bulk INSERT

```xml
<insert id="exampleBulkInsert" parameterType="list">
    INSERT INTO TB_EXAMPLE (TITLE, CONTENT)
    VALUES
    <foreach collection="list" item="item" separator=",">
        (#{item.title}, #{item.content})
    </foreach>
</insert>
```

## MyBatis 설정

### application.properties

```properties
# MyBatis Mapper 위치
mybatis.mapper-locations=classpath:/mapper/*.xml

# Type Alias 패키지
mybatis.type-aliases-package=com.academy

# Camel Case 변환
mybatis.configuration.map-underscore-to-camel-case=true
```

## Service에서 Mapper 사용

### Constructor Injection

```java
@Service
public class ExampleServiceImpl implements ExampleService {

    private final ExampleMapper exampleMapper;

    @Autowired
    public ExampleServiceImpl(ExampleMapper exampleMapper) {
        this.exampleMapper = exampleMapper;
    }

    @Override
    public List<HashMap<String, String>> getList(HashMap<String, String> params) {
        return exampleMapper.exampleList(params);
    }
}
```

## 트랜잭션 처리

Mapper 메서드는 Service 레이어에서 `@Transactional`로 관리됩니다:

```java
@Service
public class ExampleServiceImpl implements ExampleService {

    @Transactional(readOnly=false, rollbackFor=Exception.class)
    public void insert(HashMap<String, String> params) {
        exampleMapper.exampleInsert(params);
    }
}
```

## 주의사항

1. **Namespace**: XML의 namespace는 Mapper 인터페이스의 풀 패키지 경로와 일치해야 함
2. **Method ID**: XML의 id는 Mapper 인터페이스의 메서드명과 일치해야 함
3. **파라미터 타입**: HashMap 또는 VO 사용
4. **결과 타입**: hashMap, JSONObject, int 등
5. **SQL Injection 방지**: `#{}` 사용 (PreparedStatement)
6. **동적 SQL**: `${}` 사용 시 주의 (SQL Injection 위험)

## 리팩토링 이력

과거 이 프로젝트는 DAO 레이어를 사용했으나, 최근 리팩토링을 통해:
1. DAO 클래스 삭제
2. Mapper 인터페이스로 대체
3. ServiceImpl에서 Mapper 직접 사용
4. XML Mapper의 namespace를 Mapper 인터페이스로 변경

## 참고

- **MyBatis 공식 문서**: https://mybatis.org/mybatis-3/
- **Spring Boot MyBatis**: https://mybatis.org/spring-boot-starter/
- **Mapper 위치**: `src/main/java/com/academy/mapper/`
- **XML 위치**: `src/main/resources/mapper/`