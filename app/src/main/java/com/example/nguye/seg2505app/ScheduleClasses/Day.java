package com.example.nguye.seg2505app.ScheduleClasses;

public class Day {
    private String dateString;
    private DailySchedule schedule;
    private boolean loaded;

    // Constructor
    public Day(String dateString, DailySchedule schedule, boolean loaded) {
        this.dateString = dateString;
        this.schedule = schedule;
        this.loaded = loaded;
    }

    public String getDateString() { return this.dateString; }
    public void setDateString(String dateString) { this.dateString = dateString; }

    public DailySchedule getSchedule() { return this.schedule; }
    public void setSchedule(DailySchedule schedule) { this.schedule = schedule; }

    public boolean isLoaded() { return loaded; }
    public void setLoaded(boolean loaded) { this.loaded = loaded; }
}
