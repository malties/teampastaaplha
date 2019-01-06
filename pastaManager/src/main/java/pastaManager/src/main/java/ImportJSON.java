package pastaManager.src.main.java;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

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

    public String printTeamMemberData() {
        String data = "";
        String newLine = System.getProperty("line.separator");
        if(!teamMembers.isEmpty()) {
            for (TeamMemberData teamMember : teamMembers) {

                data += "Team member name: " + teamMember.getName() + newLine +
                        "Member ID number: " + teamMember.getUid() + newLine +
                        "Salary (per hour): SEK " + teamMember.getSalaryPerHour() + newLine +
                        "Current projects: " + teamMember.getProject() + newLine +
                        "Work hours (per week): " + teamMember.getHoursPerWeek() + newLine + newLine;

            }
        }else{
            data = "No team members";
        }
        return data;
    }

    public String printProjectData(){
        String data;
        String newLine = System.getProperty("line.separator");
        int hours = 0;
        if(!teamMembers.isEmpty()){
            for (TeamMemberData teamMember: teamMembers) {
                hours += teamMember.getHoursPerWeek();
            }
            data = "Project name: " + teamMembers.get(1).getProject() + newLine +
                   "Total project hours per week: " + hours;

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
                        "Current projects: " + teamMembers.get(id).getProject() + newLine +
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