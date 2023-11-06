package com.codestates.seb.burgerqueen.order;

import com.codestates.seb.burgerqueen.cart.CartHandler;
import com.codestates.seb.burgerqueen.discount.Discount;
import com.codestates.seb.burgerqueen.interaction.UIManager;
import com.codestates.seb.burgerqueen.product.Product;

import java.util.Map;

public class OrderHandler {
//  private Cart cart;
//  private Discount discount;
//
//  public Order(Cart cart, Discount discount) {
//    this.cart = cart;
//    this.discount = discount;
//  }

  private final CartHandler cartHandler;
  private final Discount discount;
  private final UIManager uiManager;

  public OrderHandler(CartHandler cartHandler, Discount discount, UIManager uiManager) {
    this.cartHandler = cartHandler;
    this.discount = discount;
    this.uiManager = uiManager;
  }

  public void makeOrder() {

    discount.checkAllDiscountConditions();

    int totalPrice = cartHandler.calculateTotalPrice();
    int finalPrice = discount.discount(totalPrice);

    Map<Integer, Product> cartProductList = cartHandler.getAllCartItems();
    System.out.println("[📣] 주문이 완료되었습니다. ");
    System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
    System.out.println("-".repeat(60));

//    cart.printCartItemDetails();
    cartProductList.forEach((key, item) -> uiManager.displayCartItem(item));

    System.out.println("-".repeat(60));
    System.out.printf("금액 합계      : %d원\n", totalPrice);
    System.out.printf("할인 적용 금액 : %d원\n", finalPrice);
  }
}
