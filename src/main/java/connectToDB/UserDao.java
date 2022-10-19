package connectToDB;

import connectToDB.toClass.AwsConnectionMaker;
import connectToDB.toClass.ConnectionMaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker){this.connectionMaker=connectionMaker;}

    public void add(User user) {

        try {
            // DB접속 (ex sql workbeanch실행)
            Connection conn = connectionMaker.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");

            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            // Query문 실행
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        Map<String, String> env = System.getenv();

        try {
            // DB접속 (ex sql workbeanch실행)
            Connection conn=connectionMaker.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query문 실행
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("name"));

            rs.close();
            pstmt.close();
            conn.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
