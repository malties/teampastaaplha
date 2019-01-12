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
        JsonObject pData = jsonPData.getAsJsonObject("Project");

        br.close();
        return pData;
    }

    public void addProjectInformation(JsonObject jsonPData){

        ProjectData projData = new ProjectData();

        String jsonName = jsonPData.get("PName").getAsString();
        projData.setPName(jsonName);
        String jsonPId = jsonPData.get("PId").getAsString();
        projData.setPId(jsonPId);
        int jsonStartWeek = jsonPData.get("startWeek").getAsInt();
        projData.setStartWeek(jsonStartWeek);
        int jsonEndWeek = jsonPData.get("endWeek").getAsInt();
        projData.setEndWeek(jsonEndWeek);
        double jsonBudget = jsonPData.get("budget").getAsDouble();
        projData.setBudget(jsonBudget);
        int jsonStartYear = jsonPData.get("startYear").getAsInt();
        projData.setStartYear(jsonStartYear);
        int jsonEndYear = jsonPData.get("endYear").getAsInt();
        projData.setEndYear(jsonEndYear);

        this.projectData = projData;
    }

    public String getProjectData(){
        String data = "";
        String newLine = System.getProperty("line.separator");
        if(projectData != null){
            data =  "Project name: " + projectData.getPName() + newLine +
                    "Start: week " + projectData.getStartWeek() + " year " + projectData.getStartYear() + newLine +
                    "End: week " + projectData.getEndWeek() + " year " + projectData.getEndYear() + newLine;

        }
        return data;
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
