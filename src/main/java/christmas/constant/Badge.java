package christmas.constant;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int price_minimum;

    private Badge(String name, int price_minimum) {
        this.name = name;
        this.price_minimum = price_minimum;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadgeByPrice(int price) {
        for (Badge badge : values()) {
            if (price >= badge.price_minimum) {
                return badge;
            }
        }
        return NONE;
    }
}
