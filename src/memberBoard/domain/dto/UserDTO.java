package memberBoard.domain.dto;

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
