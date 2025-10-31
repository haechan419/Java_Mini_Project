package memberBoard.repository;

// 구현체

import java.util.ArrayList;
import java.util.List;

import memberBoard.domain.entity.User;

public class UserRepositoryImpl implements UserRepository {
	// 메모리 저장소(나중에 DB로 교체 가능)
	private final List<User> userList = new ArrayList<>();

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userList.add(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		for (User user : userList) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userList;
	}

}
