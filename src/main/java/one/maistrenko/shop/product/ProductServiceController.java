package one.maistrenko.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@Service("product-service-controller")
@Slf4j
public class ProductServiceController {
    private final ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public @ResponseBody Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(method =  RequestMethod.GET, value = "{id:\\d+}")
    public @ResponseBody Product getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id:\\d+}")
    public void removeProduct(@PathVariable long id){
        productService.removeProduct(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<Long, Product> showProducts(){
        return productService.showProducts();
    }
}
