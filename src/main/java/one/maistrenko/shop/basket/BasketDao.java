package one.maistrenko.shop.basket;


public interface BasketDao {
    Basket createBasket(Basket basket);
    Basket updateBasket(Basket basket);
    void removeBasket(long basketId);
    void showAllBaskets();
    Basket getBasket(long basketId);
}
