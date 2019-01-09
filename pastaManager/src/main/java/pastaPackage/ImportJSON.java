package pastaPackage;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ImportJSON {

    private ArrayList <TeamMemberData> teamMembers;

    private static final String JSON_FILE = ClassLoader.getSystemResource("TeamMemberData.json").getFile();

    public ImportJSON() {
        this.teamMembers = new ArrayList<>();
    }
    
    // Parses the JSON file and returns an array of team members
    public JsonArray getJsonArray() throws Exception {


        Gson gson = new Gson();
        File file = new File(JSON_FILE);

        JsonReader br = new JsonReader(new FileReader(file));

        JsonObject jsonObject = gson.fromJson(br, JsonObject.class);
        JsonArray teamMembersJsonArray = jsonObject.getAsJsonArray("teamMembers");

        br.close();
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
            String jsonProject = jsonObject.get("project").getAsString();
            newTeamMember.setProject(jsonProject);
            int jsonHoursPerWeek = jsonObject.get("hoursPerWeek").getAsInt();
            newTeamMember.setHoursPerWeek(jsonHoursPerWeek);
            int jsonWeeks = jsonObject.get("weeks").getAsInt();
            newTeamMember.setWeeks(jsonWeeks);

            // Adds each team member to an array list
            teamMembers.add(newTeamMember);
        }
        printTeamMemberData();
    }

    public TeamMemberData createTeamMember() {
        TeamMemberData newTeamMember = new TeamMemberData();
        return newTeamMember;
    }

    public String printTeamMemberData() {
        String data = "";
        String newLine = System.getProperty("line.separator");
        if(!teamMembers.isEmpty()) {
            for (TeamMemberData teamMember : teamMembers) {

                data += "Team member name: " + teamMember.getName() + newLine +
                        "Member ID number: " + teamMember.getUid() + newLine +
                        "Salary (per hour): SEK " + teamMember.getSalaryPerHour() + newLine +
                        "Current project: " + teamMember.getProject() + newLine +
                        "Work hours (per week): " + teamMember.getHoursPerWeek() + newLine +
                        "Number of working weeks: " + teamMember.getWeeks() + newLine + newLine;
            }
        }else{
            data = "No team members";
        }
        return data;
    }

    public int getSumOFSalaries(){
        int sum = 0;
        if(!teamMembers.isEmpty()){
            for(TeamMemberData teamMember : teamMembers){
                sum += teamMember.getSalaryPerHour();
            }
        }
        return sum;
    }

    public String printProjectData(){
        String data;
        String newLine = System.getProperty("line.separator");
        int hoursPerWeek = 0;
        int totalHours = 0;
        int actualCostPerWeek = 0;
        int actualCost = 0;
        if(!teamMembers.isEmpty()){
            for (TeamMemberData teamMember: teamMembers) {
                hoursPerWeek += teamMember.getHoursPerWeek();
                totalHours += teamMember.getHoursPerWeek() * teamMember.getWeeks();
                actualCostPerWeek += teamMember.getHoursPerWeek() * teamMember.getSalaryPerHour();
                actualCost += teamMember.getHoursPerWeek() * teamMember.getSalaryPerHour() * teamMember.getWeeks();
            }
            data =  "Project name: " + teamMembers.get(1).getProject() + newLine +
                    "Number of members: " + teamMembers.size() + newLine +
                    "Total project hours (per week): " + hoursPerWeek + newLine +
                    "Total project hours: " + totalHours + newLine +
                    "Actual Cost of project (per week): " + actualCostPerWeek + " sek" + newLine +
                    "Actual Cost of project: " + actualCost + " sek";
        }else{
            data = "No projects";
        }
        return data;
    }

    public String search(int id) {
        id--;
        String info;
        String newLine = System.getProperty("line.separator");
        try{
            if(!teamMembers.isEmpty()) {
                info = "Team member name: " + teamMembers.get(id).getName() + newLine +
                        "Member ID number: " + teamMembers.get(id).getUid() + newLine +
                        "Salary (per hour): SEK " + teamMembers.get(id).getSalaryPerHour() + newLine +
                        "Current project: " + teamMembers.get(id).getProject() + newLine +
                        "Work hours (per week): " + teamMembers.get(id).getHoursPerWeek();
            }else{
                return "No member found.";
            }
        }catch(Exception e){
            return "No members found.";
        }

        if(info == ""){
            return "No members found.";
        }
        return info;

    }


}