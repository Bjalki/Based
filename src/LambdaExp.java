import java.util.ArrayList;

public class LambdaExp extends Exp {
	
	ArrayList<Label> labels = new ArrayList<>();
	Exp exp;

	@Override
	ValEnv eval(Env env) {
		Closure result = new Closure();
		Env closureEnv = new Env();
		for(Label l: labels) {
			closureEnv.addEval(l, null);
		}
		result.params = closureEnv;
		result.code = exp;
		return new ValEnv(result, env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
