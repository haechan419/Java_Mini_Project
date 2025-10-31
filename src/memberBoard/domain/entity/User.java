package memberBoard.domain.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Entity
//(Entity는 DB 저장용, DTO는 데이터 전달용)

public class User {
	private int id;
	private String username; // 사용자 아이디
	private String password; // 비밀번호
	private String name; // 이름
	private String phone; // 연락처
	private String email; // 이메일
	private String role; // 권한(USER/ADMIN)
	private boolean status; // 계정 활성화 여부
	private String createdAt; // 가입일
	private String updateAt; // 수정일

	// 생성자
	public User(int id, String username, String password, String name, String phone, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.role = "USER"; // 기본 권한은 일반 사용자
		this.status = true; // 가입 시 활성화 상태로 설정
		this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.updateAt = this.createdAt; // 처음에는 가입일과 동일
	}

	// Getter
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public boolean isStatus() {
		return status;
	}

	// Setter

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	// 수정일 자동 갱신
	public void updateTimestamp() {
		this.updateAt=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
	}

	@Override
	public String toString() {
		return "User [ID=" + id + ", 아이디=" + username + ", 이름=" + name + ", 연락처=" + phone + ", 이메일=" + email
				+ ", 권한=" + role + ", 활성화=" + status + ", 가입일=" + createdAt + ", 수정일=" + updateAt + "]";
	}
	
	

}
