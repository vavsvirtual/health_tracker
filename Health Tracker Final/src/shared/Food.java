package shared;

//Import statements
import java.io.Serializable;

/**
 * Project      : health_tracker
 * File         : Food.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that represents one serving of a Food item,
 *                  its name & calories
 */

public class Food implements Serializable {
    private static final long serialVersionUID = 9147349L;

    //Class variables
    private final String name;
    private final int caloriesPerPortion;

    //Constructor
    public Food(String name, int caloriesPerPortion){
        this.name = name;
        this.caloriesPerPortion = caloriesPerPortion;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getCaloriesPerPortion() {
        return caloriesPerPortion;
    }

    //Test Harness not required
}
