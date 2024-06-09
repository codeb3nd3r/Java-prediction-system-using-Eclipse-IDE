package com.prediction.system;

public class StudentAttendance {
    private boolean[] attendance;
    private boolean[] weather;

    public StudentAttendance(boolean[] attendance, boolean[] weather) {
        this.attendance = attendance;
        this.weather = weather;
    }

    public boolean[] getAttendance() {
        return attendance;
    }

    public boolean[] getWeather() {
        return weather;
    }
}
