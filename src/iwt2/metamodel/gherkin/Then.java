package iwt2.metamodel.gherkin;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Then extends Step {

	static int THEN_TYPE = 2;

	public Then(){
		super();
	}

	public Then(String substring) {
		this.setAction(substring);
	}

	@Override
	public boolean isType(int type) {
		return type == Then.THEN_TYPE;
	}

}//end Then