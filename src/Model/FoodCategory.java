package Model;

public enum FoodCategory {
    DAIRY,
    MEAT,
    VEGETABLE,
    FRUIT,
    GRAIN,
    SWEETS,
    OTHER;

    public static FoodCategory convert(String category){
        for (FoodCategory foodCategory : FoodCategory.values()) {
            if (category.equalsIgnoreCase(foodCategory.name())){
                return foodCategory;
            }
        }

        return null;
    }
}
