public class ExpAccess extends Access {
	
	Exp exp;

	public ExpAccess(Exp exp) {
		this.exp = exp;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp.eval(env);
		Object value = ve.val;
		if(value instanceof Integer) {
			int index = (Integer)value;
			try {
				value = ve.env.lookup(index);
			}
			catch(RuntimeException e) {
				System.err.print(e.getMessage());
				System.exit(4);
			}
		}
		else if(value instanceof String) {
			String id = (String) value;
			try {
				value = ve.env.lookup(id);
			}
			catch(RuntimeException e) {
				System.err.print(e.getMessage());
				System.exit(4);
			}
		}
		else if(value instanceof Loc) {
			Loc loc = (Loc) value;
			try {
				value = ve.env.lookup(loc.layer, loc.index);
			}
			catch(RuntimeException e) {
				System.err.print(e.getMessage());
				System.exit(4);
			}
		}
		else {
			System.err.print("access: incorrect type " + value.getClass().getName());
			System.exit(4);
		}
		if(next == null) {
			ve = new ValEnv(value, ve.env);
		}
		else if(!(value instanceof Env)) {
			System.err.print("access: dimension out of bounds");
			System.exit(5);
		}
		else {
			Env currEnv = ve.env;
			Env accessEnv = (Env) value;
			accessEnv.nextEnv = currEnv;
			accessEnv.layer = currEnv.layer + 1;
			ve = new ValEnv(next.eval(accessEnv).val, currEnv);
		}
		return ve;
	}

	@Override
	void print() {
		System.out.print("[");
		exp.print();
		System.out.print("]");
		if(next != null) {
			next.print();
		}
	}

}
