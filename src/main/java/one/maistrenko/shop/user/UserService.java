package one.maistrenko.shop.user;

import one.maistrenko.shop.product.Product;

import java.util.List;
import java.util.Map;

public interface UserService {
    void removeUser(long userId);
    Map<Long, User> showUsers();
    User createUser(User user);
    User updateUser(User user);
//    void putInBasket(long userId, Product product);
//    void removeFromBasket (long userId, Product product);
//    List<Product> showBasket(long userId);
    User getUserById(long userId);
    User getUserByName(String username);

}
