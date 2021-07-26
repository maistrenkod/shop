package one.maistrenko.shop.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@ToString
@EqualsAndHashCode
@Slf4j
@Service("user-service")
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void removeUser(long userId) {
        userDao.removeUser(userId);
    }

    @Override
    public Map<Long, User> showUsers() {
        return userDao.showUsers();
    }

    @Override
    public User createUser(User user) {
        if(user.getPassword().length() < 6){
            System.out.println("Can't create user. Password is not available");
            log.warn("Can't create user. User password length is", user.getPassword().length());
            return null;
        }
        if(user.getUsername().length() < 1){
            System.out.println("Can't create user. Username is not available");
            log.warn("Can't create user. Username is {{}} to short", user.getUsername());
            return null;
        }
        User helpUser = userDao.getUserByName(user.getUsername());
        if(!(null == helpUser)){
            System.out.println("Can't create user. This username already exists");
            log.warn("Can't create user. This username {{}} already exists", user.getUsername());
            return null;
        }
        return userDao.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        if(user.getPassword().length() < 6){
            System.out.println("Can't update user. Password is not available");
            log.warn("Can't update user. User password's length is", user.getPassword().length());
            return null;
        }
        User helpUser = userDao.getUserByName(user.getUsername());
        if(!(null == helpUser)){
            System.out.println("Can't update user. This username already exists");
            log.warn("Can't update user. This username {{}} already exists", user.getUsername());
            return null;
        }
        return userDao.updateUser(user);
    }

//    @Override
//    public void putInBasket(long userId, Product product) {
//        userDao.putInBasket(userId,product);
//    }
//
//    @Override
//    public void removeFromBasket(long userId, Product product) {
//        userDao.removeFromBasket(userId,product);
//    }
//
//    @Override
//    public List<Product> showBasket(long userId) {
//        return userDao.showBasket(userId);
//    }

    @Override
    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }
}
