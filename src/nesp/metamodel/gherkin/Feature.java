package nesp.metamodel.gherkin;

import java.util.*;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Feature {

	private String description;
	private String i_want;
	private String in_order_to;
	/**
	 * context
	 */
	public Background context;
	public List<Scenario> scenarios;

	public Feature(){
		scenarios = new ArrayList<>();
	}

	public void finalize() throws Throwable {

	}
}//end Feature