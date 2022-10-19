package connectToDB.toClass;

import connectToDB.UserDao;

public class UserDaoFactory {
    public UserDao awsUserDao(){
        UserDao userDao=new UserDao(new AwsConnectionMaker());
        return userDao;
    }

}


