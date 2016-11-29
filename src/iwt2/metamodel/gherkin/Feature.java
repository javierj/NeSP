package iwt2.metamodel.gherkin;

import java.util.*;

import mdetest.metamodels.functionalrequirement.FunctionalRequirement;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Feature {

	private String description;
	private String i_want;
	private String in_order_to;
	/** Trace relatio */
	private FunctionalRequirement fr;
	/**
	 * context
	 */
	public Background context;
	public List<Scenario> scenarios;

	
	public Feature(){
		scenarios = new ArrayList<>();
	}
	
	public String getI_want() {
		return i_want;
	}

	public void setIWant(String i_want) {
		this.i_want = i_want;
	}

	public String getInOrderTo() {
		return in_order_to;
	}

	public void setInOrderTo(String in_order_to) {
		this.in_order_to = in_order_to;
	}

	public Background getContext() {
		return context;
	}

	public void setContext(Background context) {
		this.context = context;
	}

	public List<Scenario> scenarios() {
		return Collections.unmodifiableList(scenarios);
	}

	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FunctionalRequirement getFunctionalRequirement() {
		return fr;
	}

	public void setFunctionalRequirement(FunctionalRequirement fr) {
		this.fr = fr;
	}
	
	
}//end Feature