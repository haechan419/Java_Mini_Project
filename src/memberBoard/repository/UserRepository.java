package memberBoard.repository;

import java.util.List;

import memberBoard.domain.entity.User;

// 인터페이스

public interface UserRepository {

	void save(User user);

	User findByUsername(String username);

	List<User> findAll();
}
