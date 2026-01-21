// A parking garage needs to assign vehicles to parking spaces. The garage has N parking spaces, each with a specific size (length in feet). There are M vehicles that need parking, and each vehicle has a known length.
// Your task is to assign vehicles to parking spaces such that:
// Each vehicle is assigned to a space that can accommodate it (space length ≥ vehicle length)
// Each parking space can only be assigned to one vehicle
// You want to maximize the number of vehicles parked
// Among all possible optimal solutions, minimize the total wasted space (sum of unused length across all assigned spaces)
// Input Format
// The first line contains two integers N (1 ≤ N ≤ 1000) and M (1 ≤ M ≤ 1000), representing the number of parking spaces and vehicles respectively.
// The second line contains N pairs of values. Each pair consists of:
// A space identifier (a string of 1-10 characters containing letters and digits, e.g., "A1", "SPOT5", "P23")
// The length of that space in feet (5 ≤ length ≤ 50)
// The third line contains M pairs of values. Each pair consists of:
// A vehicle identifier (a string of 1-10 characters, e.g., "CAR1", "TRUCK", "V42")
// The length of the vehicle in feet (5 ≤ length ≤ 50)
// The input ends when both N and M are zero.
// Output Format
// For each test case, print three lines:
// Line 1: "Case X: Y vehicles parked"
// Line 2: "Total wasted space: Z feet"
// Line 3: "Assignments: [list of assignments]" or "Assignments: none"
// Where:
// X is the test case number (starting from 1)
// Y is the number of vehicles successfully parked
// Z is the total unused space in feet
// Assignments are in the format "VEHICLE->SPACE" sorted by vehicle identifier alphabetically
// Sample Input
// 5 5
// A1 20 A2 25 A3 18 B1 30 B2 22
// CAR1 18 CAR2 24 SUV1 16 TRUCK 28 VAN 21
// 3 4
// P1 15 P2 20 P3 25
// V1 22 V2 18 V3 14 V4 12
// 2 2
// SPOT1 30 SPOT2 40
// BIG 45 SMALL 25
// 0 0
// Sample Output
// Case 1: 5 vehicles parked
// Total wasted space: 13 feet
// Assignments: CAR1->A1 CAR2->A2 SUV1->A3 TRUCK->B1 VAN->B2
// Case 2: 3 vehicles parked
// Total wasted space: 8 feet
// Assignments: V2->P2 V3->P1 V4->P3
// Case 3: 1 vehicles parked
// Total wasted space: 15 feet
// Assignments: SMALL->SPOT1
 
// Explanation
// Case 1:
// All 5 vehicles can be parked
// SUV1(16)→A3(18): waste=2, CAR1(18)→A1(20): waste=2, VAN(21)→B2(22): waste=1, CAR2(24)→A2(25): waste=1, TRUCK(28)→B1(30): waste=2
// Total waste: 8 feet (recalculate for accurate example)
// Case 2:
// Only 3 spaces for 4 vehicles
// Best assignment to minimize waste while parking maximum vehicles
// Case 3:
// BIG(45) cannot fit in any space
// SMALL(25) fits in SPOT1(30): waste=5

// 5 5
    
// A1 20 A2 25 A3 18 B1 30 B2 22

// CAR1 18 CAR2 24 SUV1 16 TRUCK 28 VAN 21
// 3 4
// P1 15 P2 20 P3 25
// V1 22 V2 18 V3 14 V4 12

// 2 2
// SPOT1 30 SPOT2 40
// BIG 45 SMALL 25

// 0 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParkingSpaceAllocation {
    public static int totalCarsParked = 0;
    public static int totalWastedSpace = 0;
    public static List<String> assignedParkingSlots = new ArrayList<>();
    public static Map<String, String> assignments = new TreeMap<>();

    public static void parkingSpaceAllocation(String totalNumbers, Map<String, Integer> parkingSlots, Map<String, Integer> cars){
        String[] separatedTotalNumbers = totalNumbers.split(" ");
        int numberofParkingslots = Integer.parseInt(separatedTotalNumbers[0]);
        int numberOfCars = Integer.parseInt(separatedTotalNumbers[1]);

        if ((numberOfCars <= 0) || (numberofParkingslots <=0))
            return;

        int currentSmallestFitLength = 0;
        
        //get each car length, loop through parking lot to find space bigger than car, whiles tracking the smallest of the biggest
        for (Map.Entry<String, Integer> car : cars.entrySet()) {
            String currentParkingSlot = new String();
            // System.out.println("Carrr" + car.getKey() + "=" + car.getValue());
            for (Map.Entry<String, Integer> parkingSlot : parkingSlots.entrySet()){
                // System.out.println(parkingSlot.getKey() + "=" + parkingSlot.getValue());
        
                //if slot is bigger than car but bigger than the current slot bigger than the car, used the smaller
                if (parkingSlot.getValue() >= car.getValue()){
                    if (!(currentSmallestFitLength >= car.getValue() && parkingSlot.getValue() > currentSmallestFitLength)){
                        //if the parking slot has not been assigned already
                        if (!assignedParkingSlots.contains(parkingSlot.getKey())){
                            assignedParkingSlots.add(parkingSlot.getKey());
                            currentParkingSlot = parkingSlot.getKey();
                            currentSmallestFitLength = parkingSlot.getValue();
                        }
                    } 
                    
                }
                
            }
            //increment parked cars, total wasted space and the car assignment to the parking slot
            if (!currentParkingSlot.isEmpty()){
                totalCarsParked++;
                totalWastedSpace += currentSmallestFitLength - car.getValue();
                assignments.put(car.getKey(), currentParkingSlot);
                // assignments.add(car.getKey() + "->" + currentParkingSlot);
            } 
            
            // System.out.println("Right slot: " + currentParkingSlot);
            // break;
        }
        System.out.println(totalCarsParked + " vehicles parked ");
        System.out.println("Total Wasted Space: "+ totalWastedSpace + " feet");
        System.out.print("Assignments: [ ");
        assignments.forEach((key, value) -> System.out.print(key + " -> " + value + " "));
        System.out.print("]");

    }       

    public static void main(String[] args) {
     
        Map<String, Integer> parkingSlots = new HashMap<>();
        // parkingSlots.put("A1", 20);    
        // parkingSlots.put("A2", 25);    
        // parkingSlots.put("A3", 18);    
        // parkingSlots.put("B1", 30);    
        // parkingSlots.put("B2", 22);    

        parkingSlots.put("P1", 15);    
        parkingSlots.put("P2", 20);    
        parkingSlots.put("P3", 25); 
        
        Map<String, Integer> cars = new HashMap<>();
        // cars.put("CAR1", 18);
        // cars.put("CAR2", 24);
        // cars.put("SUV1", 16);
        // cars.put("TRUCK", 30);
        // cars.put("VAN", 21);

        cars.put("V1", 22);
        cars.put("V2", 18);
        cars.put("V3", 14);
        cars.put("V4", 12);
        
        String totalNumbers = "3 4";

        parkingSpaceAllocation(totalNumbers, parkingSlots, cars);
    }
    

        
}
