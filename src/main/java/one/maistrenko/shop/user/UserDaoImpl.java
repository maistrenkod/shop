package one.maistrenko.shop.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@ToString
@Slf4j
@Service("user-dao")
public class UserDaoImpl implements UserDao {
    private Map<Long,User> users = new HashMap<>();
    private IdGenerator idGenerator;

    public UserDaoImpl(IdGenerator generator){
        idGenerator = generator;
    }

    @Override
    public void removeUser(long userId) {
        if (users.containsKey(userId)){
            users.remove(userId);
            log.info("user with id {{}} successfully removed", userId);
        } else{
            log.warn("Removing user with id {{}} was failed : user not exists", userId);
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
        log.info("User {{}} was created", users.get(id));
        return user;
    }

    @Override
    public User updateUser(User user) {
        User helpUser = users.get(user.getUserid());
        if(null == helpUser){
            log.warn("Update user with id {{}} was failed: user not exists", user.getUserid());
            throw new RuntimeException("There is no user with id =" + user.getUserid());
        }
        helpUser.setUserid(user.getUserid());
        helpUser.setUsername(user.getUsername());
        helpUser.setPassword(user.getPassword());
        //helpUser.setBasket(user.getBasket());
        log.info("User {{}} was updated", helpUser);
        return user;
    }

    @Override
    public void putInBasket(long userId, Product product) {
        User helpUser = users.get(userId);
        if(null == helpUser){
            log.warn("Putting in basket user with id {{}} was failed: user not exists", userId);
            throw new RuntimeException("There is no user with id =" + userId);
        }
        helpUser.getBasket().putInBasket(product);
        log.info("User with id {{}} put in basket product {{}}", userId, product);
        log.debug("User with id {{}} have in basket {{}}", users.get(userId).getUserid(), users.get(userId).getBasket().getBasket());
    }

    @Override
    public void removeFromBasket(long userId, Product product) {
        User helpUser = users.get(userId);
        if(null == helpUser){
            log.warn("Removing from basket user with id {{}} was failed: user not exists", userId);
            throw new RuntimeException("There is no user with id =" + userId);
        }
        helpUser.getBasket().removeFromBasket(product);
        log.debug("User with id {{}} have in basket {{}}", users.get(userId).getUserid(), users.get(userId).getBasket().getBasket());

    }

    @Override
    public void showBasket(long userId) {
        User helpUser = users.get(userId);
        if(null == helpUser){
            log.warn("Removing from basket user with id {{}} was failed: user not exists", userId);
            throw new RuntimeException("There is no user with id =" + userId);
        }
        helpUser.getBasket().showBasket();
    }

    @Override
    public User getUserById(long userId) {
        User helpuser = users.get(userId);
        if(null == helpuser){
            log.warn("Get user with id {{}} was failed: product not exists", userId);
            throw new RuntimeException("User with id = "+ userId + " not exists");
        }
        return User.builder()
                .userid(userId)
                .username(helpuser.getUsername())
                .password(helpuser.getPassword())
                .basket(helpuser.getBasket())
                .build();
    }

    @Override
    public User getUserByName(String username) {
        for (User i:users.values()) {
            if(username.equals(i.getUsername())){
                log.info("Username {{}} is not available", username);
                return getUserById(i.getUserid());
            }
        }
        log.info("Username {{}} is available", username);
        return null;
    }
}
