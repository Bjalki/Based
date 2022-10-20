import java.util.ArrayList;

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
				value = env.lookup(index);
			}
			catch(RuntimeException e) {
				System.err.println(e.getMessage());
				System.exit(4);
			}
		}
		else if(value instanceof String) {
			String id = (String) value;
			try {
				value = env.lookup(id);
			}
			catch(RuntimeException runE) {
				System.err.println(runE.getMessage());
				System.exit(4);
			}
		}
		else {
			System.err.println("access: incorrect type " + value.getClass().getName());
			System.exit(4);
		}
		if(next == null) {
			ve = new ValEnv(value, ve.env);
		}
		else if(!(value instanceof ArrayList)) {
			System.err.println("access: dimension out of bounds");
			System.exit(5);
		}
		else {
			ArrayList<Object> list = (ArrayList<Object>) value;
			Env accessEnv = new Env(env, list);
			ve = new ValEnv(next.eval(accessEnv).val, env);
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
