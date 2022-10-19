import connectToDB.UserDao;
import domain.User;
import connectToDB.dao.UserDaoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        //String id = "12"; // to prevent duplicate PK errors

        UserDao userDao = context.getBean("awsUserDao",UserDao.class);
        userDao.add(new User("14", "mimi", "cat"));

        User selectedUser = userDao.findById("14");
        Assertions.assertEquals("mimi", selectedUser.getName());
        Assertions.assertEquals("cat", selectedUser.getPassword());
    }
}