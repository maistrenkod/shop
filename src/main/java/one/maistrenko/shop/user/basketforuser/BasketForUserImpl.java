package one.maistrenko.shop.user.basketforuser;

import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.basket.BasketService;
import one.maistrenko.shop.product.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Slf4j
@Service("basket-for-user")
public class BasketForUserImpl implements BasketForUser {
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
        basketService.updateBasket(basket);
        log.debug("basket with id {} have list {{}}", basket.getBasketId(), basket.getProductList());
        log.info("product {} put in basket with id {{}}", product, basket.getBasketId());
    }

    @Override
    public void removeFromBasket(Product product) {
        ArrayList<Product> helplist = new ArrayList<>();
        helplist.addAll(0, basket.getProductList());
        if (!helplist.remove(product)) {
            log.debug("this basket with id {{}} doesn\'t contain product with id {{}}",basket.getBasketId(), product.getProductId());
        }
        basket.setProductList(helplist);
        basketService.updateBasket(basket);
        log.debug("basket {{}} has list {{}}", basket.getBasketId(), basket.getProductList());
        log.info("product {{}} removed from basket {{}}", product, basket.getBasketId());
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
