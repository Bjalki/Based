
public class AssignExp extends Exp {
	
	Exp data;
	Exp location;

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = location.eval(env);
		Object value = ve.val;
		if(!(value instanceof Loc)) {
			System.err.println("assign: expected location, given non-location");
			System.exit(9);
		}
		Loc loc = (Loc) value;
		ve = data.eval(ve.env);
		value = ve.val;
		try {
			env.assign(loc, value);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(10);
		}
		return new ValEnv(loc, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
