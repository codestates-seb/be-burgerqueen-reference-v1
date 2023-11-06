package com.codestates.seb.burgerqueen.product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new SingleMenu(1, "새우버거", 3500, 500, ProductType.HAMBURGER));
        products.add(new SingleMenu(2, "치킨버거", 4000, 600, ProductType.HAMBURGER));
        products.add(new SingleMenu(3, "감자튀김", 1000, 300, ProductType.SIDE));
        products.add(new SingleMenu(4, "어니언링", 1000, 300, ProductType.SIDE));
        products.add(new SingleMenu(5, "코카콜라", 1000, 200, ProductType.DRINK));
        products.add(new SingleMenu(6, "제로콜라", 1000, 0, ProductType.DRINK));
        products.add(new SetMenu(7, "새우버거 세트", ProductType.SET, new ArrayList<>() {{
            add(products.get(0)); // 새우버거
        }}));
        products.add(new SetMenu(8, "새우버거 세트", ProductType.SET, new ArrayList<>() {{
            add(products.get(1)); // 새우버거
        }}));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // 원본 리스트의 변경을 방지하기 위해 복사본을 반환
    }

    public Product findById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
