package one.maistrenko.shop.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.basket.BasketService;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
@Slf4j
@Service("user-dao")
public class UserDaoImpl implements UserDao {
    private final Map<Long,User> users = new HashMap<>();
    private IdGenerator idGenerator;
    private final BasketService basketService;

    public UserDaoImpl(IdGenerator generator, BasketService basketService){
        idGenerator = generator;
        this.basketService = basketService;
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
    public Map<Long, User> showUsers() {
        for (User i: users.values()) {
            System.out.println(i);
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        long id = idGenerator.generateId();
        basketService.getBasket(user.getBasketId());
        User helpUser = User.builder()
                .userid(id)
                .username(user.getUsername())
                .password(user.getPassword())
                .basketId(user.getBasketId())
                .role(user.getRole())
                .build();
        users.put(id,helpUser);
        user.setUserid(id);
        log.info("User {{}} was created", users.get(id));
        return user;
    }

    @Override
    public User updateUser(User user) {
        User helpUser = users.get(user.getUserid());
        basketService.getBasket(user.getBasketId());
        if(null == helpUser){
            log.warn("Update user with id {{}} was failed: user not exists", user.getUserid());
            throw new RuntimeException("There is no user with id =" + user.getUserid());
        }
        helpUser.setUserid(user.getUserid());
        helpUser.setUsername(user.getUsername());
        helpUser.setPassword(user.getPassword());
        helpUser.setBasketId(user.getBasketId());
        helpUser.setRole(user.getRole());
        log.info("User {{}} was updated", helpUser);
        return user;
    }

//    @Override
//    public void putInBasket(long userId, Product product) {
//        User helpUser = users.get(userId);
//        if(null == helpUser){
//            log.warn("Putting in basket user with id {{}} was failed: user not exists", userId);
//            throw new RuntimeException("There is no user with id =" + userId);
//        }
//        basketService.putInBasket(helpUser.getBasketId(), product);
//        log.info("User with id {{}} put in basket product {{}}", userId, product);
//        log.debug("User with id {{}} have in basket {{}}", users.get(userId).getUserid(), basketService.showBasket(users.get(userId).getBasketId()));
//    }
//
//    @Override
//    public void removeFromBasket(long userId, Product product) {
//        User helpUser = users.get(userId);
//        if(null == helpUser){
//            log.warn("Removing from basket user with id {{}} was failed: user not exists", userId);
//            throw new RuntimeException("There is no user with id =" + userId);
//        }
//        basketService.removeFromBasket(helpUser.getBasketId(), product);
//        log.debug("User with id {{}} have in basket {{}}", users.get(userId).getUserid(), basketService.showBasket(users.get(userId).getBasketId()));
//
//    }
//
//    @Override
//    public List<Product> showBasket(long userId) {
//        User helpUser = users.get(userId);
//        if(null == helpUser){
//            log.warn("Showing basket of user with id {{}} was failed: user not exists", userId);
//            throw new RuntimeException("There is no user with id =" + userId);
//        }
//        return basketService.showBasket(helpUser.getBasketId());
//    }

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
                .basketId(helpuser.getBasketId())
                .role(helpuser.getRole())
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
