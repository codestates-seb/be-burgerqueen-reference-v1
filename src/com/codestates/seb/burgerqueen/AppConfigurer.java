package com.codestates.seb.burgerqueen;

import com.codestates.seb.burgerqueen.cart.CartHandler;
import com.codestates.seb.burgerqueen.cart.CartRepository;
import com.codestates.seb.burgerqueen.discount.Discount;
import com.codestates.seb.burgerqueen.discount.discountCondition.CozDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.DiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountCondition.KidDiscountCondition;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedAmountDiscountPolicy;
import com.codestates.seb.burgerqueen.discount.discountPolicy.FixedRateDiscountPolicy;
import com.codestates.seb.burgerqueen.interaction.UIManager;
import com.codestates.seb.burgerqueen.order.OrderHandler;
import com.codestates.seb.burgerqueen.product.ProductRepository;

public class AppConfigurer {

//    private Cart cart = new Cart(productRepository(), menu());
    public CartHandler cartHandler() {
        return new CartHandler(cartRepository(), productRepository());
    }
    public CartRepository cartRepository() {
        return new CartRepository();
    }

    public Discount discount() {
        return new Discount(
            new DiscountCondition[]{
                new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
            });
    }

    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    public UIManager uiManager() {
        return new UIManager(productRepository(), cartHandler());
    }

    public OrderHandler orderHandler() {
        return new OrderHandler(cartHandler(), discount(), uiManager());
    }
}
