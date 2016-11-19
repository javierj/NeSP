package iwt2.metamodel.gherkin;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public abstract class Step {

	/**
	 * action attribute is a description of the action of the step
	 */
	private String action;
	
	public Step(){
		setAction("undescribed step");
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	public String toString() {
		return this.action;
	}
	
	
	/**
	 * This method asks the step is if a type Givem, when or then.
	 * Use the type constaints defined in each son class.
	 * 
	 * @param type use the constans in the specifit types of steps
	 * @return
	 */
	public abstract boolean isType(int type);

}//end Step