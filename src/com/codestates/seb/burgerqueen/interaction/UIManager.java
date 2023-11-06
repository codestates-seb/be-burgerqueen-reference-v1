package com.codestates.seb.burgerqueen.interaction;

import com.codestates.seb.burgerqueen.cart.CartHandler;
import com.codestates.seb.burgerqueen.product.Product;
import com.codestates.seb.burgerqueen.product.ProductRepository;
import com.codestates.seb.burgerqueen.product.ProductType;
import com.codestates.seb.burgerqueen.product.SetMenu;


import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UIManager {

  private ProductRepository productRepository;
  private CartHandler cartHandler;

  public UIManager(ProductRepository productRepository, CartHandler cartHandler) {
    this.productRepository = productRepository;
    this.cartHandler = cartHandler;
  }

  public void printMenu() {
    List<Product> menuItemList = productRepository.getAllProducts();
    System.out.println("[🔻] 메뉴");
    System.out.println("-".repeat(60));


    for (ProductType type : ProductType.values()) {
      displayProductsByType(type, menuItemList);
    }

    System.out.println();
    System.out.println("🧺 (0) 장바구니");
    System.out.println("📦 (+) 주문하기");
    System.out.println("-".repeat(60));
    System.out.print("[📣] 메뉴를 선택해주세요 : ");
  }

  private void displayProductsByType(ProductType type, List<Product> products) {
    System.out.println(type.name());
    products.stream()
        .filter(product -> product.getType() == type)
        .forEach(product -> printEachMenu(product, true));
  }


  private static void printEachMenu(Product product, boolean printPrice) {
    if (printPrice) System.out.printf("   (%d) %s %5dKcal %5d원\n", product.getId(), product.getName(), product.getKcal(), product.getPrice());
    else System.out.printf("   (%d) %s %5dKcal\n", product.getId(), product.getName(), product.getKcal());
  }

  public void printCart() {
    Map<Integer, Product> cartItems = cartHandler.getAllCartItems();
    int totalPrice = cartHandler.calculateTotalPrice();
    System.out.println("🧺 장바구니");
    System.out.println("-".repeat(60));
    cartItems.forEach((key, item) -> displayCartItem(item));
    System.out.println("-".repeat(60));
    System.out.printf("합계 : %d원\n", totalPrice);

    System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine().trim();
  }

  public void displayCartItem(Product product) {
    if (product.getType() == ProductType.SET) {
      SetMenu setMenu = (SetMenu) product;
      String sideItems = getSideItemsAsString(setMenu);
      System.out.printf("%s %d원 (%s)\n", product.getName(), product.getPrice(), sideItems);
    } else {
      System.out.printf("%s %d원\n", product.getName(), product.getPrice());
    }
  }

  private String getSideItemsAsString(SetMenu setMenu) {
    return setMenu.getItems().stream()
        .map(Product::getName)
        .collect(Collectors.joining(", "));
  }
}
