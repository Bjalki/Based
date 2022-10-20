
public class Int extends Literal {
	
	int i;

	public Int(int i) {
		this.i = i;
	}
	
	public Int(String s) {
		i = Integer.parseInt(s);
	}

	@Override
	ValEnv eval(Env env) {
		return new ValEnv(i, env);
	}

	@Override
	void print() {
		System.out.print(i);
	}

}
