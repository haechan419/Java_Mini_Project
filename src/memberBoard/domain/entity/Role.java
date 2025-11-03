// ============================================
// Role.java - 역할 Enum
// ============================================
package memberBoard.domain.entity;

public enum Role {
    USER("일반 사용자"),
    ADMIN("관리자");
    
    private final String description;
    
    Role(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static Role fromString(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return USER;
        }
    }
}