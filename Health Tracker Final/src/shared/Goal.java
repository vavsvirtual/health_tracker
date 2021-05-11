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
    private Weight weightGoal;
    private Exercise exerciseGoal;

    //Constructor (weight goal)
    public Goal(Weight weightGoal){
        this.weightGoal = weightGoal;
    }
    //Constructor (exercise goal)
    public Goal(Exercise exerciseGoal){
        this.exerciseGoal = exerciseGoal;
    }

    //Weight goal getter
    public Weight getWeightGoal() {
        return weightGoal;
    }
    //Exercise goal getter
    public Exercise getExerciseGoal() {
        return exerciseGoal;
    }

    //Test harness not required
}
