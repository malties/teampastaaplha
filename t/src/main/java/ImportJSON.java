import com.google.gson.*;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

public class ImportJSON {

    public ImportJSON() {

    }

    private static final String JSON_PATH = "./src/Individual.json";
    importdata.TeamMemberData data = new importdata.TeamMemberData();

    public JsonObject getJsonObject() throws Exception {
        Gson gson = new Gson();
        File file = Paths.get(JSON_PATH).toFile();
        JsonObject jsonObject = gson.fromJson(new FileReader(file), JsonObject.class);
        return jsonObject;
    }

    public void setTeamMemberData(JsonObject jsonObject) {
        String jsonName = jsonObject.get("name").getAsString();
        data.setName(jsonName);

        int jsonUid = jsonObject.get("uid").getAsInt();
        data.setUid(jsonUid);

        int jsonSalaryPerHour = jsonObject.get("salaryPerHour").getAsInt();
        data.setSalaryPerHour(jsonSalaryPerHour);

        String jsonProject = jsonObject.get("project").getAsString();
        data.setProject(jsonProject);

        int jsonHoursPerWeek = jsonObject.get("hoursPerWeek").getAsInt();
        data.setHoursPerWeek(jsonHoursPerWeek);

        System.out.println("Team member name: " + data.getName());
        System.out.println("Member ID number: " + data.getUid());
        System.out.println("Salary per hour: " + data.getSalaryPerHour());
        System.out.println("Project name: " + data.getProject());
        System.out.println("Hours per week: " + data.getHoursPerWeek());
    }

    public static void main(String[] args) {
        ImportJSON importJSON = new ImportJSON();
        try {
            importJSON.setTeamMemberData(importJSON.getJsonObject());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}