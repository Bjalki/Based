public class LocAccess extends Access {
	
	Exp exp1;
	Exp exp2;

	public LocAccess(Exp exp1, Exp exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve1 = exp1.eval(env);
		ValEnv ve2 = exp2.eval(ve1.env);
		if(!(ve1.val instanceof Integer)) {
			System.err.println("assign(layer, index): expects type <integer> as 1st argument");
			System.exit(3);
		}
		if(!(ve2.val instanceof Integer)) {
			System.err.println("assign(layer, index): expects type <integer> as 2nd argument");
			System.exit(3);
		}
		
		int layer = (Integer) ve1.val;
		int index = (Integer) ve2.val;
		
		Object value = null;
		try {
			value = ve2.env.lookup(layer, index);
		}
		catch(RuntimeException e) {
			System.err.print(e.getMessage());
			System.exit(4);
		}
		
		if(next == null) {
			ve2 = new ValEnv(value, ve2.env);
		}
		else if(!(value instanceof Env)) {
			System.err.print("access: dimension out of bounds");
			System.exit(5);
		}
		else {
			Env currEnv = ve2.env;
			Env accessEnv = (Env) value;
			ve2 = new ValEnv(next.eval(accessEnv).val, currEnv);
		}
		return ve2;
	}

	@Override
	void print() {
		System.out.print("[");
		exp1.print();
		System.out.print(", ");
		exp2.print();
		System.out.print("]");
		if(next != null) {
			next.print();
		}
	}

}
