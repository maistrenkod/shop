package one.maistrenko.shop.basket;

import lombok.*;

import one.maistrenko.shop.product.Product;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
public class Basket {
    private long basketId;
    private List<Product> productList = new ArrayList<>();
}
