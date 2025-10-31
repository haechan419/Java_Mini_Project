package memberBoard.repository;

// 구현체

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import memberBoard.domain.entity.User;

public class UserRepositoryImpl implements UserRepository {
	// 메모리 저장소(나중에 DB로 교체 가능)
	// private final List<User> userList = new ArrayList<>();
	// 컬렉션 프레임워크 사용
	private final Map<String, User> userTable = new HashMap<>();

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		if (userTable.containsKey(user.getUsername())) {
			User existingUser = userTable.get(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setPhone(user.getPhone());
			existingUser.setEmail(user.getEmail());
			// 수정일 자동 갱신
			existingUser.updateTimestamp();
			userTable.put(user.getUsername(), existingUser);
		} else {
			// 신규 저장
			userTable.put(user.getUsername(), user);
		}
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userTable.get(username);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(userTable.values());
	}

	@Override
	public void delete(String username) {
		userTable.remove(username);
	}

}
