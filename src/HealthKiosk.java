import java.util.Scanner;
import java.util.Random;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random letter = new Random();

        System.out.println("Welcome to Ashesi University’s Health Center Kiosk! ");

        // Task 1 — Service Router
        double RoundedBMI = 0;
        int numberOfTablets = 0;
        int sinValue = 0;
        int metricCode = 0;
        String displayCode = "";

        System.out.print("Enter the service code (P/L/T/C): ");
        char service = input.nextLine().charAt(0);
        service = Character.toUpperCase(service);
        // Guide the visitor to services based on their choice.
        switch (service) {
            case 'P':
                System.out.println("Go to: Pharmacy Desk");
                break;
            case 'L':
                System.out.println("Go to: Lab Desk");
                break;
            case 'T':
                System.out.println("Go to: Triage Desk");
                break;
            case 'C':
                System.out.println("Go to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
        }

        // Task 2 — Mini Health Metric
        // If the visitor choose Triage, display available health metrics to choose from.
        if (service == 'T') {
            System.out.print("Enter the health metric (1-BMI, 2-Dosage round-up, 3-simple trig helper): ");
            int metric = input.nextInt();
            input.nextLine();

            // Option 1: BMI calculation = weight / height^2
            if (metric == 1) {
                System.out.print("Enter weight (kg): ");
                double weight = input.nextDouble();
                System.out.print("Enter height (m): ");
                double height = input.nextDouble();
                input.nextLine();
                double BMI = weight / (Math.pow(height, 2));
                RoundedBMI = Math.round(BMI * 10) / 10.0;

                // Based on their BMI categorize them (Underweight, Normal, Overweight or Obese).
                String category = "";
                if (RoundedBMI < 18.5) category = "Underweight";
                else if (RoundedBMI >= 18.5 && RoundedBMI <= 24.9) category = "Normal";
                else if (RoundedBMI >= 25.0 && RoundedBMI <= 29.9) category = "Overweight";
                else if (RoundedBMI >= 30.0) category = "Obese";

                // Display their BMI and their category.
                System.out.println("BMI: " + RoundedBMI + "  Category: " + category);
                metricCode = (int) RoundedBMI;

                // Option 2: Dosage round-up
            } else if (metric == 2) {
                System.out.print("Enter the required dosage (mg): ");
                double requiredDosage = input.nextDouble();
                input.nextLine();
                // Calculate and display the number of tablets they need.
                final int tabletDispense = 250;
                numberOfTablets = (int) Math.ceil(requiredDosage / tabletDispense);

                System.out.println("Number of tablets needed is " + numberOfTablets);
                metricCode = numberOfTablets;

                // Option 3: Simple trig helper
            } else if (metric == 3) {
                System.out.print("Enter an angle in degrees: ");
                double degrees = input.nextDouble();
                input.nextLine();
                // Change the degree into radian to find the Sin and Cos.
                double radian = degrees * Math.PI / 180;
                double sinAngle = Math.sin(radian);
                double cosAngle = Math.cos(radian);
                double roundedSinAngle = (Math.round(sinAngle * 1000) / 1000.0);
                double roundedCosAngle = (Math.round(cosAngle * 1000) / 1000.0);

                System.out.println("Sin of angle " + degrees + " is " + roundedSinAngle);
                System.out.println("Cos of angle " + degrees + " is " + roundedCosAngle);
                sinValue = (int) Math.round(sinAngle * 100);
                metricCode = sinValue;
            } else {
                System.out.println("Invalid metric choice.");
                return; // exit if invalid metric
            }
        }
        // Task 3 — ID Sanity Check
        // Generate the Student ID.
        char randomChar = (char) ('A' + letter.nextInt(26));
        int randomNum1 = (int) (4 + letter.nextInt(5));
        int randomNum2 = (int) (4 + letter.nextInt(5));
        int randomNum3 = (int) (4 + letter.nextInt(5));
        int randomNum4 = (int) (4 + letter.nextInt(5));
        String shortCode = "" + randomChar + randomNum1 + randomNum2 + randomNum3 + randomNum4;
        System.out.println("Your Student ID is: " + shortCode);

        // Validate the Visitor (Student) ID.

        if (shortCode.length() != 5)
            System.out.println("Invalid length");
        else if (!Character.isLetter(shortCode.charAt(0)))
            System.out.println("Invalid: first char must be a letter");
        else if (!Character.isDigit(shortCode.charAt(1)) || !Character.isDigit(shortCode.charAt(2))
                || !Character.isDigit(shortCode.charAt(3)) || !Character.isDigit(shortCode.charAt(4)))
            System.out.println("Invalid: last 4 must be digits");
        else
            System.out.println("Your ID is OK");

        // Task 4 — “Secure” Display Code
        // Generate the final code.
        System.out.print("Enter your first name: ");
        String yourName = input.nextLine();
        char baseCode = yourName.charAt(0);
        baseCode = Character.toUpperCase(baseCode);
        char shiftedLetter = (char) ('A' + (baseCode - 'A' + 2) % 26);
        String lastTwoValues = shortCode.substring(3, 5);

        displayCode = shiftedLetter + lastTwoValues + "-" + metricCode;
        System.out.println("Display Code: " + displayCode);

        // Task 5 — Service Summary
        // Display a summary of their visit result based on their service choice.
        switch (service) {
            case 'P' -> System.out.println("Summary: PHARMACY | ID=" + shortCode + " |Code=" + displayCode);
            case 'T' -> System.out.println("Summary: Triage | ID=" + shortCode + "   |BMI= " + RoundedBMI);
            case 'L' -> System.out.println("Summary: Lab | ID=" + shortCode + " |Code=" + displayCode);
            case 'C' -> System.out.println("Summary: Counseling | ID=" + shortCode + "  |Code=" + displayCode);
        }

    }
}
