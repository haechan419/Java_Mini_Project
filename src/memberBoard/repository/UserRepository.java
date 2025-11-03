// ============================================
// UserRepository.java - 인터페이스
// ============================================
package memberBoard.repository;

import java.util.List;
import java.util.Optional;
import memberBoard.domain.entity.User;

public interface UserRepository {
	// CRUD 기본 메서드
	void save(User user);

	Optional<User> findById(int id);

	Optional<User> findByUsername(String username);

	List<User> findAll();

	void update(User user);

	void delete(String username);

	// 비즈니스 메서드
	boolean existsByUsername(String username);

	Optional<User> findByNameAndEmail(String name, String email);
}