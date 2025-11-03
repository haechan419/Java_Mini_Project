// ============================================
// InputValidator.java - 공통 입력 검증
// ============================================
package memberBoard.validator;

public class InputValidator {
    
    public boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("^010-\\d{4}-\\d{4}$");
    }
    
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }
    
    public boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    public boolean isInRange(String value, int min, int max) {
        if (value == null) return false;
        int length = value.length();
        return length >= min && length <= max;
    }
}