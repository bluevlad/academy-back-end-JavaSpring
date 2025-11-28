# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Small Online Academy backend - a Spring Boot 3.2.0 REST API service for managing an educational platform with courses, instructors, students, exams, lockers, and administrative functions.

**Tech Stack**: Java 17, Spring Boot 3.2.0, MyBatis 3.0.4, MySQL 8.x, JJWT 0.11.5

## Build & Run Commands

### Build
```bash
mvn clean package
```

### Run Development Server
```bash
mvn spring-boot:run
# Server runs on http://localhost:8080
```

### Run from JAR
```bash
java -jar target/academy-0.0.1-SNAPSHOT.jar
```

### Database Setup
1. Create MySQL schema: `acm_basic`
2. Execute DDL scripts in order:
   ```bash
   # From MySQL client or workbench:
   source ddls/member.sql
   source ddls/board.sql
   ```

### Testing
```bash
mvn test
```

## Architecture & Code Structure

### Package Architecture Pattern

This codebase follows a **layered architecture with direct Mapper usage** (DAO layer has been eliminated):

```
com.academy
├── [module]/                    # Feature modules (board, member, lecture, etc.)
│   ├── [Module]Api.java        # REST Controller (@RestController)
│   └── service/
│       ├── [Module]Service.java      # Service interface
│       ├── [Module]ServiceImpl.java  # Service implementation (uses Mapper directly)
│       └── [Module]VO.java          # Value Object (optional)
├── mapper/                      # MyBatis Mapper interfaces (@Mapper)
│   └── [Module]Mapper.java
└── common/                      # Shared utilities
```

**Key Pattern**: ServiceImpl classes directly inject and use Mapper interfaces - there is NO DAO layer.

### Important Architecture Decisions

1. **Spring Boot 3.x Migration Completed**:
   - All `javax.*` imports migrated to `jakarta.*`
   - EgovPropertyService replaced with `@Value` annotations
   - Example: `@Value("${pageUnit:10}")` for property injection

2. **MyBatis Integration**:
   - XML mappers located in `src/main/resources/mapper/*.xml`
   - Mapper interfaces in `com.academy.mapper` package
   - Namespace pattern: `<mapper namespace="com.academy.mapper.[Module]Mapper">`
   - All Mapper interfaces use `@Mapper` annotation

3. **REST API Controller Pattern**:
   - All API controllers extend `CORSFilter` for cross-origin support
   - Use `@RestController` + `@RequestMapping("/api/[module]")`
   - Constructor-based dependency injection (not field injection)
   - Return `JSONObject` (org.json.simple) for responses
   - HTTP method-specific mappings: `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`

4. **Authentication**:
   - HttpSession-based auth (not JWT for API endpoints currently)
   - Session key: `AdmUserInfo.USER_ID`
   - Automatically populated in `REG_ID` and `UPD_ID` fields

5. **File Uploads**:
   - Use `MultipartHttpServletRequest` for file handling
   - Upload paths: `member_upload/`, `openlecture_upload/`
   - File utility: `FileUtil.uploadFile()`

### Module Organization

**Feature Modules** (each follows same pattern):
- `board` - Bulletin board system
- `member` - User management
- `lecture` - Course/lecture management (11 sub-APIs)
- `exam` - Examination system
- `locker` - Locker assignment
- `login` - Authentication
- `dashboard` - Admin dashboard
- `menu` - Menu management
- `book` - Textbook/materials management

**Lecture Module** (largest, most complex):
- 11 APIs: Category, Teacher, Subject, Form, Kind, Series, Lecture, LectureMst, MacAddressManager, OpenLecture, ProductEvent
- Complex business logic with code generation (e.g., `D202400001`, `M202400001`)
- Bridge table pattern for many-to-many relationships
- Extensive file upload support (up to 32 images for teachers)

### Common Utilities (`com.academy.common`)

- `CORSFilter`: Base class for all API controllers (sets CORS headers)
- `JwtUtil`: JWT token generation/validation
- `PaginationInfo`: Pagination helper
- `CommonUtil`: Utility methods (null handling, string operations)
- `DBUtil`: Database connection utilities
- `Configurations`: Application configuration beans

### Data Access Layer

**NO DAO classes** - ServiceImpl directly uses Mapper interfaces:

```java
@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleMapper exampleMapper;  // Direct Mapper injection

    @Override
    public List<HashMap<String, String>> getList(HashMap<String, String> params) {
        return exampleMapper.getList(params);  // Direct Mapper call
    }
}
```

### API Response Pattern

All APIs return `JSONObject` with standard structure:

**Success**:
```json
{
  "result": "success",
  "message": "작업이 완료되었습니다",
  "list": [...],
  "totalCount": 100,
  "currentPage": 1,
  "totalPage": 10
}
```

**Error**: Transaction rollback with exception handling

### Pagination Pattern

All list endpoints support pagination:
- Request params: `currentPage` (default: 1), `pageRow` (default: `pageUnit` from properties)
- Use `PaginationInfo` class for calculation
- Response includes: `list`, `totalCount`, `totalPage`, `currentPage`

## Adding New Features

### Adding a New API Module

1. **Create Mapper interface** in `com.academy.mapper`:
```java
@Mapper
public interface ExampleMapper {
    List<HashMap<String, String>> exampleList(HashMap<String, String> params);
    void exampleInsert(HashMap<String, String> params);
}
```

2. **Create Service interface** in `com.academy.[module].service`:
```java
public interface ExampleService {
    List<HashMap<String, String>> getList(HashMap<String, String> params);
    void insert(HashMap<String, String> params);
}
```

3. **Create ServiceImpl** in same package (NOT in `service.impl`):
```java
@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleMapper exampleMapper;  // Direct injection

    @Override
    public List<HashMap<String, String>> getList(HashMap<String, String> params) {
        return exampleMapper.exampleList(params);
    }
}
```

4. **Create API Controller**:
```java
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
        // Implementation
    }
}
```

5. **Create MyBatis XML mapper** in `src/main/resources/mapper/`:
```xml
<mapper namespace="com.academy.mapper.ExampleMapper">
    <select id="exampleList" parameterType="hashMap" resultType="hashMap">
        SELECT * FROM TB_EXAMPLE
    </select>
</mapper>
```

### Refactoring Legacy Code

When refactoring from old DAO-based pattern to current Mapper pattern:

1. Delete DAO classes and `service.impl` package
2. Create Mapper interface in `com.academy.mapper`
3. Move ServiceImpl to `service` package (not `service.impl`)
4. Update ServiceImpl to inject Mapper instead of DAO
5. Update XML namespace to point to new Mapper interface
6. Update all `dao.*` method calls to `mapper.*`

**Example**: The `lecture` package was recently refactored following this pattern.

## Important Conventions

### File Encoding
- **Must use UTF-8 without BOM** - BOM characters (`\ufeff`) cause compilation errors
- If you see compile errors with `\ufeff`, re-save files as UTF-8 without BOM

### Jakarta EE (not javax)
- Always use `jakarta.servlet.*`, `jakarta.annotation.*`
- Never use `javax.*` - this project uses Spring Boot 3.x

### Property Injection
- Use `@Value("${propertyName:defaultValue}")` pattern
- Never use EgovPropertyService (legacy, removed)

### Session Access
- Get user from session: `request.getSession().getAttribute("AdmUserInfo")`
- Extract user ID for audit fields (REG_ID, UPD_ID)

### Mapper Method Naming
- List queries: `[module]List`
- Count queries: `[module]ListCount`
- Single record: `[module]View`
- Insert: `[module]Insert`
- Update: `[module]Update`
- Delete: `[module]Delete`

## Configuration

### Database (application.properties)
```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/acm_basic
spring.datasource.username=root
spring.datasource.password=dnflskfk
mybatis.mapper-locations=classpath:/mapper/*.xml
pageUnit=10  # Default page size
```

### Port
- Default: 8080
- Change via: `server.port=8081` in application.properties

## Related Repositories

- **Frontend**: https://github.com/bluevlad/react/tree/main/datta-able-free-react-admin-template (React Admin)
- **Legacy Admin**: https://github.com/bluevlad/Java/tree/master/Academy-egovframework (eGov framework)