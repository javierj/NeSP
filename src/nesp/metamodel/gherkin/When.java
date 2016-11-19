package nesp.metamodel.gherkin;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class When extends Step {

	static int WHEN_TYPE = 1;

	public When(){
		super();
	}

	@Override
	public boolean isType(int type) {
		return type == When.WHEN_TYPE;
	}
	
	

}//end When