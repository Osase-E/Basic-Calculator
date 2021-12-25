
import java.util.ArrayList;
import java.util.Arrays;

public class calculator {
    private static ArrayList<String> splitStr;
    private static ArrayList<String> operatorOrder;

    public static int calculate(String s) {
        splitStr = new ArrayList<String>(Arrays.asList(s.trim().split("\\W+")));
        String operators = s.replaceAll("[^+\\-\\*/]", "");
        operatorOrder = new ArrayList<String>(Arrays.asList(operators.split("")));
        int newValue = 0;
        while (splitStr.size()>1) {
            if (operatorOrder.contains("*") || operatorOrder.contains("/")) {
                int temp_div_op = operatorOrder.indexOf("/") == -1 ? Integer.MAX_VALUE : operatorOrder.indexOf("/");
                int temp_mult_op = operatorOrder.indexOf("*") == -1 ? Integer.MAX_VALUE : operatorOrder.indexOf("*");
                int i = temp_div_op < temp_mult_op ? temp_div_op : temp_mult_op;
                int firstInt = Integer.valueOf(splitStr.get(i));
                int secondInt = Integer.valueOf(splitStr.get(i+1));
                switch (operatorOrder.get(i)) {
                    case "*":
                        fixString(i, multiply(firstInt,secondInt));
                        break;
                    default:
                        fixString(i, divide(firstInt,secondInt));
                }
                continue;
            }

            if (operatorOrder.contains("+") || operatorOrder.contains("-")) {
                int i = operatorOrder.indexOf("-");
                if (i == -1) {
                    i = operatorOrder.indexOf("+");
                }
                int firstInt = Integer.valueOf(splitStr.get(i));
                int secondInt = Integer.valueOf(splitStr.get(i+1));
                switch (operatorOrder.get(i)) {
                    case "+":
                        fixString(i, add(firstInt,secondInt));
                        break;
                    default:
                        fixString(i, sub(firstInt,secondInt));
                }
            }
        }
        return Integer.valueOf(splitStr.get(0));
    }

    public static int divide(int a, int b) {
        return a/b;
    }

    public static int add(int a, int b) {
        return a+b;
    }

    public static int sub(int a, int b) {
        return a-b;
    }

    public static int multiply(int a, int b) {
        return a*b;
    }

    public static void fixString(int i, int newValue) {
        splitStr.remove(i);
        splitStr.remove(i);

        splitStr.add(i, String.valueOf(newValue));
        operatorOrder.remove(i);
    }

    public static void main(String[] args) {
        System.out.println(calculate("1+2*5/3+6/4*2"));
    }

}
