package one.maistrenko.shop.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    public UserServiceImpl(IdGenerator generator){
        userDao = new UserDaoImpl(generator);
    }

    @Override
    public void removeUser(long userId) {
        userDao.removeUser(userId);
    }

    @Override
    public void showUsers() {
        userDao.showUsers();
    }

    @Override
    public User createUser(User user) {
        if(user.getPassword().length() < 6){
            System.out.println("Can't create user. Password is not available");
            logger.warn("Can't create user. User password length is", user.getPassword().length());
            return null;
        }
        User helpUser = userDao.getUserByName(user.getUsername());
        if(!(null == helpUser)){
            System.out.println("Can't create user. This username already exists");
            logger.warn("Can't create user. This username {} already exists", user.getUsername());
            return null;
        }
        return userDao.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        if(user.getPassword().length() < 6){
            System.out.println("Can't updatee user. Password is not available");
            logger.warn("Can't update user. User password's length is", user.getPassword().length());
            return null;
        }
        User helpUser = userDao.getUserByName(user.getUsername());
        if(!(null == helpUser)){
            System.out.println("Can't update user. This username already exists");
            logger.warn("Can't update user. This username {} already exists", user.getUsername());
            return null;
        }
        return userDao.updateUser(user);
    }

    @Override
    public void putInBasket(long userId, Product product) {
        userDao.putInBasket(userId,product);
    }

    @Override
    public void removeFromBasket(long userId, Product product) {
        userDao.removeFromBasket(userId,product);
    }

    @Override
    public void showBasket(long userId) {
        userDao.showBasket(userId);
    }

    @Override
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }
}
