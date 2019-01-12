package pastaPackage;

public class Variance {

    private ImportJSON jsonForCalcs = new ImportJSON();
    private ImportProjectJSON jsonPDataForCalcs = new ImportProjectJSON();

    public double actualSpent(){
        double additionalCosts = UserInput.readDouble("Please add unplanned additional costs here");
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
            currentYear = UserInput.readInt("What Year is this?");
        }
        int currentWeek = UserInput.readInt("What week is this?");
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

    public double calculateCV(){
        return  calculateEV() + actualSpent();
    }


}
