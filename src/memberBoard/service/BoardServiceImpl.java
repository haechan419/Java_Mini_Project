// ============================================
// BoardServiceImpl.java - 구현체
// ============================================
package memberBoard.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import memberBoard.domain.dto.BoardDTO;
import memberBoard.domain.entity.Board;
import memberBoard.exception.BoardException;
import memberBoard.repository.BoardRepository;
import memberBoard.validator.BoardValidator;

public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final BoardValidator boardValidator;

	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
		this.boardValidator = new BoardValidator();
	}

	@Override
	public void createBoard(BoardDTO boardDTO) throws BoardException {
		// 유효성 검증
		boardValidator.validateBoard(boardDTO);

		// DTO -> Entity 변환
		Board board = toEntity(boardDTO);

		// 저장
		boardRepository.save(board);
	}

	@Override
	public BoardDTO getBoardById(int id) throws BoardException {
		Optional<Board> boardOpt = boardRepository.findById(id);

		if (!boardOpt.isPresent()) {
			throw new BoardException("존재하지 않는 게시글입니다.");
		}

		return toDTO(boardOpt.get());
	}

	@Override
	public List<BoardDTO> getAllBoards() {
		List<Board> boards = boardRepository.findAll();
		return boards.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Override
	public List<BoardDTO> getBoardsByUserId(int userId) {
		List<Board> boards = boardRepository.findByUserId(userId);
		return boards.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) throws BoardException {
		// 게시글 존재 확인
		if (!boardRepository.existsById(boardDTO.getId())) {
			throw new BoardException("존재하지 않는 게시글입니다.");
		}

		// 유효성 검증
		boardValidator.validateBoard(boardDTO);

		// DTO -> Entity 변환 및 업데이트
		Board board = toEntity(boardDTO);
		boardRepository.update(board);
	}

	@Override
	public void deleteBoard(int id) throws BoardException {
		// 게시글 존재 확인
		if (!boardRepository.existsById(id)) {
			throw new BoardException("존재하지 않는 게시글입니다.");
		}

		boardRepository.delete(id);
	}

	@Override
	public void deleteBoardByUser(int id, int userId) throws BoardException {
		// 게시글 조회
		Optional<Board> boardOpt = boardRepository.findById(id);

		if (!boardOpt.isPresent()) {
			throw new BoardException("존재하지 않는 게시글입니다.");
		}

		Board board = boardOpt.get();

		// 작성자 확인
		if (!board.isOwnedBy(userId)) {
			throw new BoardException("본인 게시글만 삭제할 수 있습니다.");
		}

		boardRepository.delete(id);
	}

	// Entity <-> DTO 변환 메서드
	private Board toEntity(BoardDTO dto) {
		return new Board(dto.getId(), dto.getTitle(), dto.getContent(), dto.getUserId());
	}

	private BoardDTO toDTO(Board board) {
		return new BoardDTO(board.getId(), board.getTitle(), board.getContent(), board.getUserId(),
				board.getCreatedAt(), board.getUpdatedAt());
	}
}