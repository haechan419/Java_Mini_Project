# 회원 관리 및 게시판 시스템 (Java Mini Project)

---

## 기획 의도
Java 기반의 콘솔 애플리케이션으로 회원 관리와 게시판 기능을 구현하여,  
객체지향 프로그래밍(OOP) 원칙과 예외 처리, 컬렉션 활용 능력을 향상시키고자 기획하였습니다.  
사용자 인증, 권한 분리, 데이터 검증, 비밀번호 암호화 등 실무에서 필요한 핵심 기능을 직접 구현하는 데 중점을 두었습니다.

---

## 기획 목표
- Java의 상속과 인터페이스 활용 능력 강화  
- 예외 처리(try-catch)를 통한 안정적인 프로그램 설계  
- 컬렉션 프레임워크 사용 이유 및 적용 경험 습득  
- JDBC를 이용한 MySQL 연동 및 데이터베이스 운영 경험  
- bcrypt 암호화 라이브러리(jbcrypt)를 사용한 보안 강화  

---

## 주요 아이디어
- 회원가입, 로그인, 프로필 수정, 회원 탈퇴 등 회원 관리 기능 구현  
- 게시글 작성, 조회, 수정, 삭제가 가능한 게시판 기능 구현  
- 관리자 계정과 일반 사용자 권한 구분 및 메뉴 분리  
- 비밀번호는 jbcrypt 라이브러리로 암호화하여 보안성 강화  
- 데이터 유효성 검증을 위한 Validator 클래스 분리  

---

## 프로젝트 노션
[프로젝트 노션](https://www.notion.so/29c65619610a80fd8afef186e02b0a98)  

---

## 팀 구성 및 역할
- **개인 프로젝트**  
- 전체 설계, 개발, 테스트 및 문서화 모두 단독 수행

---

## 기술 스택
- Java 21  
- MySQL (mysql-connector-j)  
- jbcrypt (비밀번호 해싱)  
- 표준 Java 컬렉션 프레임워크 (List, Optional 등)  

---

## 개발 환경
- IntelliJ IDEA (Java IDE)  
- MySQL Workbench (DB 관리)  
- Java SDK 11  
- Gradle (빌드 관리)  

---

## 프로젝트 구조
```plaintext
memberBoard/
├── Main.java                      # 애플리케이션 시작점
│
├── config/                        # 설정 관련
│   ├── DBConnection.java         # DB 연결 (싱글톤)
│   └── AppConfig.java            # 애플리케이션 설정 상수
│
├── domain/                        # 도메인 모델
│   ├── entity/                   # 엔티티 (DB 테이블과 매핑)
│   │   ├── User.java            # 사용자 엔티티
│   │   ├── Board.java           # 게시글 엔티티
│   │   └── Role.java            # 권한 Enum
│   └── dto/                      # 데이터 전송 객체
│       ├── UserDTO.java         # 사용자 DTO
│       └── BoardDTO.java        # 게시글 DTO
│
├── repository/                    # 데이터 접근 계층
│   ├── UserRepository.java      # 사용자 Repository 인터페이스
│   ├── UserRepositoryImpl.java  # 사용자 Repository 구현
│   ├── BoardRepository.java     # 게시글 Repository 인터페이스
│   └── BoardRepositoryImpl.java # 게시글 Repository 구현
│
├── service/                       # 비즈니스 로직 계층
│   ├── UserService.java         # 사용자 Service 인터페이스
│   ├── UserServiceImpl.java     # 사용자 Service 구현
│   ├── BoardService.java        # 게시글 Service 인터페이스
│   └── BoardServiceImpl.java    # 게시글 Service 구현
│
├── controller/                    # 컨트롤러 계층
│   ├── UserController.java      # 사용자 컨트롤러
│   └── BoardController.java     # 게시글 컨트롤러
│
├── view/                          # 뷰 계층 (UI)
│   ├── MainView.java            # 메인 화면 (메뉴)
│   ├── InputHandler.java        # 입력 처리
│   └── MessageView.java         # 출력 처리
│
├── exception/                     # 예외 처리
│   ├── UserException.java       # 사용자 관련 예외
│   └── BoardException.java      # 게시글 관련 예외
│
├── validator/                     # 유효성 검증
│   ├── UserValidator.java       # 사용자 입력 검증
│   ├── BoardValidator.java      # 게시글 입력 검증
│   └── InputValidator.java      # 공통 입력 검증
│
├── security/                      # 보안 관련
│   └── PasswordUtil.java        # 비밀번호 암호화
│
└── util/                          # 유틸리티
    ├── PasswordGenerator.java   # 임시 비밀번호 생성
    └── StringUtil.java          # 문자열 유틸
```
주요 구현 기능
회원 관리

회원가입 시 사용자 정보 입력 검증 및 비밀번호 bcrypt 암호화

로그인 시 비밀번호 검증 및 활성 상태 확인

회원정보 조회, 수정, 삭제 기능 구현

아이디 찾기 및 비밀번호 재설정(임시 비밀번호 발급) 기능

게시판 관리

게시글 작성, 목록 조회, 상세 조회

작성자 권한 기반 게시글 수정 및 삭제 기능

관리자 권한으로 전체 게시글 관리

예외 처리

모든 사용자 입력 및 DB 연동 과정에 try-catch 문 적용

사용자 정의 예외 클래스로 명확한 에러 구분 및 메시지 제공

상속과 인터페이스

BoardService, UserService 인터페이스와 구현체 분리

Entity, DTO 클래스 계층 구조 설계

컬렉션 프레임워크 사용 이유

다수의 사용자 및 게시글 데이터를 관리하고 조회하는 데 List 컬렉션 사용

Optional 클래스를 활용해 NullPointerException 예방

Stream API를 이용한 데이터 필터링 및 변환으로 코드 가독성 향상

기술 요약
MySQL 연동: JDBC mysql-connector-j를 이용해 DB CRUD 구현

비밀번호 보안: jbcrypt 라이브러리로 비밀번호 해싱 및 검증

입력 검증: 정규표현식 기반 전화번호, 이메일, 비밀번호 등 유효성 검사

콘솔 UI: 사용자 친화적 입력 핸들링과 메뉴 구성

객체지향 설계: 책임 분리 및 유지보수 용이성 고려한 계층 구조

환경 변수 및 설치
MySQL 설치 및 데이터베이스 생성

프로젝트 클론 및 빌드


git clone https://github.com/yourusername/member-board.git
cd member-board
./gradlew build
src/main/resources/config.properties (또는 코드 내 DB 설정) 파일에 DB 연결 정보 설정

properties

db.url=jdbc:mysql://localhost:3306/memberboard?useSSL=false&serverTimezone=UTC
db.username=root
db.password=yourpassword
실행


java -jar build/libs/member-board.jar
프로젝트 마무리
Java 기본 문법부터 JDBC 연동, bcrypt 보안 적용까지 한 프로젝트에 모두 담아 실무 감각 향상에 중점

복잡한 사용자 검증과 예외 처리를 통해 견고한 코드 작성법 경험

단독으로 프로젝트 기획, 개발, 테스트, 문서화 전 과정을 완성하며 책임감과 문제 해결 능력 향상

향후 발전 방향
GUI 환경(예: JavaFX, Swing) 또는 웹 기반 인터페이스로 확장

Spring Framework 및 JPA 도입으로 유지보수성 및 생산성 개선

JWT 기반 인증 및 권한 관리로 보안 강화

커넥션 풀(HikariCP 등) 적용으로 DB 성능 최적화

자동화 테스트(JUnit) 도입으로 코드 신뢰성 향상

클라우드 배포 및 CI/CD 환경 구축

감사합니다!
