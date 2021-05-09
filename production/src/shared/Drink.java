package shared;

/**
 * Project      : health_tracker
 * File         : Drink.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : An extension of the Food object that represents one serving of a Drink item,
 *                  its name & calories
 */

public class Drink extends Food{
    //Constructor
    public Drink(String name, int caloriesPerPortion) {
        super(name, caloriesPerPortion);
    }
}
