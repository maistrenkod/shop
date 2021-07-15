package one.maistrenko.shop.product;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Product {
    private long productId;
    private String description;
}
