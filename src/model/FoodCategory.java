package model;

import model.exceptions.CategoryException;

public enum FoodCategory {
    DAIRY,
    MEAT,
    VEGETABLE,
    FRUIT,
    GRAIN,
    SWEETS,
    OTHER;

    public static FoodCategory parseCategory(String category) throws CategoryException{
        for (FoodCategory foodCategory : FoodCategory.values()) {
            if (category.equalsIgnoreCase(foodCategory.name())){
                return foodCategory;
            }
        }

        throw new CategoryException();
    }
}
