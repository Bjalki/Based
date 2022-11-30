
public class Bool extends Literal {
	
	boolean b;

	public Bool(boolean b) {
		this.b = b;
	}
	
	public Bool(String s) {
		b = !s.equals("#f");
	}

	@Override
	ValEnv eval(Env env) {
		return new ValEnv(b, env);
	}

	@Override
	void print() {
		System.out.print(b ? "#t" : "#f");
	}

}
