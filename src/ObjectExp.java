
public class ObjectExp extends Exp {
	
	Object value;

	public ObjectExp(Object value) {
		this.value = value;
	}

	@Override
	ValEnv eval(Env env) {
		// TODO Auto-generated method stub
		return new ValEnv(value, env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
