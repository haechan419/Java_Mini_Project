package memberBoard.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "3306";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB 연결 실패: " + e.getMessage());
        }
    }
}
