import java.util.ArrayList;

public class Call extends Exp {
	
	Exp head;
	ArrayList<Exp> params = new ArrayList<>();

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = head.eval(env);
		Object val = ve.val;
		if(!(val instanceof Closure)) {
			System.err.println("call: expected procedure, given non-procedure");
			System.exit(7);
		}
		Closure func = (Closure)val;
		//attach local env to current env
		Env funcEnv = (Env)func.params.clone();
		funcEnv.nextEnv = ve.env;
		funcEnv.layer = ve.env.layer + 1;
		
		int n = funcEnv.values.size() - 1;
		if(n != params.size()) {
			System.err.println("call: param length not equivalent: given " + params.size() + " expected " + n);
			System.exit(8);
		}
		for(int i = 1; i <= n; i++) {
			ve = params.get(i - 1).eval(ve.env);
			funcEnv.values.set(i, ve.val);
		}
		ValEnv result = func.code.eval(funcEnv);
		return new ValEnv(result.val, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
