package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;

import java.util.List;
import java.util.Map;

public interface BasketService {
    Basket createBasket(Basket basket);

    void removeBasket(long basketId);

    Basket updateBasket(Basket basket);

    Map<Long, Basket> showAllBaskets();

    Basket getBasket(long basketId);

    void putInBasket(long basketId, Product product);

    void removeFromBasket(long basketId, Product product);

    List<Product> showBasket(long basketId);
}
