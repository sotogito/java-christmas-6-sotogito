package christmas.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private static Map<Category,List<MenuItem>> menu = new EnumMap<>(Category.class);

    private static List<MenuItem> APPETIZER = new ArrayList<>();
    private static List<MenuItem> MAIN_COURSE = new ArrayList<>();
    private static List<MenuItem> DESSERT = new ArrayList<>();
    private static List<MenuItem> BEVERAGE = new ArrayList<>();


    static {
        initMenuItem();
        menu.put(Category.APPETIZER,APPETIZER);
        menu.put(Category.MAIN_COURSE,MAIN_COURSE);
        menu.put(Category.DESSERT,DESSERT);
        menu.put(Category.BEVERAGE,BEVERAGE);

    }

    private static void initMenuItem(){

        APPETIZER.add(new MenuItem("양송이수프",6000));
        APPETIZER.add(new MenuItem("타파스",5500));
        APPETIZER.add(new MenuItem("시저샐러드",8000));

        MAIN_COURSE.add(new MenuItem("티본스테이크",55000));
        MAIN_COURSE.add((new MenuItem("바비큐립", 54000)));
        MAIN_COURSE.add(new MenuItem("해산물파스타",35000));
        MAIN_COURSE.add(new MenuItem("크리스마스파스타",25000));

        DESSERT.add(new MenuItem("초코케이크",15000));
        DESSERT.add(new MenuItem("아이스크림",5000));

        BEVERAGE.add(new MenuItem("제로콜라",3000));
        BEVERAGE.add(new MenuItem("레드와인",60000));
        BEVERAGE.add(new MenuItem("샴페인",25000));

    }

    public static Map<Category, List<MenuItem>> getMenu() {
        Map<Category, List<MenuItem>> immutableMenu = new EnumMap<>(Category.class);
        for (Map.Entry<Category, List<MenuItem>> entry : menu.entrySet()) {
            immutableMenu.put(entry.getKey(), List.copyOf(entry.getValue()));
        }
        return immutableMenu;
    }


}
