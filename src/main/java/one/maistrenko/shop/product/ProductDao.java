package one.maistrenko.shop.product;

public interface ProductDao {
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void showProducts();
    public void removeProduct(long productId);
    public Product getProduct(long productId);
    public Product getProductByDescription(String description);
}
