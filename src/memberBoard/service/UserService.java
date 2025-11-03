// ============================================
// UserService.java - 인터페이스
// ============================================
package memberBoard.service;

import java.util.List;
import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;

public interface UserService {
	void register(UserDTO userDTO) throws UserException;

	User login(String username, String password) throws UserException;

	void updateUser(String username, String password, String phone, String email) throws UserException;

	void deleteUser(String username) throws UserException;

	List<User> getAllUsers();

	String findUsernameByNameAndEmail(String name, String email) throws UserException;

	void resetPassword(String username, String name, String email) throws UserException;
}