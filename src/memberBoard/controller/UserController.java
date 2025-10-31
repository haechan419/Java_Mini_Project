package memberBoard.controller;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.service.UserService;

// 콘솔 입력/출력 담당

public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	public void register(String username, String password, String email) {
		try {
			service.register(new UserDTO(username,password,email));
		}catch(UserException e) {
			System.out.println("[오류] " + e.getMessage());
		}
	}
	
	public void login(String username, String password) {
		try {
			User user = service.login(username, password);
			System.out.println("[로그인 성공] "+ user.getUsername() + "님 환영합니다!");
		} catch(UserException e) {
			System.out.println("[로그인 실패] " + e.getMessage());
		} 
	}
	

}
