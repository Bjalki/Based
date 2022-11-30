
public class Label extends Exp {
	
	String id;

	public Label(String id) {
		this.id = id;
	}

	@Override
	ValEnv eval(Env env) {
		Loc loc = null;
		try {
			loc = env.lookupLoc(id);
		}
		catch(RuntimeException e) {
			System.err.print(e.getMessage());
			System.exit(6);
		}
		return new ValEnv(loc, env);
	}

	@Override
	void print() {
		System.out.print(id);
	}

}
