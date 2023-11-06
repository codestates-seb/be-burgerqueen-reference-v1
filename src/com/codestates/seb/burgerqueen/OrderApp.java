package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.cart.CartHandler;
import com.codestates.seb.burgerqueen.interaction.UIManager;
import com.codestates.seb.burgerqueen.order.OrderHandler;
import com.codestates.seb.burgerqueen.product.Product;
import com.codestates.seb.burgerqueen.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    private ProductRepository productRepository;
    private UIManager uiManager;
    private OrderHandler orderHandler;
    private CartHandler cartHandler;

    public OrderApp(ProductRepository productRepository, UIManager uiManager, OrderHandler orderHandler, CartHandler cartHandler) {
        this.productRepository = productRepository;
        this.uiManager = uiManager;
        this.orderHandler = orderHandler;
        this.cartHandler = cartHandler;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("🍔 BurgerQueen Order Service");

        while (true) {
            uiManager.printMenu();
            String input = scanner.nextLine();

            if (input.equals("+")) {
                orderHandler.makeOrder();
                break;
            }
            else {
                int menuNumber = Integer.parseInt(input);

                if (menuNumber == 0) uiManager.printCart();
                else if (1 <= menuNumber && menuNumber <= productRepository.getAllProducts().size()) {
                    Product isSelectedMenu = productRepository.findById(menuNumber);
                    cartHandler.addProductToCart(isSelectedMenu);
                }
            }
        }
    }
}
