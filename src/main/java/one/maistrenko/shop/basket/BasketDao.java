package one.maistrenko.shop.basket;


import one.maistrenko.shop.product.Product;

import java.util.List;
import java.util.Map;

public interface BasketDao {
    Basket createBasket(Basket basket);
    Basket updateBasket(Basket basket);
    void removeBasket(long basketId);
    Map<Long, Basket> showAllBaskets();
    Basket getBasket(long basketId);
    void putInBasket(long basketId, Product product);
    void removeFromBasket(long basketId, Product product);
    List<Product> showBasket(long basketId);
}
