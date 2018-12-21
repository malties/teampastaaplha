package pastaManager.src.main.java;

import java.util.ArrayList;

public class ProjectData {

    private String PName;
    private int PId;
    private ArrayList<TeamMemberData> TeamMembers;
    private int completion;
    private double budget;

    public ProjectData(String PName, double budget, int PId, ArrayList<TeamMemberData> TeamMembers, int completion) {
        this.PName = PName;
        this.PId = PId;
        this.TeamMembers = TeamMembers;
        this.completion = completion;
        this.budget = budget;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public ArrayList<TeamMemberData> getTeamMembers() {
        return TeamMembers;
    }

    public void setTeamMembers(ArrayList<TeamMemberData> teamMembers) {
        TeamMembers = teamMembers;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
