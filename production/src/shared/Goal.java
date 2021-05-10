package shared;

/**
 * Project      : health_tracker
 * File         : Goal.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores a Weight goal, to be expanded to other goals
 *                  at a later date (eg. Exercise for x time)
 */

public class Goal {
    private final Weight weight;

    //Constructor (weight goal)
    public Goal(Weight weight){
        this.weight = weight;
    }

    //Getters
    public Weight getWeight() {
        return weight;
    }

    //Test harness not required
}
