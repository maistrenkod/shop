package one.maistrenko.shop.product;

import one.maistrenko.shop.product.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product createProduct(Product product) throws ParseException;
    Product updateProduct(Product product);
    List<Product> showProducts();
    void removeProduct(long productId);
    Product getProduct(long productId);
}
