package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/basket")
@Service("basket-service-controller")
public class BasketServiceController {
    private final BasketService basketService;

    public BasketServiceController(BasketService basketService){
        this.basketService = basketService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Basket createBasket(@RequestBody Basket basket){
        return basketService.createBasket(basket);
    }

    @RequestMapping(method = RequestMethod.POST, value = "update")
    public @ResponseBody Basket updateBasket(@RequestBody Basket basket){
        return basketService.updateBasket(basket);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{basketId:\\d+}")
    public @ResponseBody Basket getBasket(@PathVariable long basketId){
        return basketService.getBasket(basketId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<Long, Basket> showAllBaskets(){
        return basketService.showAllBaskets();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/put/{basketId:\\d+}")
    public void putInBasket(@PathVariable long basketId, @RequestBody Product product){
        basketService.putInBasket(basketId, product);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/remove/{basketId:\\d+}")
    public void removeFromBasket(@PathVariable long basketId, @RequestBody Product product){
        basketService.removeFromBasket(basketId, product);
    }

}
