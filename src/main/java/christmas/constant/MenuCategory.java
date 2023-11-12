package christmas.constant;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String name;

    private MenuCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MenuCategory nameOf(String name) {
        for (MenuCategory category : MenuCategory.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }
}
