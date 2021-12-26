import java.util.Stack;

public class calculator_updated {
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int newValue = 0;
        char currentOperator = '+';
        for (int i=0;i<s.length();i++) {
            char currentChar = s.charAt(i);
            if(Character.isDigit(currentChar)) {
                newValue = (newValue*10) + (currentChar-'0');
            }
            if(!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == s.length()-1) {
                if (currentOperator == '+') {
                    stack.push(newValue);
                } else if (currentOperator == '-') {
                    stack.push(-newValue);
                } else if (currentOperator == '*') {
                    stack.push(stack.pop() *newValue);
                } else if (currentOperator == '/') {
                    stack.push(stack.pop()/newValue);
                }
                currentOperator = currentChar;
                newValue = 0;
            }
        }
        int output = 0;
        while (!stack.isEmpty()) {
            output = output + stack.pop();
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println(calculate("1+2*5/3+6/4*2"));
    }

}
