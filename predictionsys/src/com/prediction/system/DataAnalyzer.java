package com.prediction.system;

public class DataAnalyzer {

    public double analyze(boolean[] attendance, boolean[] weather, boolean tomorrowWeather) {
        int days = attendance.length;
        int presentCount = 0;
        int presentWhenSunny = 0;
        int totalSunny = 0;
        int presentWhenRainy = 0;
        int totalRainy = 0;

        for (int i = 0; i < days; i++) {
            if (weather[i]) {
                totalSunny++;
                if (attendance[i]) {
                    presentWhenSunny++;
                }
            } else {
                totalRainy++;
                if (attendance[i]) {
                    presentWhenRainy++;
                }
            }
            if (attendance[i]) {
                presentCount++;
            }
        }

        double overallProbability = (double) presentCount / days;
        double sunnyProbability = (totalSunny != 0) ? (double) presentWhenSunny / totalSunny : 0;
        double rainyProbability = (totalRainy != 0) ? (double) presentWhenRainy / totalRainy : 0;

        return tomorrowWeather ? sunnyProbability : rainyProbability;
    }
}
