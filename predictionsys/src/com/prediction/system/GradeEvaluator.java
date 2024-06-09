package com.prediction.system;

public class GradeEvaluator {

    public double calculateAttendanceRate(boolean[] attendance) {
        int totalDays = attendance.length;
        int presentDays = 0;

        for (boolean present : attendance) {
            if (present) {
                presentDays++;
            }
        }

        return (double) presentDays / totalDays;
    }

    public String evaluateStudent(double gpa, double attendanceRate) {
        String grade;
        if (gpa >= 3.5 && gpa <= 4.0) {
            grade = "A";
        } else if (gpa >= 3.0 && gpa < 3.5) {
            grade = "B";
        } else if (gpa >= 2.0 && gpa < 3.0) {
            grade = "C";
        } else if (gpa >= 1.0 && gpa < 2.0) {
            grade = "D";
        } else {
            grade = "F";
        }

        if (attendanceRate >= 0.75) {
            if ("FDC".contains(grade)) {
                return "Student is weak and poor";
            } else {
                return "Student is good";
            }
        } else {
            if ("FDC".contains(grade)) {
                return "Student didn't attend classes and is an okay student";
            } else {
                return "Student is extraordinary";
            }
        }
    }
}
