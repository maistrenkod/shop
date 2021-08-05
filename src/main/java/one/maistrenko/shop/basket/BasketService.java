package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface BasketService {
    Basket createBasket(Basket basket) throws ParseException;

    void removeBasket(long basketId);

    Basket updateBasket(Basket basket) throws ParseException;

    List<Basket> showAllBaskets();

    Basket getBasket(long basketId);

    void putInBasket(long basketId, Product product) throws ParseException;

    void removeFromBasket(long basketId, Product product) throws ParseException;

    List<Product> showBasket(long basketId);
}
