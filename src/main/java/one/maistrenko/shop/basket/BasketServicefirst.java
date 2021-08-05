package one.maistrenko.shop.basket;


import one.maistrenko.shop.product.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface BasketServicefirst {
    Basket createBasket(Basket basket) throws ParseException;
    Basket updateBasket(Basket basket) throws ParseException;
    void removeBasket(long basketId);
    List<Basket> showAllBaskets();
    Basket getBasket(long basketId);
    void putInBasket(long basketId, Product product) throws ParseException;
    void removeFromBasket(long basketId, Product product) throws ParseException;
    List<Product> showBasket(long basketId);
}
