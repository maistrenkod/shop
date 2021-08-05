package one.maistrenko.shop.basket;

import one.maistrenko.shop.product.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketServiceController {
    private final BasketService basketService;

    public BasketServiceController(BasketService basketService){
        this.basketService = basketService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Basket createBasket(@RequestBody Basket basket) throws ParseException {
        return basketService.createBasket(basket);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public @ResponseBody Basket updateBasket(@RequestBody Basket basket) throws ParseException {
        return basketService.updateBasket(basket);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{basketId:\\d+}")
    public @ResponseBody Basket getBasket(@PathVariable long basketId){
        return basketService.getBasket(basketId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Basket> showAllBaskets(){
        return basketService.showAllBaskets();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/put/{basketId:\\d+}")
    public void putInBasket(@PathVariable long basketId, @RequestBody Product product) throws ParseException {
        basketService.putInBasket(basketId, product);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/remove/{basketId:\\d+}")
    public void removeFromBasket(@PathVariable long basketId, @RequestBody Product product) throws ParseException {
        basketService.removeFromBasket(basketId, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id:\\d+}")
    public void deleteBasket(@PathVariable long id){
        basketService.removeBasket(id);
    }

}
