package christmas.constant;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, "애피타이저"),
    TAPAS("타파스", 5_500, "애피타이저"),
    CAESAR_SALAD("시저샐러드", 8_000, "애피타이저"),
    T_BONE_STEAK("티본스테이크", 55_000, "메인"),
    BBQ_RIBS("바비큐립", 54_000, "메인"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "메인"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "메인"),
    CHOCO_CAKE("초코케이크", 15_000, "디저트"),
    ICECREAM("아이스크림", 5_000, "디저트"),
    ZERO_COKE("제로콜라", 3_000, "음료"),
    RED_WINE("레드와인", 60_000, "음료"),
    CHAMPAGNE("샴페인", 25_000, "음료");

    private final String name;
    private final int price;
    private final MenuCategory category;

    private Menu(String name, int price, String categoryName) {
        this.name = name;
        this.price = price;
        this.category = MenuCategory.nameOf(categoryName);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return this.category;
    }

    public static Menu nameOf(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException();
    }
}
