package one.maistrenko.shop.basket;

import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasketDaoImpl implements BasketDao {
    private static final Logger logger = LoggerFactory.getLogger(BasketDaoImpl.class);
    private Map<Long, Basket> baskets = new HashMap<>();
    private IdGenerator idGenerator;

    public BasketDaoImpl(IdGenerator generator){
        idGenerator = generator;
    }


    @Override
    public Basket createBasket(Basket basket) {
        long id = idGenerator.generateId();
        Basket helpBasket = Basket.builder()
                .basketId(id)
                .productList(basket.getProductList())
                .build();
        baskets.put(id,helpBasket);
        basket.setBasketId(id);
        logger.info("Basket {} was created", baskets.get(id));
        return basket;
    }

    @Override
    public void removeBasket(long basketId) {
        if (baskets.containsKey(basketId)){
            baskets.remove(basketId);
            logger.info("basket {} successfully removed", basketId);
        } else{
            logger.warn("Removing basket with id {} was failed : basket not exists", basketId);
            throw new RuntimeException("There is no basket with id=" + basketId);
        }
    }

    @Override
    public void showAllBaskets() {
        for (Basket i: baskets.values()) {
            System.out.println(i);
        }
    }
}
