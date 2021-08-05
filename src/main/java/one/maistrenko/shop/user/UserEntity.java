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
@Entity
@Table(name ="users", schema = "basic")
public class UserEntity {

    @Id
    @Column(name = "userid", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userid;

    @Column(name = "username", columnDefinition = "varchar(400)", nullable = false, unique = true)
    private String username;

    @Column(name = "password", columnDefinition = "varchar(400)", nullable = false)
    private String password;

    @Column(name = "basketId")
    private long basketId;
}