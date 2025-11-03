// ============================================
// PasswordUtil.java - 비밀번호 암호화 유틸
// ============================================
package memberBoard.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    public String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 null이거나 빈 값일 수 없습니다.");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        
        if (!hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("유효하지 않은 해시 형식입니다.");
        }
        
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}