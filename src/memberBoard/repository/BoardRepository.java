// ============================================
// BoardRepository.java - 인터페이스
// ============================================
package memberBoard.repository;

import java.util.List;
import java.util.Optional;
import memberBoard.domain.entity.Board;

public interface BoardRepository {
	void save(Board board);

	Optional<Board> findById(int id);

	List<Board> findAll();

	List<Board> findByUserId(int userId);

	void update(Board board);

	void delete(int id);

	boolean existsById(int id);
}