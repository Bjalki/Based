
public class RemoveExp extends Exp {
	
	Exp exp1;
	Exp exp2;

	public RemoveExp(Exp exp1, Exp exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp1.eval(env);
		Object value = ve.val;
		if(!(value instanceof Env)) {
			System.err.println("remove: expects type <list> as 1st argument");
			System.exit(97);
		}
		Env list = (Env) value;
		ve = exp2.eval(ve.env);
		value = ve.val;
		if(!(value instanceof Integer)) {
			System.err.println("remove: expects type <int> as 2nd argument");
			System.exit(98);
		}
		int i = (int) value;
		try {
			value = list.removeAtIndex(i);
		}
		catch(RuntimeException e) {
			System.err.println("remove: index out of bounds (1-based-indexing) [" + i + ((i == 0) ? " (0 is reserved for null)" : "") + "]");
			System.exit(99);
		}
		return new ValEnv(value, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
