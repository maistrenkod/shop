package one.maistrenko.shop.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.maistrenko.shop.product.Product;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Basket {
    private long basketId;
    private List<Product> productList = new ArrayList<>();
}
