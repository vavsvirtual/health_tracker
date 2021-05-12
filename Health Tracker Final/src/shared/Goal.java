package shared;

import java.io.Serializable;

/**
 * Project      : health_tracker
 * File         : Goal.java
 * Last Edit    : 12/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores a Weight goal, to be expanded to other goals
 *                  at a later date (eg. Exercise for x time)
 */

public class Goal implements Serializable {
    private static final long serialVersionUID = 9043212L;
    private boolean expired;
    private boolean goalMet;
    private boolean gainingWeight;
    private Weight weightGoal;
    private Exercise exerciseGoal;

    //Constructor (weight goal)
    public Goal(Weight weightGoal, boolean gainingWeight){
        this.weightGoal = weightGoal;
        this.gainingWeight = gainingWeight;
    }
    //Constructor (exercise goal)
    public Goal(Exercise exerciseGoal){
        this.exerciseGoal = exerciseGoal;
    }
    @Override
    public String toString(){
        String stringForm = "";
        if(weightGoal != null){
            stringForm = "Target weight of " + weightGoal;
        }else{
            stringForm = exerciseGoal.toString();
        }
        return stringForm;
    }

    //Weight goal getter
    public Weight getWeightGoal() {
        return weightGoal;
    }
    //Exercise goal getter
    public Exercise getExerciseGoal() {
        return exerciseGoal;
    }
    //Goal expired getter
    public boolean getExpired() {
        return expired;
    }
    //Goal met getter
    public boolean getGoalMet() {
        return goalMet;
    }
    //Goal gainingWeight getter
    public boolean getGainingWeight() {
        return gainingWeight;
    }

    //Goal expired setter
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    //Goal met setter
    public void setGoalMet(boolean goalMet) {
        this.goalMet = goalMet;
    }

    //Test harness not required
}
