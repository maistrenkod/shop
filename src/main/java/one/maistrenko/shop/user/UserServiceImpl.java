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
    private Map<Long,User> users = new HashMap<>();
    private IdGenerator idGenerator;

    public UserServiceImpl(IdGenerator generator){
        idGenerator = generator;
    }

    @Override
    public void removeUser(long userId) {
        if (users.containsKey(userId)){
            users.remove(userId);
            logger.info("user with id {} successfully removed", userId);
        } else{
            logger.warn("Removing user with id {} was failed : user not exists", userId);
            throw new RuntimeException("There is no user with id=" + userId);
        }
    }

    @Override
    public void showUsers() {
        for (User i: users.values()) {
            System.out.println(i);
        }
    }

    @Override
    public User createUser(User user) {
        long id = idGenerator.generateId();
        User helpUser = User.builder()
                .userid(id)
                .username(user.getUsername())
                .password(user.getPassword())
                .basket(user.getBasket())
                .build();
        users.put(id,helpUser);
        user.setUserid(id);
        logger.info("User {} was created", users.get(id));
        return user;
    }

    @Override
    public User updateUser(User user) {
        User helpUser = users.get(user.getUserid());
        if(null == helpUser){
            logger.warn("Update user with id {} was failed: user not exists", user.getUserid());
            throw new RuntimeException("There is no user with id =" + user.getUserid());
        }
        helpUser.setUserid(user.getUserid());
        helpUser.setUsername(user.getUsername());
        helpUser.setPassword(user.getPassword());
        //helpUser.setBasket(user.getBasket());
        return user;
    }

    @Override
    public void putInBasket(long userId, Product product) {
        User helpUser = users.get(userId);
        if(null == helpUser){
            logger.warn("Putting in basket user with id {} was failed: user not exists", userId);
            throw new RuntimeException("There is no user with id =" + userId);
        }
        helpUser.getBasket().putInBasket(product);
        logger.info("User with id {} put in basket product {}", userId, product);
        //logger.debug("User with id {} have in basket {}", users.get(userId).getUserid(), users.get(userId).getBasket());
    }

    @Override
    public void removeFromBasket(long userId, Product product) {
        User helpUser = users.get(userId);
        if(null == helpUser){
            logger.warn("Removing from basket user with id {} was failed: user not exists", userId);
            throw new RuntimeException("There is no user with id =" + userId);
        }
        helpUser.getBasket().removeFromBasket(product);
    }

    @Override
    public void showBasket(long userId) {
        User helpUser = users.get(userId);
        if(null == helpUser){
            logger.warn("Removing from basket user with id {} was failed: user not exists", userId);
            throw new RuntimeException("There is no user with id =" + userId);
        }
        helpUser.getBasket().showBasket();
    }
}
