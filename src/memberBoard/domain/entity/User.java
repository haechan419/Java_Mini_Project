// ============================================
// User.java - Entity (도메인 모델)
// ============================================
package memberBoard.domain.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class User {
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Role role;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // 생성자
    public User(int id, String username, String password, String name, 
                String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = Role.USER;
        this.status = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public User(String username, String password, String name, 
                String phone, String email) {
        this(0, username, password, name, phone, email);
    }
    
    // 비즈니스 로직 메서드
    public void updateProfile(String password, String phone, String email) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
        if (phone != null && !phone.isEmpty()) {
            this.phone = phone;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        this.updatedAt = LocalDateTime.now();
    }
    
    public void promoteToAdmin() {
        this.role = Role.ADMIN;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void deactivate() {
        this.status = false;
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
    
    public boolean isActive() {
        return this.status;
    }
    
    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public boolean getStatus() { return status; }
    public String getCreatedAt() { return createdAt.format(FORMATTER); }
    public String getUpdatedAt() { return updatedAt.format(FORMATTER); }
    
    // Setters (필요한 것만)
    public void setId(int id) { this.id = id; }
    public void setPassword(String password) { 
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }
    public void setRole(Role role) { 
        this.role = role;
        this.updatedAt = LocalDateTime.now();
    }
    public void setStatus(boolean status) { 
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
    
    @Override
    public String toString() {
        return String.format("User [ID=%d, 아이디=%s, 이름=%s, 연락처=%s, " +
                           "이메일=%s, 권한=%s, 활성화=%s, 가입일=%s, 수정일=%s]",
            id, username, name, phone, email, role, status, 
            getCreatedAt(), getUpdatedAt());
    }
}