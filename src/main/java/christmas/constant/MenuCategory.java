package christmas.constant;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String name;

    private MenuCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MenuCategory nameOf(String name) {
        for (MenuCategory category : values()) {
            if (category.name.equals(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException();
    }
}
