import java.util.ArrayList;

public class LoopExp extends Exp {
	
	Exp exp1;
	Exp exp2;
	LambdaExp func;

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = func.eval(env);
		Closure funcClosure = (Closure)ve.val;
		
		Env results = new Env(env);
		
		ve = exp1.eval(ve.env);
		Object value = ve.val;
		
		//while
		if(value instanceof Boolean) {
			if(exp2 != null) {
				System.err.println("while loop: cannot have 2 exps");
				System.exit(11);
			}
			else if(funcClosure.params.values.size() > 1) {
				System.err.println("while loop: inner function cannot absorb params");
				System.exit(12);
			}
			boolean run = (Boolean)value;
			while(run) {
				Call runtime = new Call();
				runtime.head = func;
				ve = runtime.eval(ve.env);
				results.addEval(null, ve.val);
				ve = exp1.eval(ve.env);
				value = ve.val;
				if(!(value instanceof Boolean)) {
					System.err.println("while loop: run exp must remain a boolean during runtime");
					System.exit(13);
				}
				run = (Boolean)value;
			}
		}
		else if(value instanceof Integer) {
			ArrayList<Int> vals = new ArrayList<>();
			int num1 = (Integer) value;
			int start = -1;
			int end = -1;
			if(exp2 != null) {
				ve = exp2.eval(ve.env);
				value = ve.val;
				if(!(value instanceof Integer)) {
					System.err.println("for loop: exp2 must be of type <integer>");
					System.exit(14);
				}
				start = num1;
				end = (Integer) value;
			}
			else {
				start = 1;
				end = num1;
			}
			if(start <= end) {
				for(int i = start; i <= end; i++) {
					vals.add(new Int(i));
				}
			}
			else {
				for(int i = start; i >= end; i--) {
					vals.add(new Int(i));
				}
			}
			int nParams = funcClosure.params.values.size() - 1;
			int loops = (nParams > 0) ? vals.size() / nParams : vals.size();
			for(int i = 0; i < loops; i++) {
				Call runtime = new Call();
				runtime.head = func;
				for(int j = 0; j < nParams; j++) {
					runtime.params.add(vals.remove(0));
				}
				ve = runtime.eval(ve.env);
				results.addEval(null, ve.val);
			}
		}
		else if(value instanceof Env) {
			Env listEnv = (Env) value;
			ArrayList<ObjectExp> vals = new ArrayList<>();
			for(int i = 1; i < listEnv.values.size(); i++) {
				vals.add(new ObjectExp(listEnv.values.get(i)));
			}
			int nParams = funcClosure.params.values.size() - 1;
			int loops = (nParams > 0) ? vals.size() / nParams : vals.size();
			for(int i = 0; i < loops; i++) {
				Call runtime = new Call();
				runtime.head = func;
				for(int j = 0; j < nParams; j++) {
					runtime.params.add(vals.remove(0));
				}
				ve = runtime.eval(ve.env);
				results.addEval(null, ve.val);
			}
		}
		else {
			System.err.println("loop: exp1 not of a correct type (given " + value.getClass() + ")");
			System.exit(15);
		}
		return new ValEnv(results, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
