package one.maistrenko.shop;

import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.basket.BasketDao;
import one.maistrenko.shop.basket.BasketDaoImpl;
import one.maistrenko.shop.user.basketforuser.BasketForUserImpl;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.idGenerator.IdGeneratorImpl;
import one.maistrenko.shop.product.Product;
import one.maistrenko.shop.product.ProductService;
import one.maistrenko.shop.product.ProductServiceImpl;
import one.maistrenko.shop.user.User;
import one.maistrenko.shop.user.UserService;
import one.maistrenko.shop.user.UserServiceImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        IdGenerator generator = new IdGeneratorImpl();
        UserService userService = new UserServiceImpl(generator);
        ProductService productService = new ProductServiceImpl(generator);
        BasketDao basketDao = new BasketDaoImpl(generator);

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        while (true){
            System.out.println("Enter command: ");
            switch(scanner1.nextLine()){
                case "create-user":
                    createUser(userService, scanner, generator);
                    break;
                case  "update-user":
                    updateUser(userService, scanner, scanner2);
                    break;
                case "remove-user":
                    removeUser(userService, scanner);
                    break;
                case "show-users":
                    userService.showUsers();
                    break;
                case "create-product":
                    createProduct(productService, scanner);
                    break;
                case "update-product":
                    updateProduct(productService, scanner, scanner2);
                    break;
                case "remove-product":
                    removeProduct(productService, scanner);
                    break;
                case "show-products":
                    productService.showProducts();
                    break;
                case "put-in-basket":
                    putInBasket(userService, productService, scanner);
                    break;
                case "remove-from-basket":
                    removeFromBasket(userService, productService, scanner);
                    break;
                case "show-basket":
                    showBasket(userService, scanner);
                    break;
                case "create-basket":
                    basketDao.createBasket(new Basket());
                    break;
                case "remove-basket":
                    removeBasket(basketDao, scanner);
                    break;
                case "show-all-baskets":
                    basketDao.showAllBaskets();
                    break;
                default:
                    System.out.println("This command not exists. Please, try again.");

            }
        }
    }

    public static void createUser(UserService userService, Scanner scanner, IdGenerator generator){
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        userService.createUser(new User(0, username, password, new BasketForUserImpl(generator)));
    }

    public static void updateUser(UserService userService, Scanner scanner, Scanner scanner1){
        System.out.println("Enter user id:");
        long id = scanner.nextLong();
        System.out.println("Enter username:");
        String username = scanner1.nextLine();
        System.out.println("Enter password:");
        String password = scanner1.nextLine();
        userService.updateUser(new User(id, username, password, userService.getUserById(id).getBasket()));
    }

    public static void removeUser(UserService userService, Scanner scanner){
        System.out.println("Enter userId:");
        long id = scanner.nextLong();
        userService.removeUser(id);
    }

    public static void createProduct (ProductService productService, Scanner scanner){
        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        productService.createProduct(new Product(0,description));
    }

    public static void updateProduct (ProductService productService, Scanner scanner, Scanner scanner1){
        System.out.println("Enter product id:");
        long id = scanner.nextLong();
        System.out.println("Enter product description:");
        String description = scanner1.nextLine();
        productService.updateProduct(new Product(id, description));
    }

    public static void removeProduct (ProductService productService, Scanner scanner){
        System.out.println("Enter product id:");
        long id = scanner.nextLong();
        productService.removeProduct(id);
    }

    public static void putInBasket(UserService userService, ProductService productService, Scanner scanner){
        System.out.println("Enter user id:");
        long userId = scanner.nextLong();
        System.out.println("Enter product id:");
        long productId = scanner.nextLong();
        userService.putInBasket(userId, productService.getProduct(productId));
    }

    public static void showBasket(UserService userService, Scanner scanner){
        System.out.println("Enter user id:");
        long id = scanner.nextLong();
        userService.showBasket(id);
    }

    public static void removeFromBasket(UserService userService, ProductService productService, Scanner scanner){
        System.out.println("Enter user id:");
        long userId = scanner.nextLong();
        System.out.println("Enter product id:");
        long productId = scanner.nextLong();
        userService.removeFromBasket(userId, productService.getProduct(productId));
    }

    public  static void removeBasket(BasketDao basketDao, Scanner scanner){
        System.out.println("Enter basket id:");
        long id = scanner.nextLong();
        basketDao.removeBasket(id);
    }
}
