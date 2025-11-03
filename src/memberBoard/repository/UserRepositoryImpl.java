// ============================================
// UserRepositoryImpl.java - 구현체
// ============================================
package memberBoard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import memberBoard.config.DBConnection;
import memberBoard.domain.entity.Role;
import memberBoard.domain.entity.User;

public class UserRepositoryImpl implements UserRepository {

	private final DBConnection dbConnection;

	public UserRepositoryImpl() {
		this.dbConnection = DBConnection.getInstance();
	}

	@Override
	public void save(User user) {
		if (user.getId() == 0) {
			insert(user);
		} else {
			update(user);
		}
	}

	private void insert(User user) {
		String sql = "INSERT INTO user (username, password, name, phone, email, role, status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = dbConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			setUserParameters(ps, user);

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("사용자 생성 실패, 영향받은 행이 없습니다.");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("사용자 생성 실패, ID를 가져올 수 없습니다.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 저장 실패: " + e.getMessage(), e);
		}
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE user SET password=?, phone=?, email=?, role=?, " + "status=?, updated_at=NOW() WHERE id=?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getRole().name());
			ps.setBoolean(5, user.getStatus());
			ps.setInt(6, user.getId());

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("사용자 수정 실패, 해당 ID가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 수정 실패: " + e.getMessage(), e);
		}
	}

	@Override
	public Optional<User> findById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapToUser(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 조회 실패: " + e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapToUser(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 조회 실패: " + e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM user ORDER BY created_at DESC";
		List<User> users = new ArrayList<>();

		try (Connection conn = dbConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				users.add(mapToUser(rs));
			}
		} catch (SQLException e) {
			throw new RuntimeException("전체 사용자 조회 실패: " + e.getMessage(), e);
		}
		return users;
	}

	@Override
	public void delete(String username) {
		String sql = "DELETE FROM user WHERE username = ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("사용자 삭제 실패, 해당 사용자가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 삭제 실패: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean existsByUsername(String username) {
		String sql = "SELECT COUNT(*) FROM user WHERE username = ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 존재 확인 실패: " + e.getMessage(), e);
		}
		return false;
	}

	@Override
	public Optional<User> findByNameAndEmail(String name, String email) {
		String sql = "SELECT * FROM user WHERE name = ? AND email = ?";

		try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, name);
			ps.setString(2, email);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return Optional.of(mapToUser(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("사용자 조회 실패: " + e.getMessage(), e);
		}
		return Optional.empty();
	}

	// Helper 메서드
	private void setUserParameters(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getName());
		ps.setString(4, user.getPhone());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getRole().name());
		ps.setBoolean(7, user.getStatus());
	}

	private User mapToUser(ResultSet rs) throws SQLException {
		User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"),
				rs.getString("phone"), rs.getString("email"));
		user.setRole(Role.fromString(rs.getString("role")));
		user.setStatus(rs.getBoolean("status"));
		return user;
	}
}
