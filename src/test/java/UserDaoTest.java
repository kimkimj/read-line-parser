import connectToDB.UserDao;
import connectToDB.abstrat.AWSUserDao;
import connectToDB.User;
import connectToDB.abstrat.UserDaoAbstract;
import connectToDB.toClass.AwsConnectionMaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UserDaoTest {
    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        //connectToDB.UserDao userDao = new connectToDB.UserDao();
        UserDao userDao = new UserDao(new AwsConnectionMaker());

        String id = "10"; // to prevent duplicate PK errors
        String name = "TT";

        User user = new User(id,name, "cat");
        userDao.add(user);

        User selectedUser = userDao.findById(id);
        Assertions.assertEquals("Lulu", selectedUser.getName());
        Assertions.assertEquals("cat", selectedUser.getPassword());
    }
}