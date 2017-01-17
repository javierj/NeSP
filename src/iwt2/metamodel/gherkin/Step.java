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
		this.action = action.trim();
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
	
	/**
	 * Factory method to create steps from strings
	 * @param step
	 * @return
	 */
	public static Step createStep(String stepText) {
		String lower = stepText.toLowerCase();
		
		if (lower.startsWith("given")) {
			return new Given(stepText.substring(6));
		}
		if (lower.startsWith("when")) {
			return new When(stepText.substring(5));
		}
		if (lower.startsWith("then")) {
			return new Then(stepText.substring(5));
		}
		throw new java.lang.RuntimeException("Invalid step type: " + stepText);
	}

}//end Step