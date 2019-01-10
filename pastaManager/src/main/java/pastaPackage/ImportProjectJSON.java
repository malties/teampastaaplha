package pastaPackage;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;


import java.io.File;
import java.io.FileReader;

public class ImportProjectJSON {

    private static final String PROJECT_JSON_FILE = ClassLoader.getSystemResource("ProjectData.json").getFile();

    private ProjectData projectData;

    public ImportProjectJSON(){
        this.projectData = new ProjectData();
    }

    public JsonObject getJsonPData() throws  Exception {
        Gson gson = new Gson();
        File file = new File(PROJECT_JSON_FILE);
        JsonReader br = new JsonReader(new FileReader(file));
        JsonObject jsonPData = gson.fromJson(br, JsonObject.class);
        br.close();
        return jsonPData;
    }

    public void addTeamMembers(JsonObject jsonPData){

        String jsonName = jsonPData.get("PName").getAsString();
        projectData.setPName(jsonName);
        String jsonPId = jsonPData.get("PId").getAsString();
        projectData.setPId(jsonPId);
        int jsonStartWeek = jsonPData.get("startWeek").getAsInt();
        projectData.setStartWeek(jsonStartWeek);
        int jsonEndWeek = jsonPData.get("endWeek").getAsInt();
        projectData.setEndWeek(jsonEndWeek);
        double jsonBudget = jsonPData.get("budget").getAsDouble();
        projectData.setBudget(jsonBudget);
        int jsonStartYear = jsonPData.get("startYear").getAsInt();
        projectData.setStartYear(jsonStartYear);
        int jsonEndYear = jsonPData.get("endYear").getAsInt();
        projectData.setEndYear(jsonEndYear);
    }

    public int getStartWeek(){
        return projectData.getStartWeek();
    }

    public int getEndWeek(){
        return projectData.getEndWeek();
    }

    public int getStartYear(){
        return projectData.getStartYear();
    }

    public int getEndYear(){
        return projectData.getEndYear();
    }

    public double getBudget(){
        return projectData.getBudget();
    }

}
