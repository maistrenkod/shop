package one.maistrenko.shop.product;

import one.maistrenko.shop.product.Product;

public interface ProductService {
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void showProducts();
    public void removeProduct(long productId);
}
