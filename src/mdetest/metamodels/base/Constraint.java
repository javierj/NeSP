package mdetest.metamodels.base;

/**
 * @author Rincew
 * @version 1.0
 * @created 13-abr-2010 12:11:25
 */
public class Constraint {

	private String value;

	/**
	 * Para clases hijas que no usan value.
	 */
	public Constraint(){
	}

	public Constraint(String value){
		this.value = value;
	}

	
	public void finalize() throws Throwable {

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[value=" + value + "]";
	}

}//end Constraint