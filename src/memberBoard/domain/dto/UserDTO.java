package memberBoard.domain.dto;

// DTO : View <-> Controller <-> Service 간 데이터 전달용 객체

public class UserDTO {
	private String username;
	private String password;
	private String email;

	public UserDTO(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

}
