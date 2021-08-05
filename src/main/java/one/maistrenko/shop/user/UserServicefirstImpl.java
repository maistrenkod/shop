package one.maistrenko.shop.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import one.maistrenko.shop.basket.BasketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Slf4j
@Service("user-dao")
public class UserServicefirstImpl implements UserServicefirst {
//    private final Map<Long,User> users = new HashMap<>();
//    @Autowired
//    private IdGenerator idGenerator;
    @Autowired
    private BasketService basketService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

//    public UserDaoImpl(IdGenerator generator, BasketService basketService){
//        idGenerator = generator;
//        this.basketService = basketService;
//    }

    @Override
    public void removeUser(long userId) {
        if(null != userRepository.findUserByUserid(userId)){
            userRepository.deleteByUserid(userId);
            log.info("user with id {{}} successfully removed", userId);
        } else{
            log.warn("Removing user with id {{}} was failed : user not exists", userId);
            throw new RuntimeException("There is no user with id=" + userId);
        }
    }

    @Override
    public List<User> showUsers() {
        List<UserEntity> list = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            users.add(convertToDto(list.get(i)));
        }
        log.info("All users are shown");
       return users;
//        for (User i: users.values()) {
//            System.out.println(i);
//        }
//        return users;
    }

    @Override
    public User createUser(User user) throws ParseException {
//        long id = idGenerator.generateId();
        basketService.getBasket(user.getBasketId());
        User helpUser = User.builder()
                .userid(1)
                .username(user.getUsername())
                .password(user.getPassword())
                .basketId(user.getBasketId())
//                .role(user.getRole())
                .build();
        userRepository.saveAndFlush(convertToEntity(helpUser));
//        users.put(id,helpUser);
        user.setUserid(userRepository.findUserByUsername(user.getUsername()).getUserid());
        log.info("User {{}} was created", helpUser);
        return user;
    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = userRepository.findUserByUserid(user.getUserid());
        basketService.getBasket(user.getBasketId());
        if (null == userEntity) {
            log.warn("Update user with id {{}} was failed: user not exists", user.getUserid());
            throw new RuntimeException("There is no user with id =" + user.getUserid());
        }
//        UserEntity userEntity1 =
        userRepository.updateUser(user.getUsername(), user.getPassword(),
                user.getBasketId(), user.getUserid());
//        User helpUser = new User();
//        helpUser.setUserid(user.getUserid());
//        helpUser.setUsername(user.getUsername());
//        helpUser.setPassword(user.getPassword());
//        helpUser.setBasketId(user.getBasketId());
////        helpUser.setRole(user.getRole());
        log.info("User {{}} was updated", user);
        return user;
    }

//    @Override
//    public void putInBasket(long userId, Product product) {
//        User helpUser = users.get(userId);
//        if(null == helpUser){
//            log.warn("Putting in basket user with id {{}} was failed: user not exists", userId);
//            throw new RuntimeException("There is no user with id =" + userId);
//        }
//        basketService.putInBasket(helpUser.getBasketId(), product);
//        log.info("User with id {{}} put in basket product {{}}", userId, product);
//        log.debug("User with id {{}} have in basket {{}}", users.get(userId).getUserid(), basketService.showBasket(users.get(userId).getBasketId()));
//    }
//
//    @Override
//    public void removeFromBasket(long userId, Product product) {
//        User helpUser = users.get(userId);
//        if(null == helpUser){
//            log.warn("Removing from basket user with id {{}} was failed: user not exists", userId);
//            throw new RuntimeException("There is no user with id =" + userId);
//        }
//        basketService.removeFromBasket(helpUser.getBasketId(), product);
//        log.debug("User with id {{}} have in basket {{}}", users.get(userId).getUserid(), basketService.showBasket(users.get(userId).getBasketId()));
//
//    }
//
//    @Override
//    public List<Product> showBasket(long userId) {
//        User helpUser = users.get(userId);
//        if(null == helpUser){
//            log.warn("Showing basket of user with id {{}} was failed: user not exists", userId);
//            throw new RuntimeException("There is no user with id =" + userId);
//        }
//        return basketService.showBasket(helpUser.getBasketId());
//    }

    @Override
    public User getUserById(long userId) {
        UserEntity userEntity = userRepository.findUserByUserid(userId);
//        User helpuser = users.get(userId);
        if(null == userEntity){
            log.warn("Get user with id {{}} was failed: product not exists", userId);
            throw new RuntimeException("User with id = "+ userId + " not exists");
        }
        return convertToDto(userEntity);
//        return User.builder()
//                .userid(userId)
//                .username(helpuser.getUsername())
//                .password(helpuser.getPassword())
//                .basketId(helpuser.getBasketId())
////                .role(helpuser.getRole())
//                .build();
    }

    @Override
    public User getUserByName(String username) {
        UserEntity userEntity = userRepository.findUserByUsername(username);
        if(null == userEntity){
            log.info("Username {{}} is available", username);
            return null;
        } else{
            log.info("Username {{}} is not available", username);
            return convertToDto(userEntity);
        }
//        for (User i:users.values()) {
//            if(username.equals(i.getUsername())){
//                log.info("Username {{}} is not available", username);
//                return getUserById(i.getUserid());
//            }
//        }
//        log.info("Username {{}} is available", username);
//        return null;
    }

    private User convertToDto(UserEntity userEntity) {
        User user = modelMapper.map(userEntity, User.class);
        return user;
    }

    private UserEntity convertToEntity(User user) throws ParseException {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        return userEntity;
    }
}
