// ============================================
// AppConfig.java - 설정 관리
// ============================================
package memberBoard.config;

public class AppConfig {
    // 애플리케이션 설정 상수
    public static final String APP_NAME = "회원 관리 시스템";
    public static final String VERSION = "1.0.0";
    
    // 비밀번호 정책
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final int MAX_PASSWORD_LENGTH = 20;
    
    // 전화번호 정규식
    public static final String PHONE_REGEX = "^010-\\d{4}-\\d{4}$";
    
    // 이메일 정규식
    public static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    
    // 페이징
    public static final int PAGE_SIZE = 10;
    
    private AppConfig() {
        // 유틸리티 클래스 - 인스턴스화 방지
    }
}