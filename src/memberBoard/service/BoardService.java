package memberBoard.service;

import java.util.List;

import memberBoard.domain.dto.BoardDTO;

public interface BoardService {
    void createBoard(BoardDTO boardDTO) throws Exception;

    BoardDTO getBoardById(int id) throws Exception;

    List<BoardDTO> getAllBoards() throws Exception;

    void updateBoard(BoardDTO boardDTO) throws Exception;

    void deleteBoard(int id) throws Exception;

    List<BoardDTO> getBoardsByUserId(int userId) throws Exception;
}
