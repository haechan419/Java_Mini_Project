package memberBoard.repository;

import java.util.List;

import memberBoard.domain.entity.Board;

public interface BoardRepository {
	void save(Board board) throws Exception; // 게시글 저장

	Board findById(int id) throws Exception; // ID로 게시글 조회

	List<Board> findAll() throws Exception; // 전체 게시글 조회

	void update(Board board) throws Exception; // 게시글 수정

	void delete(int id) throws Exception; // 게시글 삭제

	List<Board> findByUserId(int userId) throws Exception; // 특정 유저 게시글 조회
}
