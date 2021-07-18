package one.maistrenko.shop.user.basketforuser;

import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.basket.BasketService;
import one.maistrenko.shop.basket.BasketServiceImpl;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class BasketForUserImpl implements BasketForUser {
    private static final Logger logger = LoggerFactory.getLogger(BasketForUserImpl.class);
    private Basket basket;
    private BasketService basketService;

    public BasketForUserImpl(BasketService basketService){
        basket = new Basket();
        this.basketService = basketService;
        this.basketService.createBasket(basket);
    }

    @Override
    public void putInBasket( Product product) {
        ArrayList<Product> helplist = new ArrayList<>();
        helplist.addAll(0, basket.getProductList());
        helplist.add(product);
        basket.setProductList(helplist);
        logger.debug("basket with id {} have list {}", basket.getBasketId(), basket.getProductList());
        logger.info("product {} put in basket with id {}", product, basket.getBasketId());
    }

    @Override
    public void removeFromBasket(Product product) {
        ArrayList<Product> helplist = new ArrayList<>();
        helplist.addAll(0, basket.getProductList());
        if (!helplist.remove(product)) {
            logger.debug("this basket with id {} doesn\'t contain product with id {}",basket.getBasketId(), product.getProductId());
        }
        basket.setProductList(helplist);
        logger.debug("basket {} have list {}", basket.getBasketId(), basket.getProductList());
        logger.info("product {} removed from basket {}", product, basket.getBasketId());
    }

    @Override
    public void showBasket() {
        System.out.println(basket.getProductList());
    }

    @Override
    public Basket getBasket() {
        return Basket.builder()
                .basketId(basket.getBasketId())
                .productList(basket.getProductList())
                .build();
    }


}
