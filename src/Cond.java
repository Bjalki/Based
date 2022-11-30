
public class Cond extends Exp {
	
	Exp pred;
	Exp then;
	Exp otherwise;

	public Cond(Exp e1, Exp e2, Exp e3) {
		pred = e1;
		then = e2;
		otherwise = e3;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = pred.eval(env);
		Object value = ve.val;
		if(value instanceof Boolean && !((boolean) value)) {
			if(otherwise != null) {
				ve = otherwise.eval(ve.env);
			}
			else {
				ve = new ValEnv(null, ve.env);
			}
		}
		else {
			ve = then.eval(ve.env);
		}
		return ve;
	}

	@Override
	void print() {
		System.out.print("(");
		pred.print();
		System.out.print(" ? ");
		then.print();
		if(otherwise != null) {
			System.out.print(" : ");
			otherwise.print();
		}
		System.out.print(")");
	}

}
