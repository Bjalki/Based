
public class Reg extends ASTNode {
	
	String label = null;
	Exp exp = null;

	@Override
	public ValEnv eval(Env env) {
		ValEnv result = exp.eval(env);
		result.env.addEval(label, result.val);
		return result;
	}
	
	@Override
	public void print() {
		if(label != null) {
			System.out.printf("%s := ", label);
		}
		exp.print();
	}

}
