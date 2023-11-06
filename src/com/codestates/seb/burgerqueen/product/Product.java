package com.codestates.seb.burgerqueen.product;

public interface Product {
  int getId();
  String getName();
  int getKcal();
  int getPrice();
  ProductType getType();
}
