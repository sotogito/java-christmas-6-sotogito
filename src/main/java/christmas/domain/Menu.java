package christmas.domain;

import christmas.enums.Category;
import christmas.view.InputView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private static final Map<Category, List<MenuItem>> menuItemList = new EnumMap<>(Category.class);
    private static final List<MenuItem> APPETIZER = new ArrayList<>();
    private static final List<MenuItem> MAIN_COURSE = new ArrayList<>();
    private static final List<MenuItem> DESSERT = new ArrayList<>();
    private static final List<MenuItem> BEVERAGE = new ArrayList<>();

    static {
        createMenuItem();
        menuItemList.put(Category.APPETIZER, APPETIZER);
        menuItemList.put(Category.MAIN_COURSE, MAIN_COURSE);
        menuItemList.put(Category.DESSERT, DESSERT);
        menuItemList.put(Category.BEVERAGE, BEVERAGE);
    }

    private static void createMenuItem(){
        APPETIZER.add(new MenuItem("양송이수프", 6000));
        APPETIZER.add(new MenuItem("타파스", 5500));
        APPETIZER.add(new MenuItem("시저샐러드", 8000));
        MAIN_COURSE.add(new MenuItem("티본스테이크", 55000));
        MAIN_COURSE.add((new MenuItem("바비큐립", 54000)));
        MAIN_COURSE.add(new MenuItem("해산물파스타", 35000));
        MAIN_COURSE.add(new MenuItem("크리스마스파스타", 25000));
        DESSERT.add(new MenuItem("초코케이크", 15000));
        DESSERT.add(new MenuItem("아이스크림", 5000));
        BEVERAGE.add(new MenuItem("제로콜라", 3000));
        BEVERAGE.add(new MenuItem("레드와인", 60000));
        BEVERAGE.add(new MenuItem("샴페인", 25000));
    }
    public static List<MenuItem> getBeverage(){
        return BEVERAGE;
    }

    public static MenuItem findMenuItem(String orderMenuName) {
        return menuItemList.values().stream()
                .flatMap(List::stream)
                .filter(item -> item.getName().equals(orderMenuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(InputView.ERROR_ORDER_MENU));
    }

    public static int findMenuPrice(MenuItem menuItem) {
        return menuItemList.values().stream()
                .flatMap(List::stream)
                .filter(item -> item.getName().equals(menuItem.getName()))
                .findFirst()
                .map(MenuItem::getPrice)
                .orElseThrow(() -> new IllegalArgumentException(InputView.ERROR_ORDER_MENU));
    }

    public static Category findCategory(MenuItem menuItem) {
        return menuItemList.entrySet().stream()
                .filter(entry -> entry.getValue().contains(menuItem))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MenuItem does not belong to any category"));
    }

}
