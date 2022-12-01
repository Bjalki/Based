
public class SizeExp extends Exp {
	
	Exp exp1;

	public SizeExp(Exp exp1) {
		this.exp1 = exp1;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp1.eval(env);
		Object value = ve.val;
		if(!(value instanceof Env)) {
			System.err.println("size: expects type <list> as 1st argument");
			System.exit(37);
		}
		Env list = (Env) value;
		return new ValEnv(list.values.size() - 1, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
