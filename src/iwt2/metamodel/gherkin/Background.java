package iwt2.metamodel.gherkin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Si una feature tiene un background este es único, porque un brackground puede
 * contener todos los pasos que quieras no tiene sentido que haya más d euno.
 * @author Javier
 * @version 1.0
 * @created 17-nov.-2016 9:09:03
 */
public class Background {

	private String description;
	public List<Step> givens;

	public Background(){
		givens = new ArrayList<>();

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
		throw new java.lang.RuntimeException("Invalid step type");
	}
	
	public List<Step> givens() {
		return Collections.unmodifiableList(this.givens);
	}

}//end Background