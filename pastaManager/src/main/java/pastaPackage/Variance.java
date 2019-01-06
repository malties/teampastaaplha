package pastaPackage;

public class Variance {
    private double earnedValue;
    private double scheduleVariance;
    private double costVariance;
    private static final int TOTAL_BUDGET = 1134840;
    private static final int PLANNED_SPEND = 1100000;
    private static final int ACTUAL_SPEND = 1200000;


    public Variance(double earnedValue, double scheduleVariance, double costVariance) {
        this.earnedValue = earnedValue;
        this.scheduleVariance = scheduleVariance;
        this.costVariance = costVariance;

    }

    public double calculateEV(double percentage){
        this.earnedValue = TOTAL_BUDGET * (percentage/100);
        return this.earnedValue;
    }

    public void calculateSV(){
     this.scheduleVariance = earnedValue - PLANNED_SPEND;
    }

    public void calculateCV(){
        this.costVariance = earnedValue - ACTUAL_SPEND;


    }
}
