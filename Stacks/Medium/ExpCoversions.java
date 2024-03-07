package Stacks.Medium;

import java.util.Stack;

@SuppressWarnings("unused")

public class ExpCoversions {

     // Function to determine precedence of operators
    private static int precedence(char op) {
        if (op == '+' || op == '-')         return 1;
        else if (op == '*' || op == '/')    return 2;
        else if (op == '^')                 return 3;
        else if (op == '(' || op == ')')    return 0;
        else                                return -1;
    }

    /* Infix to PostFix
     * Input -> (3-2+l/8)-(4+6/3*(5^8))
     * Output -> 32-l8/+463/58^*+-
     */

    private static String infixToPostFix(String exp){
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < exp.length() ; i++ ){
            char curr = exp.charAt(i);

            if(precedence(curr) == -1) res.append(curr); // number or variable , not a symbol
            else if(curr == '(') st.push(curr);
            else if(curr == ')'){
                while(!st.isEmpty() && st.peek() != '(') res.append(st.pop()); // add all the operators used in the ()
                st.pop(); // pop '('
            }
            else{// If the character is an operator
                // Pop operators with higher or equal precedence from the stack and append to postfix
                while(!st.isEmpty() && precedence(st.peek()) >= precedence(curr)) res.append(st.pop());
                // Push the current operator onto the stack
                st.push(curr);
            }
        }
        // Pop remaining operators from stack and append to postfix
        for( ; !st.isEmpty() ; st.pop()) res.append(st.peek());

        return res.toString();
    }

    /* Infix to prefix
     * Input: (A – B/C) * (A/K-L)
     * Output: *-A/BC-/AKL
     */
    private static String infixToPrefix(String exp) {
        // Step 1: Reverse the infix expression
        StringBuilder reversedInfix = new StringBuilder(exp).reverse();

        // Step 2: Modify the parentheses
        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);
            if (c == '(') {
                reversedInfix.setCharAt(i, ')');
            } else if (c == ')') {
                reversedInfix.setCharAt(i, '(');
            }
        }

        // Step 3: Convert reversed infix to postfix
        StringBuilder reversedPostfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : reversedInfix.toString().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                reversedPostfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    reversedPostfix.append(stack.pop());
                }
                stack.pop(); // Discard the opening parenthesis
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    reversedPostfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            reversedPostfix.append(stack.pop());
        }

        // Step 4: Reverse the postfix expression to get prefix
        return reversedPostfix.reverse().toString();
    }

    /* Prefix to Infix
     * Input :  /-ab+-cde
     * Output: ((a-b)/((c-d)+e))
     */

    private static String prefixToInfix(String exp){
        Stack<String> stack = new Stack<>();

        // Iterate through the prefix expression from end to start
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (isOperand(c)) {
                // If operand, push onto stack
                stack.push(String.valueOf(c));
            } else {
                // If operator, pop two operands from stack and create sub-expression
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String subExpression = "(" + operand1 + c + operand2 + ")";
                // Push the sub-expression back onto the stack
                stack.push(subExpression);
            }
        }

        // Final infix expression will be on top of the stack
        return stack.pop();
    }

    /* Prefix to postfix 
     * Input : /A+BC
     * Output : ABC+/
     */

    private static String prefixToPostfix(String exp) {
        Stack<String> stack = new Stack<>();

        // Iterate through the prefix expression
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (isOperand(c)) {
                // If operand, push onto stack
                stack.push(String.valueOf(c));
            } else {
                // If operator, pop two operands from stack and concatenate them with the operator
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String postfixExpression = operand1 + operand2 + c;
                // Push the resulting postfix expression back onto the stack
                stack.push(postfixExpression);
            }
        }

        // Final postfix expression will be on top of the stack
        return stack.pop();
    }

    /* postfix to prefix
     * Input: abc*+
     * Output: +a*bc
     */

    private static String postfixToPrefix(String exp) {
        Stack<String> stack = new Stack<>();

        // Iterate through the postfix expression
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (isOperand(c)) {
                // If operand, push onto stack
                stack.push(String.valueOf(c));
            } else {
                // If operator, pop two operands from stack and concatenate them with the operator
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String prefixExpression = c + operand1 + operand2;
                // Push the resulting prefix expression back onto the stack
                stack.push(prefixExpression);
            }
        }

        // Final prefix expression will be on top of the stack
        return stack.pop();
    }

    /* postfix to infix
     * Input: ‘postfix’ = “ab+c+”
     * Output: ‘infix’ = “((a+b)+c)”
     */

    private static String postFixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();

        // Iterate through the postfix expression
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (isOperand(c)) {
                // If operand, push onto stack
                stack.push(String.valueOf(c));
            } else {
                // If operator, pop two operands from stack and concatenate them with the operator
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String infixExpression = "(" + operand1 + c + operand2 + ")";
                // Push the resulting infix expression back onto the stack
                stack.push(infixExpression);
            }
        }

        // Final infix expression will be on top of the stack
        return stack.pop();
    }

    // Function to check if a character is an operand
    private static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }
    public static void main(String[] args) {
        
        // infix to postfix
        String exp = "(3-2+l/8)-(4+6/3*(5^8))";   // expedpted : "32-l8/+463/58^*+-"
        System.out.println("infix : " + exp);
        System.out.println("postfix : "+ infixToPostFix(exp));

        // infix to prefix
        exp = "(A-B/C)*(A/K-L)";  // expected : "*-A/BC-/AKL"
        System.out.println("infix : "+exp);
        System.out.println("prefix : "+ infixToPrefix(exp));

        // System.out.println(prefixToInfix("*-A/BC-/AKL"));

    }
    
}
