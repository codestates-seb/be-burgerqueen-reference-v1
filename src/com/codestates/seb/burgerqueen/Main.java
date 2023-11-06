package com.codestates.seb.burgerqueen;


import com.codestates.seb.burgerqueen.cart.CartHandler;
import com.codestates.seb.burgerqueen.interaction.UIManager;
import com.codestates.seb.burgerqueen.order.OrderHandler;
import com.codestates.seb.burgerqueen.product.ProductRepository;

public class Main {
    public static void main(String[] args) {

        AppConfigurer appConfigurer = new AppConfigurer();

        OrderApp orderApp = new OrderApp(
                appConfigurer.productRepository(),
                appConfigurer.uiManager(),
                appConfigurer.orderHandler(),
                appConfigurer.cartHandler()
        );

        orderApp.start();
    }
}