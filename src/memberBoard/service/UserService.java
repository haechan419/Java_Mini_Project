package memberBoard.service;

import java.util.List;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;

// 비즈니스 로직
// 인터페이스
public interface UserService {
	// 회원 가입
	void register(UserDTO userDTO) throws UserException;

	// 회원 로그인
	User login(String username, String password) throws UserException;

	// 회원 삭제
	void deleteUser(String username) throws UserException;

	// 회원 수정
	void updateUser(String username, String password, String phone, String email) throws UserException;

	// 전체 회원 조회(관리자 전용)
	List<User> getAllUsers();
}
