package com.careitapp.care_it;

public class Pill {

    private String pillName,pillsPerSession,sessionsPerDay,totalPills;

    Pill(){
    }

    Pill(String pillName, String pillsPerSession, String sessionsPerDay, String totalPills){
        this.pillName = pillName;
        this.pillsPerSession = pillsPerSession;
        this.sessionsPerDay = sessionsPerDay;
        this.totalPills = totalPills;
    }

    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
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
