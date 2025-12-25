# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Small Online Academy Backend Service built with Spring Boot 3.x, MyBatis, and MySQL. Provides REST APIs for managing an online academy system including boards, members, lectures, books, exams, lockers, and dashboards.

**Tech Stack:**
- Java 17
- Spring Boot 3.2.0
- MyBatis 3.0.4
- MySQL 8.x (acm_basic database)
- JJWT 0.11.5 for JWT token authentication
- Maven for build management

EgovFramework -> SpringBoot + React

## Build and Run Commands

**Build:**
```bash
mvn clean package
```

**Run locally:**
```bash
mvn spring-boot:run
```

**Run built JAR:**
```bash
java -jar target/academy-0.0.1-SNAPSHOT.jar
```

**Run tests:**
```bash
mvn test
```

Default server port: 8080

## Database Setup

1. Create MySQL schema as configured in environment
2. Configure connection in `src/main/resources/application.properties`
3. **Important:** Use environment variables or `.env` file for credentials (never commit secrets)
4. MyBatis mapper XMLs are in `src/main/resources/mapper/*.xml`

> See `CLAUDE.local.md` for local development credentials (not tracked in git)

## Architecture Overview

### Three-Tier Architecture Pattern

The codebase follows a layered architecture with two distinct patterns:

**Pattern 1: Direct Service with Mapper (Preferred, Modern)**
Used by: board, member, book, exam, locker, dashboard, menu
```
Api (Controller) → Service → Mapper (MyBatis Interface) → XML Mapper
```

**Pattern 2: Legacy Service-DAO Pattern**
Used by: bookCmmt, bookOrder (legacy modules)
```
Controller → ServiceImpl → DAO → XML Mapper
```

### Package Structure by Domain Module

Each domain module (board, member, lecture, exam, etc.) is organized as:
```
com.academy.<module>/
  ├── <Module>Api.java          # REST Controller (@RestController)
  ├── service/
  │   ├── <Module>Service.java  # Business logic (@Service)
  │   └── <Module>VO.java       # Value Object (DTO)
  └── (for legacy modules)
      └── service/impl/
          ├── <Module>ServiceImpl.java
          └── <Module>DAO.java
```

### MyBatis Mappers

MyBatis mappers are centralized:
```
com.academy.mapper/
  ├── BoardMapper.java          # @Mapper interface
  ├── BookMapper.java
  ├── LectureMstMapper.java
  └── ...
```

Corresponding XML files:
```
src/main/resources/mapper/
  ├── board.xml                 # namespace="com.academy.mapper.BoardMapper"
  ├── book.xml
  ├── lectureMstSQL.xml
  └── ...
```

### Common Components

Located in `com.academy.common/`:
- `CORSFilter`: Base class for API controllers with CORS configuration
- `JwtUtil`: JWT token generation, validation, and extraction
- `PaginationInfo`: Pagination helper (pageUnit=10, pageSize=10)
- `Configurations`: Properties file reader
- `DBUtil`: Database utilities
- `CommonUtil`, `CommonVO`: Shared utility functions and VOs

## API Controller Conventions

All API controllers:
1. Extend `CORSFilter` for CORS support
2. Use `@RestController` and `@RequestMapping("/api/<module>")`
3. Return `JSONObject` (from org.json.simple)
4. Accept VOs via `@ModelAttribute` or `@RequestBody`
5. Follow this response structure:
   ```java
   HashMap<String,Object> jsonObject = new HashMap<>();
   jsonObject.put("data", result);
   jsonObject.put("retMsg", "OK"); // or "FAIL"
   return new JSONObject(jsonObject);
   ```

## Service Class Conventions

All Service classes must:
1. **Implement `Serializable`** for session serialization support
2. Use `@Service` annotation
3. Inject dependencies via constructor injection (preferred over field injection)
4. Follow naming convention: `<Module>Service.java`

Example:
```java
@Service
public class BookService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    // Business logic methods...
}
```

### Standard CRUD Endpoint Patterns

**GET endpoints** (list/detail):
```java
@GetMapping(value = "/get<Module>List")
public JSONObject get<Module>List(@ModelAttribute <Module>VO vo)

@GetMapping(value = "/get<Module>Detail")
public JSONObject get<Module>Detail(@ModelAttribute <Module>VO vo)
```

**POST endpoints** (create/update/delete):
```java
@PostMapping(value = "/insert<Module>")
@PostMapping(value = "/update<Module>")
@PostMapping(value = "/delete<Module>")
```

**RESTful endpoints** (newer modules like lecture):
```java
@GetMapping, @PostMapping, @PutMapping, @DeleteMapping
```

## Pagination

Most list APIs implement pagination using `PaginationInfo`:
```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(vo.getPageIndex());
paginationInfo.setRecordCountPerPage(vo.getPageUnit());
paginationInfo.setPageSize(vo.getPageSize());

vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
vo.setLastIndex(paginationInfo.getLastRecordIndex());
```

## MyBatis XML Mapper Conventions

1. Use `LIMIT #{recordCountPerPage} OFFSET #{firstIndex}` for pagination
2. Dynamic SQL with `<if test="">` for optional fields
3. Auto-generate IDs using `<selectKey>` with UUID
4. Date fields use MySQL `now()` function
5. Namespace must match the Mapper interface fully qualified name

## Authentication

JWT-based authentication:
- `JwtUtil` generates tokens with 1-hour expiration
- **Security Note:** JWT secret key should be externalized via environment variables in production
- Login API validates credentials and returns JWT token
- Protected endpoints should validate token via `JwtUtil.validateToken()`

## Module Refactoring Status

Recent refactoring (see git status) moved from legacy DAO pattern to modern Mapper pattern:
- ✅ Completed: lecture module (Category, Form, Kind, LectureMst, Lecture, OpenLecture, ProductEvent, Series, Subject, Teacher, MacAddressManager)
- ✅ Completed: board, member, book, exam, locker, dashboard
- ⚠️ Legacy: bookCmmt, bookOrder (still use ServiceImpl/DAO pattern)

When working on lecture modules, note:
- Old: `src/main/java/com/academy/lecture/service/impl/*ServiceImpl.java` (deleted)
- New: `src/main/java/com/academy/lecture/service/*Service.java` (direct @Service)
- Old: `src/main/java/com/academy/lecture/service/impl/*DAO.java` (deleted)
- New: `src/main/java/com/academy/mapper/*Mapper.java` (MyBatis @Mapper)

## Known Issues

**BOM Encoding:** If encountering compile errors with `\ufeff` character, ensure source files are saved as UTF-8 without BOM. This has been fixed in `Configurations.java` and `DBUtil.java`.

## Adding New Features

When adding new API endpoints or modules:

1. Create VO in `<module>/service/<Module>VO.java` extending `CommonVO`
2. Create Mapper interface in `com.academy.mapper/<Module>Mapper.java` with `@Mapper`
3. Create XML mapper in `src/main/resources/mapper/<module>.xml`
4. Create Service in `<module>/service/<Module>Service.java` with `@Service` **implementing `Serializable`**
5. Create API Controller in `<module>/<Module>Api.java` extending `CORSFilter`
6. Use constructor injection for dependencies (preferred over `@Autowired`)

Follow the pattern from modern modules (board, lecture) rather than legacy modules (bookCmmt, bookOrder).