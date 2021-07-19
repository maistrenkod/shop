package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;

public interface BasketDao {
    Basket createBasket(Basket basket);
    Basket updateBasket(Basket basket);
    void removeBasket(long basketId);
    void showAllBaskets();
    Basket getBasket(long basketId);
}
