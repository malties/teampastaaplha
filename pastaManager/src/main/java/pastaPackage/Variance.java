package pastaPackage;

public class Variance {

    private ImportJSON jsonForCalcs = new ImportJSON();


    public double actualSpent(){
        double additionalCosts = UserInput.readDouble("Please add unplanned additional costs here");
        int salaries = jsonForCalcs.getTeamSalaries();
        return additionalCosts + salaries;
    }

    public int plannedSpent(){
        return jsonForCalcs.getTeamSalaries();
    }

    public void calculateEV(){
        //TODO: Import the project completion from a JSON file.
    }

    public void calculateSV(){
        //NEEDS EV + actual spent to complete
    }

    public void calculateCV(){
        //NEEDS EV + actual spent to complete
    }


}
