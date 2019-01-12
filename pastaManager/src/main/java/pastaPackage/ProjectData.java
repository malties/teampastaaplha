package pastaPackage;

public class ProjectData {

    private String PName;
    private String PId;
    private int startWeek;
    private int endWeek;
    private int startYear;
    private int endYear;
    private double budget;
    private int additionalCostQ1;
    private int additionalCostQ2;
    private int additionalCostQ3;
    private int additionalCostQ4;


    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() { return endYear; }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public double getBudget() { return budget; }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getAdditionalCostQ1(){ return additionalCostQ1; }

    public void setAdditionalCostQ1(int additionalCostQ1){ this.additionalCostQ1 = additionalCostQ1; }

    public int getAdditionalCostQ2(){ return additionalCostQ2; }

    public void setAdditionalCostQ2(int additionalCostQ2){ this.additionalCostQ1 = additionalCostQ2; }

    public int getAdditionalCostQ3(){ return additionalCostQ3; }

    public void setAdditionalCostQ3(int additionalCostQ3){ this.additionalCostQ3 = additionalCostQ3; }

    public int getAdditionalCostQ4(){ return additionalCostQ4; }

    public void setAdditionalCostQ4(int additionalCostQ4){ this.additionalCostQ4 = additionalCostQ4; }
}
