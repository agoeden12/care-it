package com.careitapp.care_it;

public class Pill {

    private String pillTitle,pillsPerSession,sessionsPerDay,totalPills;

    Pill(String pillName, String pillsPerSession, String sessionsPerDay, String totalPills){
        this.pillTitle = pillName;
        this.pillsPerSession = pillsPerSession;
        this.sessionsPerDay = sessionsPerDay;
        this.totalPills = totalPills;
    }

    public String getPillTitle() {
        return pillTitle;
    }

    public void setPillTitle(String pillTitle) {
        this.pillTitle = pillTitle;
    }

    public String getPillsPerSession() {
        return pillsPerSession;
    }

    public void setPillsPerSession(String pillsPerSession) {
        this.pillsPerSession = pillsPerSession;
    }

    public String getSessionsPerDay() {
        return sessionsPerDay;
    }

    public void setSessionsPerDay(String sessionsPerDay) {
        this.sessionsPerDay = sessionsPerDay;
    }

    public String getTotalPills() {
        return totalPills;
    }

    public void setTotalPills(String totalPills) {
        this.totalPills = totalPills;
    }
}
