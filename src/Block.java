import java.util.ArrayList;

public class Block extends Exp {
	
	ArrayList<Reg> regList = new ArrayList<>();

	@Override
	ValEnv eval(Env env) {
		Env blockEnv = new Env(env);
		ValEnv ve = new ValEnv(null, blockEnv);
		for (Reg r: regList) {
			ve = r.eval(ve.env);
		}
		ve = new ValEnv(ve.env.values, env);
		return ve;
	}

	@Override
	void print() {
		System.out.print("{");
		for (int i = 0; i < regList.size(); i++) {
			regList.get(i).print();
			if(i < regList.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print("}");
	}

}
