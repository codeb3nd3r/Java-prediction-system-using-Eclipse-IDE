package com.prediction.system;

import java.util.Scanner;

public class PredictionSystem {

    private static final int MAX_USERS = 3;
    private static UserData[] users = new UserData[MAX_USERS];
    private static UserData currentUser;
    private static DataCollector collector = new DataCollector();
    private static DataAnalyzer analyzer = new DataAnalyzer();
    private static StudentAttendance studentAttendance;
    private static String currentStudentName;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Prediction System:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void signUp(Scanner scanner) {
        if (currentUser != null) {
            System.out.println("You are already registered and signed up.");
            return;
        }

        if (UserData.getNumUsers() >= MAX_USERS) {
            System.out.println("Maximum number of users reached. Cannot sign up.");
            return;
        }

        System.out.print("Enter a username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Enter your gender (male/female): ");
        String gender = scanner.nextLine().trim().toLowerCase();

        UserData user = new UserData(username, password, gender);
        users[UserData.getNumUsers() - 1] = user;
        currentUser = user;
        studentAttendance = null; // Reset student data
        currentStudentName = null; // Reset current student name

        System.out.println("Sign up successful. You are now logged in.");
        mainMenu(scanner);
    }

    private static void login(Scanner scanner) {
        if (currentUser != null) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        boolean found = false;
        for (UserData user : users) {
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                found = true;
                studentAttendance = null; // Reset student data
                currentStudentName = null; // Reset current student name
                break;
            }
        }

        if (found) {
            System.out.println("Login successful.");
            mainMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void mainMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.printf("Welcome %s:\n", currentUser.getTitleAndName());
            System.out.println("1. Data Entry");
            System.out.println("2. Calculate Probability of Attendance");
            System.out.println("3. Evaluate Student");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    collectData(scanner);
                    break;
                case 2:
                    if (studentAttendance == null) {
                        System.out.println("Please enter data first (Option 1).");
                    } else {
                        calculateProbability(scanner);
                    }
                    break;
                case 3:
                    if (studentAttendance == null) {
                        System.out.println("Please enter data first (Option 1).");
                    } else {
                        evaluateStudent(scanner);
                    }
                    break;
                case 4:
                    currentUser = null;
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void collectData(Scanner scanner) {
        if (currentStudentName == null) {
            System.out.print("Enter the student's name: ");
            currentStudentName = scanner.nextLine().trim();
        }

        collector.collectData();
        boolean[] attendance = collector.getAttendance();
        boolean[] weather = collector.getWeather();
        studentAttendance = new StudentAttendance(attendance, weather);
    }

    private static void calculateProbability(Scanner scanner) {
        System.out.println("Is the weather going to be sunny tomorrow? (yes/no)");
        boolean tomorrowWeather = scanner.nextLine().trim().equalsIgnoreCase("yes");
        double probability = analyzer.analyze(studentAttendance.getAttendance(), studentAttendance.getWeather(), tomorrowWeather) * 100;
        System.out.printf("The probability that %s will come to school tomorrow is: %.2f%%\n", currentStudentName, probability);
    }

    private static void evaluateStudent(Scanner scanner) {
        System.out.printf("Enter %s's GPA (0-4): ", currentStudentName);
        double gpa = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        GradeEvaluator evaluator = new GradeEvaluator();
        double attendanceRate = evaluator.calculateAttendanceRate(studentAttendance.getAttendance());
        String evaluation = evaluator.evaluateStudent(gpa, attendanceRate);

        System.out.printf("%s's Evaluation: %s (Attendance Rate: %.2f%%)\n", currentStudentName, evaluation, attendanceRate * 100);
    }

    private static class UserData {
        private static int numUsers = 0;

        private String username;
        private String password;
        private String gender;

        public UserData(String username, String password, String gender) {
            this.username = username;
            this.password = password;
            this.gender = gender;
            numUsers++;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getGender() {
            return gender;
        }

        public String getTitleAndName() {
            if (gender.equalsIgnoreCase("male")) {
                return "Mr. " + username;
            } else if (gender.equalsIgnoreCase("female")) {
                return "Ms. " + username;
            } else {
                return username;
            }
        }

        public static int getNumUsers() {
            return numUsers;
        }
    }
}
 