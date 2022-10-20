
public class NewlineExp extends Exp {

	@Override
	ValEnv eval(Env env) {
		System.out.println();
		return new ValEnv(null, env);
	}

	@Override
	void print() {
		System.out.print("(newline)");
	}

}
