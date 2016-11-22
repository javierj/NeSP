package mdetest.metamodels.functionalrequirement;

import java.util.ArrayList;
import java.util.List;

import mdetest.metamodels.base.NamedElement;
import mdetest.metamodels.base.Constraint;


/**
 * @version 1.0
 * @created 13-abr-2010 12:11:27
 */
public class FunctionalRequirement extends NamedElement {


	
	private String priority;


	private List<Constraint> preconditions;
	private List<Constraint> posconditions;
	private List<Step> steps;
	private List<Step> isReferenced;
	private String notes;

	public FunctionalRequirement(){
		this.isReferenced = new ArrayList<Step>(10);
		this.posconditions = new ArrayList<Constraint>(5);
		this.preconditions = new ArrayList<Constraint>(5);
		this.steps = new ArrayList<Step>(15);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<Constraint> getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(List<Constraint> preconditions) {
		this.preconditions = preconditions;
	}

	public List<Constraint> getPosconditions() {
		return posconditions;
	}

	public void setPosconditions(List<Constraint> posconditions) {
		this.posconditions = posconditions;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public List<Step> getIsReferenced() {
		return isReferenced;
	}

	public void setIsReferenced(List<Step> isReferenced) {
		this.isReferenced = isReferenced;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * 
	 * @param c
	 */
	public void addPoscondition(Constraint c) {
		posconditions.add(c);
	}
	
	/**
	 * 
	 * @param s
	 */
	public void addStep(Step s) {
		this.steps.add(s);
	}

	
	
	

	@Override
	public String toString() {
		return (getInPackage() != null ? getInPackage()
						+ ", " : "") +"::"
				+ (getName() != null ?  getName() : "")
				+ (notes != null ? "notes=" + notes + ", " : "")
				+ (posconditions != null ? "posconditions=" + posconditions
						+ ", " : "")
				+ (preconditions != null ? "preconditions=" + preconditions
						+ ", " : "")
				+ (priority != null ? "priority=" + priority + ", " : "")
				 + "]\n"
				 + "Steps: " + this.steps + "\n\n";
	}
}//end FunctionalRequirement