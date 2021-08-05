package one.maistrenko.shop.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("product-service")
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao){
        this.productDao = productDao;
    }


    @Override
    public Product createProduct(Product product) throws ParseException {
        if(product.getDescription().length() < 5 || product.getDescription().length() > 50){
            System.out.println("Can't create product. Description is not available");
            log.warn("Can't create product. Description's length is {{}}", product.getDescription().length());
            return null;
        }
        Product helpProduct = productDao.getProductByDescription(product.getDescription());
        if(!(null == helpProduct)){
            System.out.println("Can't create product. Product with equal description already exists.");
            log.warn("Can't create product. This description {{}} already exists", product.getDescription().length());
            return null;
        }
        return productDao.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(product.getDescription().length() < 5 || product.getDescription().length() > 50) {
            System.out.println("Can't update product. Description is not available");
            log.warn("Can't update product. Description's length is {{}}", product.getDescription().length());
            return null;
        }
        Product helpProduct = productDao.getProductByDescription(product.getDescription());
        if(null == helpProduct || helpProduct.getDescription().equals(product.getDescription())){
            return productDao.updateProduct(product);
        } else {
            System.out.println("Can't create product. Product with equal description already exists.");
            log.warn("Can't create product. This description:{{}} already exists", product.getDescription().length());
            return null;
        }
    }

    @Override
    public List<Product> showProducts() {
        return productDao.showProducts();
    }

    @Override
    public void removeProduct(long productId) {
        productDao.removeProduct(productId);
    }

    @Override
    public Product getProduct(long productId) {
        return productDao.getProduct(productId);
    }
}
