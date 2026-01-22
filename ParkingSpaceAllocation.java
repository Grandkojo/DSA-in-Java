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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ParkingSpaceAllocation {
    public static int totalCarsParked = 0;
    public static int totalWastedSpace = 0;
    public static List<String> assignedParkingSlots = new ArrayList<>();
    public static Map<String, String> assignments = new TreeMap<>();


    public static void parkingSpaceAllocation(String totalNumbers, List<Map.Entry<String, Integer>> parkingSlots, List<Map.Entry<String, Integer>> cars){
        String[] separatedTotalNumbers = totalNumbers.split(" ");
        int numberofParkingslots = Integer.parseInt(separatedTotalNumbers[0]);
        int numberOfCars = Integer.parseInt(separatedTotalNumbers[1]);

        if ((numberOfCars <= 0) || (numberofParkingslots <=0))
            return;

        
        //sort the cars and parkingslots list in decending order
        Collections.sort(cars, Map.Entry.<String, Integer>comparingByValue().reversed());
        Collections.sort(parkingSlots, Map.Entry.<String, Integer>comparingByValue().reversed());
        
        System.out.println(cars.toString());
        System.out.println(parkingSlots.toString());
        System.out.println();
        
        for(Entry<String, Integer> car: cars){
            
            int smallestWaste = Integer.MAX_VALUE;
            Entry<String,Integer> bestSlot = null;

            for(Entry<String, Integer> parkingSlot : parkingSlots){

                int waste = (parkingSlot.getValue() - car.getValue());
                
                //if waste is positive , smaller than the current smallest
                if (waste >= 0 && waste < smallestWaste){
                    smallestWaste = waste;
                    bestSlot = parkingSlot;
                }
                
            }
            
            //if smallest of smallest of slots that could fit the car is found, assign the slot and mark that slot as unavailable
            if (bestSlot != null){
                assignedParkingSlots.add(bestSlot.getKey());
                assignments.put(car.getKey(), bestSlot.getKey());
                parkingSlots.remove(bestSlot);
                totalCarsParked++;
                totalWastedSpace += smallestWaste;

            }  else {
                System.out.println("No spot found for " + car.getKey());
            }


        }        

        System.out.println(totalCarsParked + " vehicles parked ");
        System.out.println("Total Wasted Space: "+ totalWastedSpace + " feet");
        System.out.print("Assignments: [ ");
        assignments.forEach((key, value) -> System.out.print(key + " -> " + value + " "));
        System.out.print("]");
        System.out.println();

    }       

    public static void main(String[] args) {
     
        List<Map.Entry<String, Integer>> parkingSlots = new ArrayList<>();
        parkingSlots.add(Map.entry("A1", 20));    
        parkingSlots.add(Map.entry("A2", 25));    
        // parkingSlots.add(Map.entry("A3", 18));    
        parkingSlots.add(Map.entry("B1", 30));    
        // parkingSlots.add(Map.entry("B2", 22));    

        // parkingSlots.put("P1", 15);    
        // parkingSlots.put("P2", 20);    
        // parkingSlots.put("P3", 25); 
        
        List<Map.Entry<String, Integer>>  cars = new ArrayList<>();
        cars.add(Map.entry("CAR1", 18));
        cars.add(Map.entry("CAR2", 24));
        // cars.add(Map.entry("SUV1", 16));
        // cars.add(Map.entry("TRUCK", 28));
        // cars.add(Map.entry("VAN", 21));

        // cars.put("V1", 22);
        // cars.put("V2", 18);
        // cars.put("V3", 14);
        // cars.put("V4", 12);
        
        String totalNumbers = "5 5";

        parkingSpaceAllocation(totalNumbers, parkingSlots, cars);
    }
    

        
}
