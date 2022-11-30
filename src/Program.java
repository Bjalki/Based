import java.util.ArrayList;

public class Program extends ASTNode {
	
	ArrayList<Line> lineList = new ArrayList<>();

	@Override
	ValEnv eval(Env env) {
		ValEnv ve = new ValEnv(null, env);
		for (Line l: lineList) {
			ve = l.eval(ve.env);
		}
		return ve;
	}

	@Override
	void print() {
		for (Line l: lineList) {
			l.print();
			System.out.println();
		}
	}

}
