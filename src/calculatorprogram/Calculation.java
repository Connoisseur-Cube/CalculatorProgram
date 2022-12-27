package calculatorprogram;

public class Calculation {

	
	public static double evaluate(String input) {
		String[] tokens = input.split("(?<=[-+*/])|(?=[-+*/])");
		
		double result = 0;
		
		for(int i = 0; i < tokens.length; i++) {
			
			// if token is a number
			if(tokens[i].matches("-?\\d+(\\.\\d+)?")) {
				double number = Double.parseDouble(tokens[i]);
				
				// if number is the first one
				if(i == 0) {
					result = number;
				}
				
				// if number is not the first one
				else {
					String operator = tokens[i-1];
					
					switch(operator) {
					case "+":
						result += number;
						break;
					case "-":
						result -= number;
						break;
					case "*":
						result *= number;
						break;
					case "/":
						result /= number;
						break;
					default:
						System.out.println("Error");
						break;
					}
				}
				
			}
		}
		
		return result;
	}

}