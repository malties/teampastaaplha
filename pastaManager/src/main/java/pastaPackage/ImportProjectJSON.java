package pastaPackage;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ImportProjectJSON {

    private File JSON_file;

    private ProjectData projectData;

    public ImportProjectJSON(){
        this.projectData = new ProjectData();
    }

    public void setJsonFile(File file){
        this.JSON_file = file;
    }

    public File getJsonFile(){
        return this.JSON_file;
    }

    public JsonObject getJsonPData() throws  Exception {

        Gson gson = new Gson();
        File file = this.JSON_file;

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

        JsonArray additionalCostsAsJson = jsonPData.get("additionalCosts").getAsJsonArray();
        ArrayList<String> additionalCostsAsString = new ArrayList<>();
        ArrayList<Integer> additionalCosts = new ArrayList<>();
        if (additionalCostsAsJson != null) {
            int len = additionalCostsAsJson.size();
            for (int i=0;i<len;i++){
                additionalCostsAsString.add(additionalCostsAsJson.get(i).toString());
            }

            for (String intToParse : additionalCostsAsString){
                additionalCosts.add( Integer.parseInt(intToParse) );
            }
        }
        projData.setAdditionalCosts(additionalCosts);

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

    public double getAdditionalCost(double interval){
        return projectData.getAdditionalCost(interval);
    }

}
