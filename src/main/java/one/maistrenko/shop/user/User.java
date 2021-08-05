package one.maistrenko.shop.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {

    private long userid;
    private String username;
    private String password;
    private long basketId;
}