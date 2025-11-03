// ============================================
// UserDTO.java - 데이터 전송 객체
// ============================================
package memberBoard.domain.dto;

public class UserDTO {
    private final String username;
    private final String password;
    private final String name;
    private final String phone;
    private final String email;
    
    public UserDTO(String username, String password, String name, 
                   String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    // Getters only (DTO는 불변 객체로 설계)
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}
