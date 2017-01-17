package iwt2.metamodel.gherkin;

import java.util.*;

import mdetest.metamodels.functionalrequirement.FunctionalRequirement;

/**
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Feature {

	private String name;
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
		this.i_want = i_want.trim();
	}

	public String getInOrderTo() {
		return in_order_to;
	}

	public void setInOrderTo(String in_order_to) {
		this.in_order_to = in_order_to.trim();
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
		if (this.description == null) {
			return null;
		}
		return this.description;
		/* - Esto no debe estar aquí
		String s = "";
		String space = "    ";
		String[] lines = this.description.split("\n");
		for(String line: lines) {
			s += space + line + "\n";
		}
		return s;*/
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public FunctionalRequirement getFunctionalRequirement() {
		return fr;
	}

	public void setFunctionalRequirement(FunctionalRequirement fr) {
		this.fr = fr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addScenario(Scenario my_scenario) {
		scenarios.add(my_scenario); 
		
	}

	
	
}//end Feature