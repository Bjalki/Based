
public class AddExp extends Exp {
	
	Exp exp1;
	Exp exp2;

	public AddExp(Exp exp1, Exp exp2) {
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp1.eval(env);
		Object value = ve.val;
		if(!(value instanceof Env)) {
			System.err.println("add: expects type <list> as 1st argument");
			System.exit(13);
		}
		Env list = (Env) value;
		ve = exp2.eval(ve.env);
		value = ve.val;
		list.addEval(null, value);
		return new ValEnv(null, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
