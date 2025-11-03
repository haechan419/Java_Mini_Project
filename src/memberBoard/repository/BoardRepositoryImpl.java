package memberBoard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import memberBoard.config.DBConnection;
import memberBoard.domain.entity.Board;

public class BoardRepositoryImpl implements BoardRepository {

	@Override
	public void save(Board board) throws Exception {
		String sql = "INSERT INTO board (title, content, user_id) VALUES (?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getUserId());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					board.updateTimestamp(); // 수정일 갱신
				}
			}
		}
	}

	@Override
	public Board findById(int id) throws Exception {
		String sql = "SELECT * FROM board WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapToBoard(rs);
				}
			}
		}
		return null;
	}

	@Override
	public List<Board> findAll() throws Exception {
		String sql = "SELECT * FROM board ORDER BY created_at DESC";
		List<Board> boards = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				boards.add(mapToBoard(rs));
			}
		}
		return boards;
	}

	@Override
	public void update(Board board) throws Exception {
		String sql = "UPDATE board SET title = ?, content = ?, updated_at = NOW() WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getId());
			ps.executeUpdate();
			board.updateTimestamp();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		String sql = "DELETE FROM board WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public List<Board> findByUserId(int userId) throws Exception {
		String sql = "SELECT * FROM board WHERE user_id = ? ORDER BY created_at DESC";
		List<Board> boards = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					boards.add(mapToBoard(rs));
				}
			}
		}
		return boards;
	}

	// ResultSet → Board 변환 메서드
	private Board mapToBoard(ResultSet rs) throws SQLException {
		return new Board(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getInt("user_id"));
	}

}
