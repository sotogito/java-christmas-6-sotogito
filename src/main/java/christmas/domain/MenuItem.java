package christmas.domain;

import java.util.List;

public class MenuItem {
    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
