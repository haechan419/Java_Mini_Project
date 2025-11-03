// ============================================
// BoardService.java - 인터페이스
// ============================================
package memberBoard.service;

import java.util.List;
import memberBoard.domain.dto.BoardDTO;
import memberBoard.exception.BoardException;

public interface BoardService {
	void createBoard(BoardDTO boardDTO) throws BoardException;

	BoardDTO getBoardById(int id) throws BoardException;

	List<BoardDTO> getAllBoards();

	List<BoardDTO> getBoardsByUserId(int userId);

	void updateBoard(BoardDTO boardDTO) throws BoardException;

	void deleteBoard(int id) throws BoardException;

	void deleteBoardByUser(int id, int userId) throws BoardException;
}