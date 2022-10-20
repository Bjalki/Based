
public class WriteExp extends Exp {
	
	Exp exp;

	public WriteExp(Exp e) {
		this.exp = e;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp.eval(env);
		System.out.print(ve.val);
		return new ValEnv(null, ve.env);
	}

	@Override
	void print() {
		System.out.print("(write ");
		exp.print();
		System.out.print(")");
	}

}
