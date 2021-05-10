package shared;

/**
 * Project      : health_tracker
 * File         : Weight.java
 * Last Edit    : 09/05/2021
 * PRG Lang     : Java
 * Author(s)    : Team 4.5 | Vav Scott 100287100
 *
 * Description  : Object that stores a Weight in KG, while providing methods
 *                  to assist in data manipulation for health tracking
 */

public class Weight {
    public static final double KG_TO_POUNDS = 2.204623;
    private final double weightKg;

    //Enum to represent the BMI categories
    public enum BMIRank{
        UNDERWEIGHT(Double.MIN_VALUE, 18.5),
        NORMAL(18.5, 25),
        OVERWEIGHT(25,30),
        OBESE(30,40),
        VERY_OBESE(40, Double.MAX_VALUE);

        //BMI bounding values
        private final double lowerBound, upperBound;
        private BMIRank(double lowerBound, double upperBound){
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
        public double getLowerBound() {
            return lowerBound;
        }
        public double getUpperBound() {
            return upperBound;
        }
    }


    //Constructor (metric)
    public Weight(double weightKg){
        this.weightKg = weightKg;
    }
    //Constructor (imperial)
    public Weight(int pounds){
        this(pounds/KG_TO_POUNDS);
    }
    //Constructor (imperial)
    public Weight(int stone, int pounds){
        this((14*stone) + pounds);
    }

    //Getters
    public int getWeightPounds(){
        return Math.toIntExact(Math.round(weightKg * KG_TO_POUNDS));
    }
    public double getWeightKg(){
        return weightKg;
    }

    //Static methods
    //Calculates BMI using the formula (weight(kg)/height^2(m))
    public static double calculateBMI(double weightKg, int heightCm){
        return weightKg/((heightCm/100d)*(heightCm/100d));
    }
    //Evaluates a double into a BMI Rank
    public static BMIRank evalBMI(double bmi){
        BMIRank evalRank = null;
        for(BMIRank rank : BMIRank.values()){
            if(bmi >= rank.getLowerBound() && bmi < rank.getUpperBound()){
                evalRank = rank;
                break;
            }
        }
        return evalRank;
    }

    //Test harness
    public static void main(String[] args) {
        //--------- SETUP ---------
        Weight weight = new Weight(100f);
        //---------TEST A: WEIGHT CLASS TESTS---------
        //conversion test
        boolean successA1 = false;
        if(weight.getWeightPounds() == 220){
            successA1 = true;
        }
        //bmi calc test
        boolean successA2 = false;
        if(calculateBMI(100, 200) == 25){
            successA2 = true;
        }
        //bmi eval test
        boolean successA3 = true;
        if(Weight.evalBMI(9) != BMIRank.UNDERWEIGHT){
            successA3 = false;
        }if(Weight.evalBMI(20) != BMIRank.NORMAL){
            successA3 = false;
        }if(Weight.evalBMI(27.5) != BMIRank.OVERWEIGHT){
            successA3 = false;
        }if(Weight.evalBMI(39.999) != BMIRank.OBESE){
            successA3 = false;
        }if(Weight.evalBMI(40.1) != BMIRank.VERY_OBESE){
            successA3 = false;
        }


        //TEST RESULTS
        System.out.println("---------TEST A: RESULTS---------");
        System.out.println("KG/POUNDS: \t" + (successA1 ? "Pass" : "Fail"));
        System.out.println("BMI CALC: \t" + (successA2 ? "Pass" : "Fail"));
        System.out.println("BMI RANK: \t" + (successA3 ? "Pass" : "Fail"));
    }

}
