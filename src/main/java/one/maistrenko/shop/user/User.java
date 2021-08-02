package one.maistrenko.shop.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private long userid;
    private String username;
    private String password;
    private Role role;
    private long basketId;
}
enum Role{ADMIN, USER}