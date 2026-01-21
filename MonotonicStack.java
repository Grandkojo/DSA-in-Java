import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MonotonicStack {

    public static List<Integer> daily_temperatures(int[] temperatures){
        int arrayLength = temperatures.length;
        Stack<Integer> monotonicStack = new Stack<>();
        List<Integer> daysToWait = new ArrayList<>(Collections.nCopies(arrayLength, 0)); //could use ant int array int[] result = new int[arrayLength]
        
        //loop through temparatures array
        for (int i = 0; i < arrayLength; i++){
            //if stack is not empty, and current temperature is greater than one on stack
            while (!monotonicStack.empty() && temperatures[i] > temperatures[monotonicStack.peek()]) {
                int previousIndex = monotonicStack.pop();
                // System.out.println(previousIndex);
                daysToWait.set(previousIndex, i - previousIndex);
            }
            monotonicStack.push(i);
        }
        return daysToWait;
    }
    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(daily_temperatures(temperatures));
    }
}
