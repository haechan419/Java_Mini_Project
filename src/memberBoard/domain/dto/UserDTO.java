package memberBoard.domain.dto;

// DTO : View <-> Controller <-> Service 간 데이터 전달용 객체
// Controller -> Service로 회원가입 정보를 전달할 때 사용

public class UserDTO {
	private String username;
	private String password;
	private String name;
	private String phone;
	private String email;

	public UserDTO(String username, String password, String name, String phone, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
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

}
