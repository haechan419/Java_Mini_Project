package memberBoard.service;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;

// 비즈니스 로직
// 인터페이스
public interface UserService {
	void register(UserDTO userDTO) throws UserException;
	User login(String username, String password) throws UserException;
}
