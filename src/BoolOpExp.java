
public class BoolOpExp extends Exp {
	
	String operator;
	Exp operand1, operand2;

	public BoolOpExp(String operator, Exp operand1, Exp operand2) {
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve1 = operand1.eval(env);
		if(!(ve1.val instanceof Boolean)) {
			System.err.println(operator + ": expects type <boolean> as 1st argument");
			System.exit(3);
		}
		
		boolean a = (Boolean) ve1.val;
		
		if(operator.equals("&&") && a == false) {
			return new ValEnv(false, ve1.env);
		}
		else if(operator.equals("||") && a == true) {
			return new ValEnv(true, ve1.env);
		}
		
		ValEnv ve2 = operand2.eval(ve1.env);
		if(!(ve2.val instanceof Boolean)) {
			System.err.println(operator + ": expects type <boolean> as 2nd argument");
			System.exit(3);
		}
		
		boolean b = (Boolean) ve2.val;
		
		boolean result = false;
		if(operator.equals("&&")) {
			result = a && b;
		}
		else if(operator.equals("||")) {
			result = a || b;
		}
		else {
			throw new RuntimeException("Invalid boolean operator: " + operator);
		}
		
		return new ValEnv(result, ve2.env);
	}

	@Override
	void print() {
		System.out.printf("(%s ", operator);
		operand1.print();
		System.out.print(" ");
		operand2.print();
		System.out.print(")");
	}

}
