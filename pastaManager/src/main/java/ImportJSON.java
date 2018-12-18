import com.google.gson.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ImportJSON {

    private ArrayList <TeamMemberData> teamMembers;
    private static final String JSON_FILE = "./src/main/resources/TeamMemberData.json";

    public ImportJSON() {
        this.teamMembers = new ArrayList<>();
    }
    
    // Parses the JSON file and returns an array of team members
    public JsonArray getJsonArray() throws Exception {
        
        Gson gson = new Gson();
        File file = new File(JSON_FILE);
        JsonObject jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
        JsonArray teamMembersJsonArray = jsonObject.getAsJsonArray("teamMembers");
        return teamMembersJsonArray;
        
    }

    // Iterates through the array of team members and extracts the info for each team member
    public void addTeamMembers(JsonArray teamMembersJsonArray) {
        
        for (int i = 0; i < teamMembersJsonArray.size(); i++) {
            
            // Converts each array element into an object
            JsonObject jsonObject = (JsonObject) teamMembersJsonArray.get(i);

            // Creates a new TeamMember object for each team member
            TeamMemberData newTeamMember = createTeamMember();

            // Extracts data for each team member and saves as attributes of the class
            String jsonName = jsonObject.get("name").getAsString();
            newTeamMember.setName(jsonName);
            int jsonUid = jsonObject.get("uid").getAsInt();
            newTeamMember.setUid(jsonUid);
            int jsonSalaryPerHour = jsonObject.get("salaryPerHour").getAsInt();
            newTeamMember.setSalaryPerHour(jsonSalaryPerHour);
            String jsonProject = jsonObject.get("projects").getAsString();
            newTeamMember.setProject(jsonProject);
            int jsonHoursPerWeek = jsonObject.get("hoursPerWeek").getAsInt();
            newTeamMember.setHoursPerWeek(jsonHoursPerWeek);

            // Adds each team member to an array list
            teamMembers.add(newTeamMember);
        }
        printTeamMemberData();
    }

    public TeamMemberData createTeamMember() {
        TeamMemberData newTeamMember = new TeamMemberData();
        return newTeamMember;
    }

    public void printTeamMemberData() {
        for (TeamMemberData teamMember: teamMembers) {

            System.out.println("Team member name: " + teamMember.getName());
            System.out.println("Member ID number: " + teamMember.getUid());
            System.out.println("Salary per hour: " + teamMember.getSalaryPerHour());
            System.out.println("Project name: " + teamMember.getProject());
            System.out.println("Hours per week: " + teamMember.getHoursPerWeek());
            System.out.println();

        }
    }

    public static void main(String[] args) {
        ImportJSON importJSON = new ImportJSON();
        try {
            importJSON.addTeamMembers(importJSON.getJsonArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}