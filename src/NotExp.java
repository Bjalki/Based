public class NotExp extends Exp {
	
	Exp exp;

	public NotExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp.eval(env);
		Object value = ve.val;
		if(!(value instanceof Boolean)) {
			System.err.println("~: expects type <boolean> as 1st argument");
			System.exit(12);
		}
		boolean result = !((boolean) value);
		return new ValEnv(result, ve.env);
	}

	@Override
	void print() {
		System.out.print("~(");
		exp.print();
		System.out.print(")");
	}

}
