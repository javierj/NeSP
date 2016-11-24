package iwt2.metamodel.gherkin;

import java.util.*;

/**
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

	public String getDescription() {
		return description;
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