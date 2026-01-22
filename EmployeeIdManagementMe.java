// A human resources department needs an efficient system to manage employee records. Employees are identified by unique employee ID numbers, and the system must support fast lookups, insertions, and various queries.
// Your task is to implement a system that efficiently handles the following operations:
// REGISTER id salary: Add an employee with the given ID and salary. If the ID already exists, update their salary.
// LOOKUP id: Find an employee by their ID and report their salary.
// RANGE low high: Find all employees with IDs in the range [low, high] (inclusive) and list them in ascending order.
// HEIGHT: Calculate and report the maximum height of your data structure.
// PATH id: Show the navigation path taken to reach a specific employee ID.
// DISPLAY: Show all employees sorted by ID in ascending order.
// Input Format
// The first line contains an integer N (1 ≤ N ≤ 1000), the number of operations. Each of the next N lines contains an operation:
// REGISTER id salary - where id is an integer (1 ≤ id ≤ 1000000) and salary is an integer (1000 ≤ salary ≤ 500000)
// LOOKUP id - where id is an integer
// RANGE low high - where low and high are integers
// HEIGHT - no parameters
// PATH id - where id is an integer
// DISPLAY - no parameters
// The input ends when N is zero.
// Output Format
// For each test case, print:
// "Case X:" where X is the test case number (starting from 1)
// For each operation, print the result:
// REGISTER: "Registered: ID=X, Salary=Y"
// LOOKUP: "Found: ID=X, Salary=Y" or "Not found: ID=X"
// RANGE: "Range [X-Y]: [comma-separated list of IDs]" or "Range [X-Y]: none"
// HEIGHT: "Tree height: H"
// PATH: "Path to X: [comma-separated path of IDs]" or "Path to X: not found"
// DISPLAY: "All employees: [comma-separated list of all IDs]"
// Print a blank line after each test case.
// Sample Input
// 10
// REGISTER 50000 75000
// REGISTER 30000 65000
// REGISTER 70000 80000
// REGISTER 20000 55000
// REGISTER 40000 70000
// LOOKUP 30000
// RANGE 25000 45000
// HEIGHT
// PATH 20000
// DISPLAY
// 8
// REGISTER 10000 50000
// REGISTER 5000 45000
// REGISTER 15000 60000
// REGISTER 2500 40000
// LOOKUP 20000
// RANGE 5000 15000
// HEIGHT
// DISPLAY
// 0
// Sample Output
// Case 1:
// Registered: ID=50000, Salary=75000
// Registered: ID=30000, Salary=65000
// Registered: ID=70000, Salary=80000
// Registered: ID=20000, Salary=55000
// Registered: ID=40000, Salary=70000
// Found: ID=30000, Salary=65000
// Range [25000-45000]: [30000, 40000]
// Tree height: 2
// Path to 20000: [50000, 30000, 20000]
// All employees: [20000, 30000, 40000, 50000, 70000]
// Case 2:
// Registered: ID=10000, Salary=50000
// Registered: ID=5000, Salary=45000
// Registered: ID=15000, Salary=60000
// Registered: ID=2500, Salary=40000
// Not found: ID=20000
// Range [5000-15000]: [5000, 10000, 15000]
// Tree height: 2
// All employees: [2500, 5000, 10000, 15000]

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeIdManagementMe {

    public static List<String> commands = List.of("REGISTER", "LOOKUP", "RANGE", "HEIGHT", "PATH", "DISPLAY", "DISPLAY_VERBOSE");

    class InputParser {
        static String[] getLine(String line) {
            String[] commandArgs = line.split(" ");
            return commandArgs;
        }
    }

    public static Employee creatEmployee(int id, int salary) {
        return new Employee(id, salary);
    }

    public static void main(String[] args) {

        //you can place the content in the terminal when you run it
        String sampleInput = """
            11
            REGISTER 50000 75000
            REGISTER 30000 65000
            REGISTER 70000 80000
            REGISTER 20000 55000
            REGISTER 40000 70000
            RANGE 25000 55000
            PATH 50000
            LOOKUP 70000
            HEIGHT
            DISPLAY
            DISPLAY_VERBOSE
        """;

        EmployeeInventory employeeInventory = new EmployeeInventory();
        List<String[]> instructions = new ArrayList<>();

        System.out.println("Enter inputs");
        Scanner scanner = new Scanner(System.in);
        int inputSize = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= inputSize; i++) {
            String line = scanner.nextLine();
            instructions.add(InputParser.getLine(line));
        }

        System.out.println("\nStarting Operations...\n");

        for (String[] instruction : instructions) {
            switch (instruction.length) {
                case 1:
                    if (commands.contains(instruction[0])) {
                        switch (instruction[0]) {
                            case "HEIGHT":
                                employeeInventory.height();
                                break;

                            case "DISPLAY":
                                employeeInventory.display();
                                break;
                            case "DISPLAY_VERBOSE":
                                System.out.println("\nEmployees:\n");
                                employeeInventory.inOrder();
                                break;

                            default:
                                break;
                        }
                    }
                    break;
                case 2:
                    if (commands.contains(instruction[0])) {
                        switch (instruction[0]) {
                            case "LOOKUP":
                                employeeInventory.search(Integer.parseInt(instruction[1]));
                                break;
                            case "PATH":
                                employeeInventory.inOrder();
                                employeeInventory.findPath(Integer.parseInt(instruction[1]));
                            default:
                                break;
                        }
                    }
                    break;

                case 3:
                    if (commands.contains(instruction[0])) {
                        switch (instruction[0]) {
                            case "REGISTER":
                                Employee newEmployee = creatEmployee(Integer.parseInt(instruction[1]),
                                        Integer.parseInt(instruction[2]));
                                employeeInventory.insert(newEmployee);
                                System.out.println("Employee: " + Integer.parseInt(instruction[1]) + " added");
                                // System.out.println("Employees->");
                                // employeeInventory.inOrder();
                                break;

                            case "RANGE":
                                employeeInventory.inorderFindRange(Integer.parseInt(instruction[1]),
                                        Integer.parseInt(instruction[2]));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

}

class Employee {

    private int id;
    private int salary;

    public Employee(int id, int salary) {
        this.id = id;
        this.salary = salary;
    }

    public Employee getEmployee(int id) {
        return null;

    }

    public int getId() {
        return this.id;
    }

    public int getSalary() {
        return this.salary;
    }

    public String toString() {
        return "(Employee: ID - " + this.id + ", Salary - " + this.salary + ")\n";

    }
}

class Node {
    Employee employee;
    Node left, right;

    public Node(Employee employee) {
        this.employee = employee;
        this.left = this.right = null;
    }
}

class EmployeeInventory {

    Node root = null;
    private static List<Integer> idsWithinRange = new ArrayList<>();
    private static List<Integer> employeePath = new ArrayList<>();
    private static List<Integer> employees = new ArrayList<>();

    boolean foundIdInPath = false;

    public void insert(Employee employee) {
        root = insertRecursive(root, employee);
    }

    public void display(){
        employees.clear();
        displayRecursive(root);
        System.out.print("All employees: ");
        System.out.println(employees);
    }

    public void inOrder() {
        inorderRecursive(root);
    }

    public void search(int id) {
        Node found = searchRecursive(root, id);
        if (found != null) {
            System.out.println("Found:" + found.employee);
        } else {
            System.out.printf("\nNot Found: ID=%d\n", id);

        }
    }

    public void inorderFindRange(int low, int high) {
        idsWithinRange.clear();
        inorderFindRangeRecursive(root, low, high);

        if (!idsWithinRange.isEmpty()) {
            System.out.print("Range [" + low + "-" + high + "]: ");
            System.out.println(idsWithinRange);
        } else {
            System.out.print("Range [" + low + "-" + high + "]: None\n");
        }
    }

    public void findPath(int id) {
        employeePath.clear();
        findPathRecursive(root, id);
        if (foundIdInPath) {
            System.out.print("Path to " + id + ": ");
            System.out.println(employeePath);
        } else {
            employeePath.clear();
        }
    }

    public void height() {
        int height = heightRecursive(root);
        System.out.println(height);

    }

    private int heightRecursive(Node root) {
        if (root == null)
            return -1;
        int left = heightRecursive(root.left);
        int right = heightRecursive(root.right);
        return Integer.max(left, right) + 1;
    }

    private void findPathRecursive(Node root, int id) {
        if (root != null) {
            findPathRecursive(root.left, id);
            int currentId = root.employee.getId();

            if (currentId != id && (this.foundIdInPath == false)) {
                employeePath.add(currentId);
            } else if (currentId == id) {
                employeePath.add(currentId);
                this.foundIdInPath = true;
                return;
            }

            findPathRecursive(root.right, id);
        }
    }

    private void inorderFindRangeRecursive(Node root, int low, int high) {
        if (root != null) {
            inorderFindRangeRecursive(root.left, low, high);
            int id = root.employee.getId();
            if (id >= low && id <= high) {
                idsWithinRange.add(id);
            }
            inorderFindRangeRecursive(root.right, low, high);
        }
    }

    

    private Node searchRecursive(Node root, int id) {
        if (root == null || root.employee.getId() == id) {
            return root;
        }
        if (root.employee.getId() > id) {
            return searchRecursive(root.left, id);
        }
        return searchRecursive(root.right, id);
    }

    private void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.println(root.employee);
            inorderRecursive(root.right);
        }
    }

    private void displayRecursive(Node root) {
        if (root != null) {
            displayRecursive(root.left);
            employees.add(root.employee.getId());
            displayRecursive(root.right);
        }
    }

    private Node insertRecursive(Node root, Employee employee) {
        if (root == null) {
            return new Node(employee);
        }

        if (employee.getId() < root.employee.getId()) {
            root.left = insertRecursive(root.left, employee);
        }

        if (employee.getId() > root.employee.getId()) {
            root.right = insertRecursive(root.right, employee);
        }

        return root;
    }

}