// ============================================
// PasswordGenerator.java - 임시 비밀번호 생성
// ============================================
package memberBoard.util;

import java.util.UUID;

public class PasswordGenerator {
    
    private static final int DEFAULT_LENGTH = 8;
    
    public String generate() {
        return generate(DEFAULT_LENGTH);
    }
    
    public String generate(int length) {
        if (length <= 0 || length > 32) {
            throw new IllegalArgumentException("비밀번호 길이는 1-32 사이여야 합니다.");
        }
        
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, Math.min(length, uuid.length()));
    }
}