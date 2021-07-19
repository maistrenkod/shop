package one.maistrenko.shop.basket;

import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.idGenerator.IdGenerator;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("basket-dao")
public class BasketDaoImpl implements BasketDao {
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
        log.info("Basket {{}} was created", baskets.get(id));
        return basket;
    }

    @Override
    public Basket updateBasket(Basket basket) {
        Basket helpBasket = baskets.get(basket.getBasketId());
        if(null == helpBasket){
            log.warn("Update basket with id {{}} was failed: basket not exists", basket.getBasketId());
            throw new RuntimeException("There is no basket with id =" + basket.getBasketId());
        }
        helpBasket.setProductList(basket.getProductList());
        return basket;
    }

    @Override
    public void removeBasket(long basketId) {
        if (baskets.containsKey(basketId)){
            baskets.remove(basketId);
            log.info("basket {{}} successfully removed", basketId);
        } else{
            log.warn("Removing basket with id {{}} was failed : basket not exists", basketId);
            throw new RuntimeException("There is no basket with id=" + basketId);
        }
    }

    @Override
    public void showAllBaskets() {
        for (Basket i: baskets.values()) {
            System.out.println(i);
        }
    }

    @Override
    public Basket getBasket(long basketId) {
        Basket helpBasket = baskets.get(basketId);
        if(null == helpBasket){
            log.warn("Get basket with id {{}} was failed: basket not exists", basketId);
            throw new RuntimeException("There is no basket with id =" + basketId);
        }
        return Basket.builder()
                .basketId(basketId)
                .productList(helpBasket.getProductList())
                .build();
    }
}
