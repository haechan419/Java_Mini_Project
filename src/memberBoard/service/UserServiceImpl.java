package memberBoard.service;

import java.util.List;

import memberBoard.Security.PasswordUtil;
import memberBoard.domain.dto.UserDTO;
import memberBoard.domain.entity.User;
import memberBoard.exception.UserException;
import memberBoard.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	// 생성자
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void register(UserDTO userDTO) throws UserException {
		if (repository.findByUsername(userDTO.getUsername()) != null) {
			throw new UserException("이미 존재하는 사용자입니다.");
		}
		// 비밀번호 암호화해서 저장
		String hashedPassword = PasswordUtil.hashPassword(userDTO.getPassword());

		User user = new User(0, userDTO.getUsername(), hashedPassword, userDTO.getName(), userDTO.getPhone(),
				userDTO.getEmail());
		repository.save(user);
	}

	@Override
	public User login(String username, String password) throws UserException {
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UserException("존재하지 않는 사용자입니다.");
		// 비밀번호 해시 검증
		if (!PasswordUtil.checkPassword(password, user.getPassword()))
			throw new UserException("비밀번호가 일치하지 않습니다.");
		return user;
	}

	@Override
	public void deleteUser(String username) throws UserException {
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UserException("삭제할 사용자가 존재하지 않습니다.");
		repository.delete(username);
	}

	@Override
	public void updateUser(String username, String password, String phone, String email) throws UserException {
		User user = repository.findByUsername(username);
		if (user == null)
			throw new UserException("존재하지 않는 사용자입니다.");

		if (password != null && !password.isEmpty()) {
			// 비밀번호 암호화해서 업데이트
			user.setPassword(PasswordUtil.hashPassword(password));
		}
		if (phone != null && !phone.isEmpty()) {
			user.setPhone(phone);
		}
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
		}

		user.updateTimestamp();
		repository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public String findUsernameByNameAndEmail(String name, String email) throws UserException {
		List<User> users = repository.findAll();
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name) && user.getEmail().equalsIgnoreCase(email)) {
				return user.getUsername();
			}
		}
		throw new UserException("일치하는 사용자 정보가 없습니다.");
	}

	@Override
	public void resetPassword(String username, String name, String email) throws UserException {
		User user = repository.findByUsername(username);
		if (user == null || !user.getName().equalsIgnoreCase(name) || !user.getEmail().equalsIgnoreCase(email)) {
			throw new UserException("사용자 정보가 일치하지 않습니다.");
		}

		// 임시 비밀번호 생성
		String tempPassword = generateTempPassword();
		String hashed = PasswordUtil.hashPassword(tempPassword);
		user.setPassword(hashed);

		// DB 반영(컬렉션이 아니라 DB라면 save 호출 필요)
		repository.save(user);

		System.out.println("[임시 비밀번호 발급] 임시 비밀번호: " + tempPassword);
		System.out.println("로그인 후 반드시 비밀번호를 변경해주세요.");
	}

	private String generateTempPassword() {
		return java.util.UUID.randomUUID().toString().substring(0, 8); // 8자리 임시 비밀번호
	}
}
