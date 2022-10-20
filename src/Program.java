import java.util.ArrayList;

public class Program extends ASTNode {
	
	ArrayList<Reg> regList = new ArrayList<>();

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = new ValEnv(null, env);
		for (Reg r: regList) {
			ve = r.eval(ve.env);
		}
		return ve;
	}

	@Override
	void print() {
		for (Reg r: regList) {
			r.print();
			System.out.println();
		}
	}

}
