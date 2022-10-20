import java.util.*;


public class Env {
	HashMap<String, Integer> labels = new HashMap<>();
	ArrayList<Object> values;
	Env nextEnv = null;
	
	public Env(Env nextEnv, ArrayList<Object> vals) {
		this.nextEnv = nextEnv;
		values = vals;
	}
	
	public Env(Env nextEnv) {
		this.nextEnv = nextEnv;
		values = new ArrayList<Object>();
		values.add(null);
	}
	
	public Env() {
		this(null);
	}
	
	public void addEval(String label, Object value) {
		if(label != null) {
			Integer last = labels.put(label, values.size());
			if(last != null) {
				throw new RuntimeException("Duplicate label: " + label);
			}
		}
		values.add(value);
	}
	
	public Object lookup(String label) {
		Integer index = labels.get(label);
		if (index != null)
			return values.get(index);
		else if (nextEnv == null)
			throw new RuntimeException("Undefined label: " + label);
		else
			return nextEnv.lookup(label);
	}
	
	public Object lookup(int i) {
		if(i < 0) {
			throw new RuntimeException("Invalid index (less than zero): " + i);
		}
		if (i < values.size())
			return values.get(i);
		else
			throw new RuntimeException("Invalid index (out of bounds): given " + i + ", size " + values.size());
	}
	
	public void assign(String label, Object value) {
		Integer index = labels.get(label);
		if (index != null)
			values.set(index, value);
		else if (nextEnv == null)
			throw new RuntimeException("Undefined label: " + label);
		else
			nextEnv.assign(label, value);
	}
}
