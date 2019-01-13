package pastaPackage;

import java.util.ArrayList;

public class Variance {

    public Variance(){
    }

    private ImportJSON jsonForCalcs = new ImportJSON();
    private ImportProjectJSON jsonPDataForCalcs = new ImportProjectJSON();


    private int week;
    private int year;

    public double actualSpent(int week){
        double salaries = plannedSpent(week);
        int additionalCosts = jsonPDataForCalcs.getAdditionalCost(week/2);
        return  salaries;
    }

    public double plannedSpent(int weeks){
        int allSalaries = jsonForCalcs.getTeamSalaries();
        allSalaries = allSalaries * (weeks/calculateTotalWeeks());
        return allSalaries;
    }

    public int calculateTotalWeeks(){
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
            totalWeeks = (WEEKS_IN_YEAR - firstWeek) + lastWeek;
            totalWeeks += (lastYear - firstYear -1 ) * 52;
        }

        return totalWeeks +1;
    }

    /*
    public int getCurrentWeek(int week) {
        int totalWeeks = calculateTotalWeeks();
        int firstWeek = jsonPDataForCalcs.getStartWeek();
        int lastWeek = jsonPDataForCalcs.getEndWeek();
        int firstYear = jsonPDataForCalcs.getStartYear();
        int lastYear = jsonPDataForCalcs.getEndYear();

        // get current week and year from the user
        int currentYear = 0;
        if(! ((lastWeek > firstWeek && (lastYear - firstYear) == 0) ||
                (lastWeek < firstWeek && (lastYear - firstYear) == 1) )) {
            currentYear = this.year;
        }
        int currentWeek = this.week;
        if ( (currentYear - firstYear) != 0) {
            currentWeek += (currentYear - firstYear) * 52;
        }
        return currentWeek;
    }
*/

    public double calculateEV(int currentWeek){

        //int currentWeek = getCurrentWeek();
        int totalWeeks = calculateTotalWeeks();

        //calculate % completion
        double completion = currentWeek/totalWeeks;

        //calculate budget
        double projectBudget = jsonPDataForCalcs.getBudget();
        double EV = projectBudget * completion;

        return EV;
    }

    public double getEV(int weeks) throws Exception{
        jsonPDataForCalcs.addProjectInformation(jsonPDataForCalcs.getJsonPData());
        return calculateEV(weeks);
    }

    public ArrayList<Double> getCV(){
        ArrayList <Double> CV = new ArrayList<>();
        for ( int i = 0; i <= calculateTotalWeeks(); i = i+2){
            CV.add(actualSpent(i));
            CV.add(calculateEV(i));
        }
        return CV;
    }

    public ArrayList<Double> getSV()throws Exception{

        jsonPDataForCalcs.addProjectInformation(jsonPDataForCalcs.getJsonPData());
        jsonForCalcs.addTeamMembers(jsonForCalcs.getJsonArray());


        ArrayList <Double> SV = new ArrayList<>();
        for ( int i = 0; i <= calculateTotalWeeks(); i = i+2){
            SV.add(plannedSpent(i));
            SV.add(calculateEV(i));
        }
        System.out.println(SV);
        return SV;
    }

    public int getWeeks()throws Exception{
        jsonPDataForCalcs.addProjectInformation(jsonPDataForCalcs.getJsonPData());
        jsonForCalcs.addTeamMembers(jsonForCalcs.getJsonArray());
        return calculateTotalWeeks();
    }
    /*
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
    */

}
