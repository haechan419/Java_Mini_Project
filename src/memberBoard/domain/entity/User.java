package memberBoard.domain.entity;

// Entity
//(Entity는 DB 저장용, DTO는 데이터 전달용)
public class User {
	private int id;
	private String username;
	private String password;
	private String email;

	public User(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	// Getter / Setter

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
