package calculatorprogram;

import java.util.Stack;

public class Calculation {
  public static void main(String[] args) {
    String calculation = "-2.5+2-1";
    System.out.println(evaluate(calculation));
  }

  public static double evaluate(String calculation) {
    Stack<Double> values = new Stack<>();
    Stack<Character> operations = new Stack<>();

    boolean negative = false;
    for (int i = 0; i < calculation.length(); i++) {
      char c = calculation.charAt(i);

      // If the character is a "-" sign, set the negative flag
      if (c == '-') {
        negative = true;
      } else {
        // If the character is a digit, parse the number and push it to the values stack
        if (Character.isDigit(c) || c == '.') {
          StringBuilder sb = new StringBuilder();
          while (i < calculation.length() && (Character.isDigit(c) || c == '.')) {
            sb.append(c);
            i++;
            if (i < calculation.length()) {
              c = calculation.charAt(i);
            }
          }
          i--;
          double value = Double.parseDouble(sb.toString());
          if (negative) {
            value = -value;
            negative = false;
          }
          values.push(value);
        } else if (c == '(') {
          operations.push(c);
        } else if (c == ')') {
          while (operations.peek() != '(') {
            values.push(applyOperation(operations.pop(), values.pop(), values.pop()));
          }
          operations.pop();
        } else if (isOperator(c)) {
          while (!operations.isEmpty() && hasPrecedence(c, operations.peek())) {
            values.push(applyOperation(operations.pop(), values.pop(), values.pop()));
          }
          operations.push(c);
        }
      }
    }

    while (!operations.isEmpty()) {
      values.push(applyOperation(operations.pop(), values.pop(), values.pop()));
    }

    return values.pop();
  }

  // Returns true if the operator is one of +, -, *, or /
  public static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }

  // Returns true if op2 has higher or same precedence as op1
  public static boolean hasPrecedence(char op1, char op2) {
    if (op2 == '(' || op2 == ')') {
      return false;
    }
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
      return false;
    }
    return true;
  }

  // Applies the operation to the two given values
  public static double applyOperation(char operation, double b, double a) {
    switch (operation) {
      case '+':
        return a + b;
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        if (b == 0) {
          throw new UnsupportedOperationException("Cannot divide by zero");
        }
        return a / b;
      default:
         return 0;
    }
  }
  
}