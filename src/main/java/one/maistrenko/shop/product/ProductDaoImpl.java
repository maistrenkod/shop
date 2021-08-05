package one.maistrenko.shop.product;

import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.user.User;
import one.maistrenko.shop.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("product-dao")
public class ProductDaoImpl implements ProductDao {

//    @Autowired
//    private IdGenerator idGenerator;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
//    public ProductDaoImpl(IdGenerator generator){
//        idGenerator = generator;
//    }

    @Override
    public Product createProduct(Product product) throws ParseException {
//        long id = idGenerator.generateId();
        Product helpProduct = Product.builder()
                .productId(1)
                .description(product.getDescription())
                .build();
        productRepository.saveAndFlush(convertToEntity(helpProduct));
//        products.put(id,helpProduct);
        product.setProductId(productRepository.findProductEntityByDescription(product.getDescription()).getProductId());
        log.info("Product {{}} was created", product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity productEntity = productRepository.findProductEntityByProductId(product.getProductId());
//        Product helpProduct = products.get(product.getProductId());
        if(null == productEntity){
            log.warn("Update product with id {{}} was failed: product not exists", product.getProductId());
            throw new RuntimeException("There is no product with id =" + product.getProductId());
        }
//        helpProduct.setProductId(product.getProductId());
//        helpProduct.setDescription(product.getDescription());
        productRepository.updateProduct(product.getDescription(), product.getProductId());
        log.info("Product {{}} was updated", product);
        return product;
    }

    public List<Product> showProducts(){
        List<ProductEntity> list = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            products.add(convertToDto(list.get(i)));
        }
        log.info("All products are shown");
        return products;
    }

    public void removeProduct(long productId){
        if(null != productRepository.findProductEntityByProductId(productId)){
            productRepository.deleteByProductId(productId);
//            productRepository.deleteProductEntityByProductId(productId);
//            productRepository.removeByProductId(productId);

            log.info("product with id {{}} successfully removed", productId);
        } else{
            log.warn("Removing product with id {{}} was failed : product not exists", productId);
            throw new RuntimeException("There is no product with id=" + productId);
        }
    }

    @Override
    public Product getProduct(long productId) {
        ProductEntity productEntity = productRepository.findProductEntityByProductId(productId);
        if(null == productEntity){
            log.warn("Get product with id {{}} was failed: product not exists", productId);
            throw new RuntimeException("Product with id =" + productId + "not exists");
        }
//        return Product.builder()
//                .productId(productId)
//                .description(helpProduct.getDescription())
//                .build();
        return convertToDto(productEntity);
    }

    @Override
    public Product getProductByDescription(String description) {
        ProductEntity productEntity = productRepository.findProductEntityByDescription(description);
        if(null == productEntity){
            log.info("Description {{}} is available", description);
            return null;
        } else{
            log.info("Description {{}} is not available", description);
            return convertToDto(productEntity);
        }
//        for (Product i: products.values()) {
//            if (description.equals(i.getDescription())){
//                log.info("Product description {{}} is not available", description);
//                return getProduct(i.getProductId());
//            }
//        }
//        log.info("Product description: {{}} is available", description);
//        return null;
    }

    public Product convertToDto(ProductEntity productEntity) {
        Product product = modelMapper.map(productEntity, Product.class);
        return product;
    }

    public ProductEntity convertToEntity(Product product) throws ParseException {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        return productEntity;
    }
}
