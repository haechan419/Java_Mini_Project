// ============================================
// DBConnection.java - 싱글톤 패턴 적용
// ============================================
package memberBoard.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "3306";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    // 싱글톤 인스턴스
    private static DBConnection instance;
    
    // private 생성자로 외부 생성 방지
    private DBConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
        }
    }
    
    // 싱글톤 인스턴스 반환
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    
    // Connection 객체 반환
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}