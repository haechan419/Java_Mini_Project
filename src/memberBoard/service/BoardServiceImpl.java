package memberBoard.service;

import java.util.ArrayList;
import java.util.List;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.domain.entity.Board;
import memberBoard.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository;

    public BoardServiceImpl(BoardRepository repository) {
        this.repository = repository;
    }

    // DTO → Entity 변환
    private Board toEntity(BoardDTO dto) {
        Board board = new Board(dto.getId(), dto.getTitle(), dto.getContent(), dto.getUserId());
        // createdAt, updatedAt는 엔티티가 자동 처리 (DB에서 NOW() 등)
        return board;
    }

    // Entity → DTO 변환
    private BoardDTO toDTO(Board board) {
        return new BoardDTO(board.getId(), board.getTitle(), board.getContent(), board.getUserId(),
                board.getCreatedAt(), board.getUpdatedAt());
    }

    @Override
    public void createBoard(BoardDTO boardDTO) throws Exception {
        Board board = toEntity(boardDTO);
        repository.save(board);
        // 저장 후 id 생성 등 필요하면 다시 변환 가능
    }

    @Override
    public BoardDTO getBoardById(int id) throws Exception {
        Board board = repository.findById(id);
        if (board == null) return null;
        return toDTO(board);
    }

    @Override
    public List<BoardDTO> getAllBoards() throws Exception {
        List<Board> boards = repository.findAll();
        List<BoardDTO> dtos = new ArrayList<>();
        for (Board board : boards) {
            dtos.add(toDTO(board));
        }
        return dtos;
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) throws Exception {
        Board board = toEntity(boardDTO);
        repository.update(board);
    }

    @Override
    public void deleteBoard(int id) throws Exception {
        repository.delete(id);
    }

    @Override
    public List<BoardDTO> getBoardsByUserId(int userId) throws Exception {
        List<Board> boards = repository.findByUserId(userId);
        List<BoardDTO> dtos = new ArrayList<>();
        for (Board board : boards) {
            dtos.add(toDTO(board));
        }
        return dtos;
    }
    
    
}
