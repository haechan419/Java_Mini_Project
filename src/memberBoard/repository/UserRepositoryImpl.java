package memberBoard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import memberBoard.config.DBConnection;
import memberBoard.domain.entity.User;

public class UserRepositoryImpl implements UserRepository {

	@Override
	public void save(User user) {
		if (user.getId() == 0) {
			insertUser(user);
		} else {
			updateUser(user);
		}
	}

	private void insertUser(User user) {
		String sql = "INSERT INTO user (username, password, name, phone, email, role, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getRole());
			ps.setBoolean(7, user.isStatus());

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
			e.printStackTrace();
			throw new RuntimeException("사용자 저장 실패: " + e.getMessage(), e);
		}
	}

	private void updateUser(User user) {
		String sql = "UPDATE user SET password=?, phone=?, email=?, role=?, status=?, updated_at=NOW() WHERE id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getRole());
			ps.setBoolean(5, user.isStatus());
			ps.setInt(6, user.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("사용자 수정 실패: " + e.getMessage(), e);
		}
	}

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapToUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(String username) {
		String sql = "DELETE FROM user WHERE username = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM user";
		List<User> users = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				users.add(mapToUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	private User mapToUser(ResultSet rs) throws SQLException {
		User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("name"),
				rs.getString("phone"), rs.getString("email"));
		user.setRole(rs.getString("role"));
		user.setStatus(rs.getBoolean("status"));
		return user;
	}
}
