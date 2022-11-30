import java.util.ArrayList;

public class Block extends Exp {
	
	ArrayList<Line> lineList = new ArrayList<>();
	String prefix;
	
	public Block(String prefix) {
		this.prefix = prefix;
	}

	@Override
	ValEnv eval(Env env) {
		Env blockEnv = new Env(env);
		ValEnv ve = new ValEnv(null, blockEnv);
		for (Line l: lineList) {
			ve = l.eval(ve.env);
		}
		ve = new ValEnv(ve.env, env);
		return ve;
	}

	@Override
	void print() {
		System.out.print("{");
		for (int i = 0; i < lineList.size(); i++) {
			lineList.get(i).print();
			if(i < lineList.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print("}");
	}

}
