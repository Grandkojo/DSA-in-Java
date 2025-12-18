import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    
    //rule: every open parenthesis '(' must have a matching close ')'
    /**
     * for n pairs, it becomes 2n, i.e n opens + n closes
     * the choices i have at each point is to close an open one, or open a new one
     * when n= 1 => [()], when n = 2 => [(()), ()()]
     * i need to track how many open and closes i've used so far
     * i'd decide to add a ')' when there ')' count for n is less than the open ones.
     * backtracking is add->recurse->remove
     */



    /**
     * rightParenthesis(ncount, curString, openParenthesisCount, closeParenthesisCount){
     *        if (openParenthesisCount == ncount)
     *          if (closeParenthesisCount < openParenthesisCount)
     *              curString + ')'
     *              closeParenthesisCount++            
     *          return curString
     *       curString + '('
     *       openParenthesisCount++
     *            
     *       rightParenthesis(ncount, curString, openParenthesisCount, closeParenthesisCount)
     * }
     */

    /**
     * refactor
     * rightParenthesis(curString, openParenthesisCount, closeParenthesisCount){
     *      //base case
     *      if (openParenthesisCount == ncount and closeParenthesisCount == ncount)
     *          add curString to results
     *          return
     * 
     *      //choice 1
     *      if (openParenthesisCount < ncount)
     *          curString + '('
     *          rightParenthesis(curString, openParenthesisCount+1, closeParenthesisCount)
     *          remove the last '('
     *      
     *      //choice 2
     *      if (closeParenthesisCount < openParenthesisCount)
     *          curString + ')'
     *          rightParenthesis(curString, openParenthesisCount, closeParenthesisCount+1)
     *          remove the last ')'
     * }
     * 
     */

    /**
     * 
     * @param nCount
     * @param curString
     * @param openParenthesisCount
     * @param closeParenthesisCount
     * @param result
     */

    /**
     *  Level 0: ""               open=0 close=0
        └── Can add '('? Yes (0 < 3)
            → Add '(' → "("        open=1 close=0

            Level 1: "("           open=1 close=0
            ├── Can add '('? Yes → "(("        open=2 close=0
            │   Level 2: "(("      open=2 close=0
            │   ├── Add '(' → "((("        open=3 close=0
            │   │   Level 3: "(((" open=3 close=0
            │   │   └── Can't add '(', so add ')' → "((()"  open=3 close=1
            │   │       → "((())"  open=3 close=2
            │   │       → "((()))" open=3 close=3 → VALID! → add "((()))"
            │   │       backtrack...
            │   └── After trying '(', now try ')' at level 2
            │       → "(())"   open=2 close=1
            │       → "(())("  open=3 close=1
            │       → "(())()" open=3 close=2 → VALID! → add "(())()"
            │       backtrack...
            ├── Back to level 1: now try closing early
                → "()"             open=1 close=1
                Level 2: "()"      open=1 close=1
                ├── Add '(' → "()("        open=2 close=1
                │   → "()(("       open=3 close=1
                │   → "()(()"      open=3 close=2
                │   → "()(())"     open=3 close=3 → VALID! → add "()(())"
                └── After trying '(', now try ')' at level 2
                    → "()()"       open=1 close=2 → close > open? No! Invalid path → dies here
                    (but we already got "()(())" from the other branch)

            Now go even earlier — from original "(" (level 1), we already tried both branches

        Now go back to level 0 — we only added '(' once, now try adding ')'? 
        → close=0, open=0 → 0 < 0? No → cannot close first → good!

        So only one starting move: '('
     */

    public static void rightParenthesis(int nCount, StringBuilder curString, int openParenthesisCount, int closeParenthesisCount, List<String> result){
        if ((openParenthesisCount == nCount) && (closeParenthesisCount == nCount)){
            result.add(curString.toString());
        }

        //choice 1
        if (openParenthesisCount < nCount){
            curString.append("(");
            rightParenthesis(nCount, curString, openParenthesisCount+1, closeParenthesisCount, result);
            curString.setLength(curString.length() -1);

        }

        //choice 2
        if (closeParenthesisCount < openParenthesisCount){

            curString.append(")");
            rightParenthesis(nCount, curString, openParenthesisCount, closeParenthesisCount+1, result);
            curString.setLength(curString.length() -1);
        }
    }


    // String[] availableParenthesis = new String[]{"(", ")"};

    
    public static void generateParenthesis(int count){
        List<String> result = new ArrayList<>();
        StringBuilder curString = new StringBuilder();

        rightParenthesis(count, curString, 0, 0, result);
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        generateParenthesis(2);
    }

}
