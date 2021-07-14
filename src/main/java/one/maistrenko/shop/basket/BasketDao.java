package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;

public interface BasketDao {
    public Basket createBasket(Basket basket);
    public void removeBasket(long basketId);
    public void showAllBaskets();
}
