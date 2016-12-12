package iwt2.metamodel.gherkin;

import java.util.*;

/**
 * 
 * Actually all steps are stored in th description.
 * 
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Scenario {

	String name;
	private String description;
	
	List<Step> givens;
	List<Step> whens;
	List<Step> thens;

	public Scenario(){
		this.givens = new ArrayList<>();
		this.whens = new ArrayList<>();
		this.thens = new ArrayList<>();
		this.setDescription("Undescribed");
		this.setName("Unnamed");
	}

	/**
	 * Atually all the steps are stored in the description so we tabulate them
	 * @return
	 */
	public String getDescription() {
		String s = "";
		String space = "    ";
		String[] lines = this.description.split("\n");
		for(String line: lines) {
			s += space + line + "\n";
		}
		return s;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addStep(Step step) {
		if (step.isType(Given.GIVEN_TYPE)) {
			this.givens.add(step);
			return;
		}
		if (step.isType(When.WHEN_TYPE)) {
			this.whens.add(step);
			return;
		}
		if (step.isType(Then.THEN_TYPE)) {
			this.thens.add(step);
			return;
		}
		throw new java.lang.RuntimeException("Invalid step type");
	}
	
	public List<Step> givens() {
		return Collections.unmodifiableList(this.givens);
	}
	
	public List<Step> whens() {
		return Collections.unmodifiableList(this.whens);
	}
	
	public List<Step> thens() {
		return Collections.unmodifiableList(this.thens);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}//end Scenario