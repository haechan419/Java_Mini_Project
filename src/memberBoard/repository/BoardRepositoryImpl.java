// ============================================
// BoardRepositoryImpl.java - 구현체
// ============================================
package memberBoard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import memberBoard.config.DBConnection;
import memberBoard.domain.entity.Board;

public class BoardRepositoryImpl implements BoardRepository {
    
    private final DBConnection dbConnection;
    
    public BoardRepositoryImpl() {
        this.dbConnection = DBConnection.getInstance();
    }
    
    @Override
    public void save(Board board) {
        String sql = "INSERT INTO board (title, content, user_id) VALUES (?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, 
                 Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setInt(3, board.getUserId());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("게시글 생성 실패, 영향받은 행이 없습니다.");
            }
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    board.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("게시글 저장 실패: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<Board> findById(int id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToBoard(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("게시글 조회 실패: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
    
    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM board ORDER BY created_at DESC";
        List<Board> boards = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                boards.add(mapToBoard(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("전체 게시글 조회 실패: " + e.getMessage(), e);
        }
        return boards;
    }
    
    @Override
    public List<Board> findByUserId(int userId) {
        String sql = "SELECT * FROM board WHERE user_id = ? ORDER BY created_at DESC";
        List<Board> boards = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    boards.add(mapToBoard(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("사용자 게시글 조회 실패: " + e.getMessage(), e);
        }
        return boards;
    }
    
    @Override
    public void update(Board board) {
        String sql = "UPDATE board SET title = ?, content = ?, " +
                     "updated_at = NOW() WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setInt(3, board.getId());
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("게시글 수정 실패, 해당 ID가 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("게시글 수정 실패: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM board WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("게시글 삭제 실패, 해당 게시글이 존재하지 않습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("게시글 삭제 실패: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM board WHERE id = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("게시글 존재 확인 실패: " + e.getMessage(), e);
        }
        return false;
    }
    
    private Board mapToBoard(ResultSet rs) throws SQLException {
        return new Board(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getInt("user_id")
        );
    }
}