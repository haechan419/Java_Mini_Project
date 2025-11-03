// ============================================
// StringUtil.java - 문자열 유틸
// ============================================
package memberBoard.util;

public class StringUtil {
    
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }
    
    public static String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }
    
    public static String maskEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }
        
        String[] parts = email.split("@");
        String localPart = parts[0];
        
        if (localPart.length() <= 2) {
            return localPart.charAt(0) + "*@" + parts[1];
        }
        
        return localPart.charAt(0) + "***" + localPart.charAt(localPart.length() - 1) 
               + "@" + parts[1];
    }
    
    private StringUtil() {
        // 유틸리티 클래스 - 인스턴스화 방지
    }
}