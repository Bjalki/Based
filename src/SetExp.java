
public class SetExp extends Exp {
	
	Exp exp1;
	Exp exp2;
	Exp exp3;

	public SetExp(Exp exp1, Exp exp2, Exp exp3) {
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.exp3 = exp3;
	}

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = exp1.eval(env);
		Object value = ve.val;
		if(!(value instanceof Env)) {
			System.err.println("swap: expects type <list> as 1st argument");
			System.exit(16);
		}
		Env list = (Env) value;
		Loc loc = null;
		if(exp2 instanceof Label) {
			Label l = (Label) exp2;
			loc = list.lookupLoc(l.id);
		}
		else {
			ve = exp2.eval(ve.env);
			value = ve.val;
			if(value instanceof Integer) {
				loc = new Loc(list.layer, (int) value);
			}
			else if(value instanceof Loc) {
				loc = (Loc) loc;
			}
			else {
				System.err.println("swap: expects type <integer> or <location> as 2nd argument");
				System.exit(17);
			}
		}
		
		ve = exp3.eval(ve.env);
		value = ve.val;
		list.assign(loc, value);
		return new ValEnv(null, ve.env);
	}

	@Override
	void print() {
		// TODO Auto-generated method stub

	}

}
