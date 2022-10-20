import java.util.Scanner;

public class ReadExp extends Exp {
	
	Scanner in;

	public ReadExp(Scanner in) {
		this.in = in;
	}

	@Override
	ValEnv eval(Env env) {
		String line = in.next();
		Object val = null;
		try {
			val = Integer.parseInt(line);
		}
		catch(NumberFormatException e) {
			if(line.equals("#t")) {
				val = true;
			}
			else if(line.equals("#f")) {
				val = false;
			}
			else val = line;
		}
		return new ValEnv(val, env);
	}

	@Override
	void print() {
		System.out.print("(read)");
	}

}
