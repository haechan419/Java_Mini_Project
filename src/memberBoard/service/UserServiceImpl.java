package memberBoard.service;

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
	}

	@Override
	public void register(UserDTO userDTO) throws UserException {
		// TODO Auto-generated method stub
		if (repository.findByUsername(userDTO.getUsername()) != null) {
			throw new UserException("이미 존재하는 사용자입니다.");
		}
		User user = new User(idCounter++, userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
		repository.save(user);
	}

	@Override
	public User login(String username, String password) throws UserException {
		// TODO Auto-generated method stub
		User user = repository.findByUsername(username);
		if(user == null) throw new UserException("존재하지 않는 사용자입니다.");
		if(!user.getPassword().equals(password)) throw new UserException("비밀번호가 일치하지 않습니다.");
		return user;
	}
}
