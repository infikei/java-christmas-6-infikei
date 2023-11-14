package christmas.constant;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int saleSumMinimum;

    private Badge(String name, int saleSumMinimum) {
        this.name = name;
        this.saleSumMinimum = saleSumMinimum;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadgeBySaleSum(int saleSum) {
        for (Badge badge : values()) {
            if (saleSum >= badge.saleSumMinimum) {
                return badge;
            }
        }
        return NONE;
    }
}
