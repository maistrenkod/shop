package one.maistrenko.shop.user;

import one.maistrenko.shop.product.Product;

public interface UserService {
    void removeUser(long userId);
    void showUsers();
    User createUser(User user);
    User updateUser(User user);
    void putInBasket(long userId, Product product);
    void removeFromBasket (long userId, Product product);
    void showBasket(long userId);
    User getUserById(long userId);
    User getUserByName(String username);

}
