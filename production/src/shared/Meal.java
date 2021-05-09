package shared;

import java.util.ArrayList;

/**
 * Project      : health_tracker
 * File         : Meal.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores an array of Food/Drink objects
 *                  that make up a whole meal eg. Pasta + Meatballs + Coffee
 */

public class Meal {
    private ArrayList<Food> foodList;
    private ArrayList<Drink> drinkList;
    private MealType mealType;

    public enum MealType{
        BREAKFAST,
        LUNCH,
        DINNER,
        DESERT,
        SNACK;
    }

    //Constructor
    public Meal(ArrayList<Food> foodList, ArrayList<Drink> drinkList){
        this.foodList = foodList;
        this.drinkList = drinkList;
    }
    public Meal(ArrayList<Food> foodList, ArrayList<Drink> drinkList, MealType mealType){
        this(foodList, drinkList);
        this.mealType = mealType;
    }

    //Getters
    public ArrayList<Drink> getDrinkList() {
        return drinkList;
    }
    public MealType getMealType() {
        return mealType;
    }
    public ArrayList<Food> foodList() {
        return foodList;
    }

    //Adders
    public void addFood(Food food){
        foodList.add(food);
    }
    //Adders
    public void addDrink(Drink drink){
        drinkList.add(drink);
    }

}
