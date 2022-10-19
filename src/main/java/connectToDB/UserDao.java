package connectToDB;

import connectToDB.toClass.AwsConnectionMaker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    AwsConnectionMaker awsConnectionMaker;

    public UserDao(AwsConnectionMaker awsConnectionMaker) {
        this.awsConnectionMaker = awsConnectionMaker;
    }

    public void add(User user) {

        try {
            // DB접속 (ex sql workbeanch실행)
            Connection conn = awsConnectionMaker.makeConnection();

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
            Connection conn=awsConnectionMaker.makeConnection();

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

    public static void main(String[] args) {
        UserDao userDao = new UserDao(new AwsConnectionMaker());
        User lala = new User("20", "Lala", "cat");
        userDao.add(lala);
        User user = userDao.findById("6");
        System.out.println(user.getName());

            /*
        List<User> users = new ArrayList<>();
        for(User u : users){
            u.printUserInfo();}*/

    }
}
