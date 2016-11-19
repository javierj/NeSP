package iwt2.metamodel.gherkin;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Given extends Step {

	static int GIVEN_TYPE = 0;
	public Given(){
		super();
		this.setAction("Undefined");
	}
	
	
	public Given(String string) {
		this();
		this.setAction(string);
	}


	@Override
	public boolean isType(int type) {
		return type == Given.GIVEN_TYPE;
	}
	

}//end Given