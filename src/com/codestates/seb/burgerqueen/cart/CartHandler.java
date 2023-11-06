package com.codestates.seb.burgerqueen.cart;

import com.codestates.seb.burgerqueen.product.Product;
import com.codestates.seb.burgerqueen.product.ProductRepository;
import com.codestates.seb.burgerqueen.product.ProductType;
import com.codestates.seb.burgerqueen.product.SetMenu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CartHandler {
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  public CartHandler(CartRepository cartRepository, ProductRepository productRepository) {
    this.cartRepository = cartRepository;
    this.productRepository = productRepository;
  }


  // 장바구니에 있는 모든 상품 조회
  public Map<Integer, Product> getAllCartItems() {
    return cartRepository.findAll();
  }

  // 장바구니에 상품 추가
  public void addProductToCart(Product cartItem) {
    // 여기에 세트메뉴 구성할 수 있는 기능 구현 필요함.
    if(cartItem.getType() == ProductType.SET) {
      SetMenu setItem = (SetMenu) cartItem;
      // 사이드 메뉴 보여줌
      Product selectSide = selectSideMenu();
        // 사이드 메뉴 선택 후, 추가
      setItem.addMenu(selectSide);

      // 음료 메뉴 보여줌
      Product selectedDrink = selectDrinkMenu();
        // 음료 메뉴 선택 후, 추가
      setItem.addMenu(selectedDrink);
      // 사이드가 모두 선택되었으므로, 상품 장바구니로 이동
      cartRepository.addCartItem(setItem);
    } else {
      cartRepository.addCartItem(cartItem);
    }
  }

  private Product selectSideMenu() {
    List<Product> sides = productRepository.getAllProducts().stream()
        .filter(p -> p.getType() == ProductType.SIDE)
        .collect(Collectors.toList());

    System.out.println("사이드 메뉴를 선택해주세요:");
    for (Product side : sides) {
      System.out.printf("%d. %s\n", side.getId(), side.getName());
    }

    Scanner scanner = new Scanner(System.in);
    int selectedId = scanner.nextInt();

    return sides.stream()
        .filter(p -> p.getId() == selectedId)
        .findFirst()
        .orElse(null); // 선택한 ID가 없는 경우 null 반환
  }

  private Product selectDrinkMenu() {
    List<Product> drinks = productRepository.getAllProducts().stream()
        .filter(p -> p.getType() == ProductType.DRINK)
        .collect(Collectors.toList());

    System.out.println("음료를 선택해주세요:");
    for (Product drink : drinks) {
      System.out.printf("%d. %s\n", drink.getId(), drink.getName());
    }

    Scanner scanner = new Scanner(System.in);
    int selectedId = scanner.nextInt();

    return drinks.stream()
        .filter(p -> p.getId() == selectedId)
        .findFirst()
        .orElse(null); // 선택한 ID가 없는 경우 null 반환
  }


  // 장바구니 비우기
  public void emptyCart() {
    cartRepository.clearCart();
  }

  public int calculateTotalPrice() {
    Map<Integer, Product> cartItems = cartRepository.findAll();

    return cartItems.values().stream()
        .mapToInt(item -> (int) item.getPrice())
        .sum();
  }
}