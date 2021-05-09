package shared;

//Import statement
import java.time.LocalDate;

/**
 * Project      : health_tracker
 * File         : Goal.java
 * Last Edit    : 07/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores a Weight goal associated with a definitive date
 */

public class Goal {
    private final Weight weight;
    private final LocalDate date;

    //Constructor (weight goal)
    public Goal(Weight weight, LocalDate date){
        this.weight = weight;
        this.date = date;
    }

    //Getters
    public LocalDate getDate() {
        return date;
    }
    public Weight getWeight() {
        return weight;
    }
}
