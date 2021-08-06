package one.maistrenko.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductServiceController {
    private final ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Product createProduct(@RequestBody Product product) throws ParseException {
        return productService.createProduct(product);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PATCH)
    public @ResponseBody Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(method =  RequestMethod.GET, value = "{id:\\d+}")
    public @ResponseBody Product getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "{id:\\d+}")
    public void removeProduct(@PathVariable long id){
        productService.removeProduct(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Product> showProducts(){
        return productService.showProducts();
    }
}
