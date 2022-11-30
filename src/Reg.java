
public class Reg extends Line {
	
	Label label = null;
	Exp exp = null;

	@Override
	public ValEnv eval(Env env) {
		ValEnv result = exp.eval(env);
		try {
			result.env.addEval(label, result.val);
		}
		catch(RuntimeException e) {
			System.err.print(e.getMessage());
			System.exit(7);
		}
		return result;
	}
	
	@Override
	public void print() {
		if(label != null) {
			System.out.printf("%s := ", label.id);
		}
		exp.print();
	}

}
