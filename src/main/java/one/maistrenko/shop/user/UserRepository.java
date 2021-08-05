package one.maistrenko.shop.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    void deleteByUserid(long userid);
    List<UserEntity> findAll();
    UserEntity findUserByUserid(long userid);
    UserEntity findUserByUsername(String username);

    @Modifying
    @Query("update UserEntity u set u.username = ?1, u.password = ?2, u.basketId = ?3 where u.userid = ?4")
    void updateUser(String username, String password, long basketId, long Userid);
}