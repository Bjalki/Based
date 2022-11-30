import java.util.*;


public class Env implements Cloneable{
	HashMap<String, Integer> labels = new HashMap<>();
	ArrayList<Object> values;
	Env nextEnv = null;
	
	int layer;
	
	public Env(Env nextEnv, ArrayList<Object> vals) {
		this.nextEnv = nextEnv;
		values = vals;
	}
	
	public Env(Env nextEnv) {
		if(nextEnv == null) {
			layer = 0;
		}
		else {
			layer = nextEnv.layer + 1;
		}
		this.nextEnv = nextEnv;
		values = new ArrayList<Object>();
		values.add(null);
	}
	
	public Env() {
		this(null);
	}
	
	public void addEval(Label label, Object value) {
		if(label != null) {
			Integer last = labels.put(label.id, values.size());
			if(last != null) {
				throw new RuntimeException("Duplicate label: " + label.id);
			}
		}
		values.add(value);
	}
	
	public Loc lookupLoc(String label) {
		Integer index = labels.get(label);
		if (index != null)
			return new Loc(layer, index);
		else if (nextEnv == null)
			throw new RuntimeException("Undefined label: " + label);
		else {
			return nextEnv.lookupLoc(label);
		}
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
	
	public boolean contains(Object obj) {
		return values.contains(obj);
	}
	
	public Object lookup(int layer, int index) {
		if(this.layer == layer) {
			return lookup(index);
		}
		else if (nextEnv == null || layer < 0 || layer > this.layer)
			throw new RuntimeException("Invalid layer (out of bounds): current " + this.layer + " given " + layer);
		else {
			return nextEnv.lookup(layer, index);
		}
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
	
	public void assign(String label, Object value) {
		Integer index = labels.get(label);
		if (index != null)
			values.set(index, value);
		else if (nextEnv == null)
			throw new RuntimeException("Undefined label: " + label);
		else
			nextEnv.assign(label, value);
	}
	
	public void assign(Loc loc, Object value) {
		if(loc.layer > layer) {
			throw new RuntimeException("Invalid layer: " + loc.layer);
		}
		else if(loc.layer == layer) {
			values.set(loc.index, value);
		}
		else {
			nextEnv.assign(loc, value);
		}
	}
	
	public void assign(int i, Object value) {
		values.set(i, value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		Env clone = null;
		try {
			clone = (Env)super.clone();
			clone.labels = (HashMap<String, Integer>)labels.clone();
			clone.values = (ArrayList<Object>)values.clone();
			clone.nextEnv = null;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return clone;
	}
}
