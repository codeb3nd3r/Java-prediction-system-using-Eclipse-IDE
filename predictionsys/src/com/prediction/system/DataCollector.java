package com.prediction.system;

import java.util.Scanner;

public class DataCollector {

    public static final int DAYS = 15;
    private boolean[] attendance = new boolean[DAYS];
    private boolean[] weather = new boolean[DAYS];

    public void collectData() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < DAYS; i++) {
            System.out.println("Day " + (i + 1) + ": Did the student come to school? (yes/no)");
            attendance[i] = scanner.nextLine().trim().equalsIgnoreCase("yes");

            System.out.println("Day " + (i + 1) + ": Was the weather sunny? (yes/no)");
            weather[i] = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }
    }

    public boolean[] getAttendance() {
        return attendance;
    }

    public boolean[] getWeather() {
        return weather;
    }
}
