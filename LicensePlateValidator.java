// A vehicle registration system needs to validate and format license plates from different regions. 
// Create a license plate validator that checks if plates follow proper formatting rules and converts them to a standard format.
// A license plate can be in various formats, but must follow these rules:

// Input Format Rules:
// May optionally start with a region code (2 uppercase letters)
// Must contain exactly 6 alphanumeric characters after the region code
// Can contain spaces, hyphens (-), or underscores (_) as separators
// Must have at least 2 letters and at least 2 digits
// No other special characters allowed

// Validation Rules:
// Character count: Must have exactly 6 alphanumeric characters (after region code)
// Region code: If present, must be exactly 2 uppercase letters
// Separators: Only spaces, hyphens, and underscores allowed
// Letter count: Must have at least 2 letters in the main part
// Digit count: Must have at least 2 digits in the main part
// Case: Letters can be any case in input but must be uppercase in output

// Output Format:
// Valid plates should be formatted as: [REGION-] XXX-XXX (where X is letter or digit)
// If no region code provided, omit it from output
// All letters must be uppercase
// Use hyphen to separate into two groups of 3 characters
// Invalid plates should list specific violations

// Input Format
// The first line contains an integer N (1 ≤ N ≤ 100), the number of license plates to process. The next N lines each contain a single license plate string (1 ≤ length ≤ 30 characters). The input ends when N is zero.
// Output Format
// For each test case, print:
// Line 1: "Case X:" where X is the test case number (starting from 1)
// For each plate: "Plate Y: [formatted plate]" if valid
// "Plate Y: Invalid" if invalid
// If invalid: "Reasons: [list of violations]"
// Violation reasons (check in order):
// "Invalid region code"
// "Wrong character count"
// "Invalid characters"
// "Insufficient letters"
// "Insufficient digits"
// Print a blank line after each test case

//Sample valid registration plate number [REGION-]XXX-XXX

import java.util.ArrayList;
import java.util.List;

public class LicensePlateValidator {
    
    public static char [] separators = {'-', '_', ' '};
    public static List<String> reasons = new ArrayList<>();

    private static void licensePlateValidator(int N, String[] numberPlates) {
        String totalCharacters = new String();
        for (String numberPlate : numberPlates) {
        // && !("-_ ".indexOf(firstSeparator) >= 0)
            //check for valid region code if present
            String regionCode = numberPlate.substring(0, 2);
            String firstSeparator = numberPlate.substring(2,3);
            String restOfCharacters = new String();
            if ((!regionCode.matches("[A-Z]{2}")) && ("-_ ".indexOf(firstSeparator) >= 0) ){
                reasons.add("Invalid Region Code");
                System.out.println("Plate " + numberPlate + ": Invalid  Region Code");
                continue;

            }

            if (("-_ ".indexOf(firstSeparator) >= 0)){
                restOfCharacters = numberPlate.substring(3);
                totalCharacters = regionCode + restOfCharacters;
            } else {
                totalCharacters = numberPlate.substring(0);
            }

            //check for wrong character count
            if (totalCharacters.length() < 6){
                reasons.add("Wrong Character Count");
                System.out.println("Plate " + numberPlate + ": Wrong Character Count");
                continue;
            }
            
            //check for invalid characters
            if (!totalCharacters.matches("[A-Za-z0-9]+")){

                System.out.println("Plate " + numberPlate + ": Invalid  Characters");
                reasons.add("Invalid Characters");
                // System.out.println(totalCharacters);
                continue;
            }
            
            //check for insufficient letters and numbers
            if (!hasAtLeastTwoLetters(numberPlate)){
                reasons.add("Insufficient Letters");
                // System.out.println("Plate " + numberPlate + ": Insufficient Letters");

                continue;
            }

            if (!hasAtLeastTwoDigits(numberPlate)){
                reasons.add("Insufficient Digits");
                System.out.println("Plate " + numberPlate + ": Insufficient Digits");
                continue;
            }

            System.out.println(formatPlateNumber(numberPlate));

            //output the format
            // if (!reasons.isEmpty()){
            //     System.out.println("Plate " + numberPlate + ": Invalid");
            //     System.out.println("Reasons: ");
            //     for (String reason : reasons) {
            //         System.out.println(reason);
            //     }
            // }
            
        }
    }

    public static boolean hasAtLeastTwoLetters(String plate){
        if (plate == null || plate.isEmpty()){
            return false;
        }
        int lettercount = 0;

        for (char c : plate.toCharArray()){
            if (Character.isLetter(c)){
                lettercount++;
            }
        }
        return lettercount >= 2;
    }

    public static boolean hasAtLeastTwoDigits(String plate){
        if (plate == null || plate.isEmpty()){
            return false;
        }
        int digitCount = 0;

        for (char c : plate.toCharArray()){
             if (Character.isDigit(c)){
                digitCount++;
            }
        }
        return digitCount >= 2;
    }

    public static String formatPlateNumber(String plate){
        plate = plate.toUpperCase();
        String formattedPlate = new String();
        if (plate.length() == 6){
            formattedPlate =  plate.substring(0, 3) + "-" + plate.substring(3,6);
        } else if (plate.length() == 9){
            formattedPlate =  plate.substring(0, 2) + "-" + plate.substring(3,6) + "-" + plate.substring(6);
        }
        return formattedPlate;
    }


    public static void main(String[] args) {
        String [] numberPlates = new String[]{
            "CA-ABC123",
            "XYZ789",
            "12-AB34CD",
            "GH_1A2B3C",
            "AB-12.34.56",
            "TX ABC1"
        };
        int N = 6;

        licensePlateValidator(N, numberPlates);
    }


}
