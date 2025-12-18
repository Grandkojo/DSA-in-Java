import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

// Represents a single employee
class Employee {
    int id;
    String name;
    String position;

    public Employee(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee found -  " + position + "{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

// Represents a department which holds employees and sub-departments
class Department {
    String name;
    // Neat List: Sorted Managers
    List<Employee> managers;
    // Messy List: Unsorted Interns
    List<Employee> interns;
    List<Department> subDepartments;

    public Department(String name, List<Employee> managers, List<Employee> interns) {
        this.name = name;
        this.managers = managers;
        this.interns = interns;
        this.subDepartments = new ArrayList<>();
    }
    
    public void addSubDepartment(Department sub) {
        this.subDepartments.add(sub);
    }
        
    @Override
    public String toString() {
        return "\nDepartment: " + name + 
               "\n  Managers: " + managers + 
               "\n  Interns: " + interns +
               "\n  Sub-Departments: " + subDepartments.size();
    }
}




public class MegaCorpDataGeneratorator {

    public static List<Department> generateMegaCorpData() {
        
        // --- Engineering Department Data ---
        
        // 1. Engineering Interns (Messy/Unsorted)
        List<Employee> engineeringInterns = Arrays.asList(
            new Employee(905, "Kyle", "Intern"),
            new Employee(901, "Alice", "Intern"),
            new Employee(909, "Zoe", "Intern"),
            new Employee(902, "Bob", "Intern")
        );
        // Note: We use Arrays.asList initially and the order is already messy/randomized.
        
        // 2. Engineering Managers (Neat/Sorted IDs)
        List<Employee> engineeringManagers = Arrays.asList(
            new Employee(101, "Ms. Alba", "Engineering Manager"),
            new Employee(102, "Mr. Ben", "Engineering Manager"),
            new Employee(103, "Dr. Cai", "Engineering Manager"),
            new Employee(104, "Dev David", "Engineering Manager")
        );
        // Requirement: Managers must be sorted. The IDs are naturally in order here.

        // Create Sub-Departments (AI, Mobile, Web)
        Department aiDept = new Department("AI", 
            Collections.singletonList(new Employee(201, "Dr. Sparks", "AI Lead")), 
            Arrays.asList(new Employee(950, "Tina", "Intern"))
        );
        
        Department mobileDept = new Department("Mobile", 
            Collections.singletonList(new Employee(202, "Ms. Byte", "Mobile Lead")), 
            Arrays.asList(new Employee(951, "Uma", "Intern"))
        );

        Department engineeringDept = new Department("Engineering", engineeringManagers, engineeringInterns);
        engineeringDept.addSubDepartment(aiDept);
        engineeringDept.addSubDepartment(mobileDept);
        
        // --- Sales Department Data ---
        
        List<Employee> salesManagers = Arrays.asList(
            new Employee(301, "Sal", "Sales Manager"),
            new Employee(302, "Sue", "Sales Manager")
        );

        List<Employee> salesInterns = Arrays.asList(
             new Employee(980, "Victor", "Intern"),
             new Employee(979, "Wendy", "Intern")
        );
        
        Department salesDept = new Department("Sales", salesManagers, salesInterns);

        // 1. Inside Sales (Handles leads remotely via phone/web)
        List<Employee> insideSalesManagers = Collections.singletonList(
            new Employee(401, "Ian", "Inside Sales Lead")
        );
        List<Employee> insideSalesInterns = Arrays.asList(
            new Employee(960, "Nancy", "Intern"),
            new Employee(961, "Oscar", "Intern")
        );
        Department insideSalesDept = new Department("Inside Sales", insideSalesManagers, insideSalesInterns);
        salesDept.addSubDepartment(insideSalesDept);

        // 2. Outside Sales (Field Sales - meets clients face-to-face in the field)
        List<Employee> outsideSalesManagers = Collections.singletonList(
            new Employee(501, "Owen", "Field Sales Lead")
        );
         List<Employee> outsideSalesInterns = Arrays.asList(
            new Employee(970, "Peter", "Intern"),
            new Employee(971, "Quinn", "Intern")
        );
        Department outsideSalesDept = new Department("Outside Sales", outsideSalesManagers, outsideSalesInterns);
        salesDept.addSubDepartment(outsideSalesDept);

        List<Department> megaCorp = Arrays.asList(engineeringDept, salesDept);

        return megaCorp; // Returning one main structure for demo
    }

    public static Employee binarySearch(List<Employee> employees, int target){
         int low = 0;
        int high = employees.size() - 1; 

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Employee midEmployee = employees.get(mid);

            if (midEmployee.id == target) {
                return midEmployee; 
            } else if (midEmployee.id < target) {
                low = mid + 1;
            } else {
                high = mid - 1; 
            }
        }
        return null;
    }

    public static Employee linearSearch(List<Employee> employees, int target){
         for (int i = 0; i < employees.size(); i++){
            if (employees.get(i).id == target)
                return employees.get(i);
        }
        return null; 

    }


    public static Employee findEmployee(List<Department> departments, int employeeID){
        for (Department department : departments){
            
            // 1. Check if employee is a manager (use Binary Search)
            Employee foundEmployee = binarySearch(department.managers, employeeID);
            if (foundEmployee != null)
                return foundEmployee;
            
            // 2. Check if employee is an intern
            foundEmployee = linearSearch(department.interns, employeeID);
            if (foundEmployee != null)
                return foundEmployee;

            // 3. Check through the subdepartments RECURSIVELY
            if (!department.subDepartments.isEmpty()){
                foundEmployee = findEmployee(department.subDepartments, employeeID);
                if (foundEmployee != null)
                    return foundEmployee;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Department> corpData = generateMegaCorpData();
        System.out.println("--- MegaCorp Search Results ---");
        
        // Test cases:
        // ID 103 (Manager - Engineering)
        System.out.println("Searching for 103: " +  findEmployee(corpData, 103)); 
        // ID 909 (Intern - Engineering)
        System.out.println("Searching for 909: " + findEmployee(corpData, 909));
        // ID 401 (Manager - Sales -> Inside Sales)
        System.out.println("Searching for 401: " + findEmployee(corpData, 401)); 
        // ID 999 (Non-existent)
        System.out.println("Searching for 999: " + findEmployee(corpData, 999)); 
    }
}
