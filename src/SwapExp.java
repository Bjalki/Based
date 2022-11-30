
public class SwapExp extends Exp {
	
	Exp exp1;
	Exp exp2;
	Exp exp3;

	public SwapExp(Exp exp1, Exp exp2, Exp exp3) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.exp3 = exp3;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp1.eval(env);
		Object value = ve.val;
		if(!(value instanceof Env)) {
			System.err.println("swap: expects type <list> as 1st argument");
			System.exit(13);
		}
		Env list = (Env) value;
		ve = exp2.eval(ve.env);
		value = ve.val;
		if(!(value instanceof Integer)) {
			System.err.println("swap: expects type <integer> as 2nd argument");
			System.exit(14);
		}
		int i = (int) value;
		ve = exp3.eval(ve.env);
		value = ve.val;
		if(!(value instanceof Integer)) {
			System.err.println("swap: expects type <integer> as 3rd argument");
			System.exit(15);
		}
		int j = (int) value;
		Object temp = list.lookup(i);
		list.assign(i, list.lookup(j));
		list.assign(j, temp);
		return new ValEnv(null, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
