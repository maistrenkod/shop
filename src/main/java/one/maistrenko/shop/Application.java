package one.maistrenko.shop;

import one.maistrenko.shop.basket.Basket;
import one.maistrenko.shop.idGenerator.IdGenerator;
import one.maistrenko.shop.idGenerator.IdGeneratorImpl;
import one.maistrenko.shop.product.ProductService;
import one.maistrenko.shop.product.ProductServiceImpl;
import one.maistrenko.shop.user.UserService;
import one.maistrenko.shop.user.UserServiceImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        IdGenerator generator = new IdGeneratorImpl();
        UserService userService = new UserServiceImpl(generator);
        ProductService productService = new ProductServiceImpl(generator);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter command: ");
            switch(scanner.nextLine()){
                case "create-user":
                    String line = scanner.nextLine();
                    System.out.println(line);
                    break;
                case  "update-user":
                    break;
                case "remove-user":
                    break;
                case "show-users":
                    break;
                case "create-product":
                    break;
                case "update-product":
                    break;
                case "remove-product":
                    break;
                case "show-products":
                    break;
                case "put-in-basket":
                    break;
                case "remove-from-basket":
                    break;
                case "show-basket":
                    break;
                case "create-basket":
                    break;
                case "remove-basket":
                    break;
                case "show-all-baskets":
                    break;
                default:
                    throw new RuntimeException("This command not exists");

            }
        }
    }
}
