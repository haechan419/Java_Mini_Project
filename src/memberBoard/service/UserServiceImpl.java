package memberBoard.service;

import java.util.List;

import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.repository.UserRepository;

//구현체

public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private int idCounter = 1;

	// 생성자
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;

		// 관리자 계정 기본 생성
		User admin = new User(idCounter++, "admin", "admin123", "관리자", "000-0000-0000", "admin@system.com");
		admin.setRole("ADMIN");
		repository.save(admin);
	}

	@Override
	public void register(UserDTO userDTO) throws UserException {
		// TODO Auto-generated method stub
		if (repository.findByUsername(userDTO.getUsername()) != null) {
			throw new UserException("이미 존재하는 사용자입니다.");
		}
		User user = new User(idCounter++, userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(),
				userDTO.getPhone(), userDTO.getEmail());
		repository.save(user);
	}

	@Override
	public User login(String username, String password) throws UserException {
		// TODO Auto-generated method stub
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UserException("존재하지 않는 사용자입니다.");
		if (!user.getPassword().equals(password))
			throw new UserException("비밀번호가 일치하지 않습니다.");
		return user;
	}

	@Override
	public void deleteUser(String username) throws UserException {
		// TODO Auto-generated method stub
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UserException("삭제할 사용자가 존재하지 않습니다.");
		repository.delete(username);
	}

	@Override
	public void updateUser(String username, String password, String phone, String email) throws UserException {
		// TODO Auto-generated method stub
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UserException("존재하지 않는 사용자입니다.");

		if (password != null && !password.isEmpty()) {
			user.setPassword(password);
		}
		if (phone != null && !phone.isEmpty()) {
			user.setPhone(phone);
		}
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
		}

		// 수정일 갱신 및 저장
		user.updateTimestamp();
		repository.save(user);
	}

	// 전체 회원 조회 기능
	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

}
