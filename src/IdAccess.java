import java.util.ArrayList;

public class IdAccess extends Access {

	String id;

	public IdAccess(String id) {
		this.id = id;
	}

	@Override
	ValEnv eval(Env env) {
		Object value = null;
		ValEnv ve = null;
		try {
			value = env.lookup(id);
		}
		catch(RuntimeException runE) {
			System.err.println(runE.getMessage());
			System.exit(4);
		}
		if(next == null) {
			ve = new ValEnv(value, env);
		}
		else if(!(value instanceof ArrayList)) {
			System.err.println("access : dimension out of bounds");
			System.exit(5);
		}
		else {
			ArrayList<Object> list = (ArrayList<Object>) value;
			Env accessEnv = new Env(env, list);
			ve = new ValEnv(next.eval(accessEnv).val, env);
		}
		return ve;
	}

	@Override
	void print() {
		System.out.printf("[%s]", id);
		if(next != null) {
			next.print();
		}
	}

}
