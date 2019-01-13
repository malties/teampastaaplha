package pastaPackage;

public class Variance {

    public Variance(int week, int year){
        this.week = week;
        this.year = year;
    }

    private ImportJSON jsonForCalcs = new ImportJSON();
    private ImportProjectJSON jsonPDataForCalcs = new ImportProjectJSON();
    private int week;
    private int year;

    public double actualSpent(int additionalCosts){
        int salaries = jsonForCalcs.getTeamSalaries();
        return additionalCosts + salaries;
    }

    public int plannedSpent(){
        return jsonForCalcs.getTeamSalaries();
    }

    public double calculateEV(){

        // calculate total number of weeks difference from start to end of project
        int firstWeek = jsonPDataForCalcs.getStartWeek();
        int lastWeek = jsonPDataForCalcs.getEndWeek();
        int firstYear = jsonPDataForCalcs.getStartYear();
        int lastYear = jsonPDataForCalcs.getEndYear();
        final int WEEKS_IN_YEAR = 52;
        int totalWeeks;
        if (lastWeek > firstWeek) {
            totalWeeks = lastWeek - firstWeek;
            totalWeeks += (lastYear - firstYear) * 52;
        } else {
            totalWeeks = (firstWeek - WEEKS_IN_YEAR) + lastWeek;
            totalWeeks += (lastYear - firstYear -1 ) * 52;
        }

        // get current week and year from the user
        int currentYear = 0;
        if(! ((lastWeek > firstWeek && (lastYear - firstYear) == 0) ||
                (lastWeek < firstWeek && (lastYear - firstYear) == 1) )) {
            currentYear = year;
        }
        int currentWeek = week;
        if ( (currentYear - firstYear) != 0) {
            currentWeek += (currentYear - firstYear) * 52;
        }

        //calculate % completion
        double completion = currentWeek/totalWeeks;

        //calculate budget
        double projectBudget = jsonPDataForCalcs.getBudget();
        double EV = projectBudget * completion;

        return EV;
    }

    public double calculateSV(){
        return calculateEV() + plannedSpent();
    }

    // quartile should be 1 to 4
    public double calculateCV(int quartile){
        if(quartile == 1){
            return  calculateEV() + actualSpent(jsonPDataForCalcs.getAdditionalCostQ1());
        }else if(quartile == 2){
            return  calculateEV() + actualSpent(jsonPDataForCalcs.getAdditionalCostQ1() + jsonPDataForCalcs.getAdditionalCostQ2());
        }else if(quartile == 3){
            return  calculateEV() + actualSpent(jsonPDataForCalcs.getAdditionalCostQ1() + jsonPDataForCalcs.getAdditionalCostQ2() + jsonPDataForCalcs.getAdditionalCostQ3());
        }else if(quartile == 4){
            return  calculateEV() + actualSpent(jsonPDataForCalcs.getAdditionalCostQ1() + jsonPDataForCalcs.getAdditionalCostQ2() + jsonPDataForCalcs.getAdditionalCostQ3() + jsonPDataForCalcs.getAdditionalCostQ4());
        }
        return 0.0;
    }


}
