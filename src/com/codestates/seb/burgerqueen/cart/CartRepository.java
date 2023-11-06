package com.codestates.seb.burgerqueen.cart;

import com.codestates.seb.burgerqueen.product.Product;

import java.util.HashMap;
import java.util.Map;

public class CartRepository {
  private static Map<Integer, Product> items = new HashMap<>();

  public Map<Integer, Product> findAll() {
    return items;
  }

  public void addCartItem(Product cartItem) {
    items.put(items.size(), cartItem);
  }

  public void clearCart() {
    items.clear();
  }
}
