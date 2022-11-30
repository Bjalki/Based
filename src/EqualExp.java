
public class EqualExp extends Exp {
	
	Exp left;
	Exp right;

	public EqualExp(Exp left, Exp right) {
		this.left = left;
		this.right = right;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve1 = left.eval(env);
		ValEnv ve2 = right.eval(ve1.env);
		Object val1 = ve1.val;
		Object val2 = ve2.val;
		boolean result = false;
		if(val1 == null) {
			result = (val2 == null);
		}
		else {
			result = val1.equals(val2);
		}
		return new ValEnv(result, ve2.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
