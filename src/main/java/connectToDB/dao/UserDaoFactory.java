package connectToDB.dao;

import connectToDB.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao(){
        UserDao userDao=new UserDao(new AwsConnectionMaker());
        return userDao;
    }

}


