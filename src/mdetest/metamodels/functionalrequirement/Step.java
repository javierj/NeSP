package mdetest.metamodels.functionalrequirement;
import java.util.ArrayList;
import java.util.List;

import mdetest.metamodels.base.Element;

/**
 * @version 1.0
 * @created 13-abr-2010 12:11:31
 */
public class Step extends Element {

	private String action;
	private Boolean mainStep;
	private List<ExecutionOrder> in;
	private List<ExecutionOrder> out;
	private SystemActor executor;
	private FunctionalRequirement functionalRequirement;
	private FunctionalRequirement requirementReferenced;
	private Boolean finalStep;
	
	
	public Step(){
		this.in = new ArrayList<ExecutionOrder>(4);
		this.out = new ArrayList<ExecutionOrder>(4);
		this.mainStep = false;
		this.finalStep = false;
	}
	
	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Boolean getMainStep() {
		return mainStep;
	}

	public void setMainStep(Boolean mainStep) {
		this.mainStep = mainStep;
	}

	public List<ExecutionOrder> getIn() {
		return in;
	}

	public void setIn(List<ExecutionOrder> in) {
		this.in = in;
	}

	public List<ExecutionOrder> getOut() {
		return out;
	}

	public void setOut(List<ExecutionOrder> out) {
		this.out = out;
	}

	public SystemActor getExecutor() {
		return executor;
	}

	public void setExecutor(SystemActor executor) {
		this.executor = executor;
	}

	public FunctionalRequirement getFunctionalRequirement() {
		return functionalRequirement;
	}

	public void setFunctionalRequirement(FunctionalRequirement functionalRequirement) {
		this.functionalRequirement = functionalRequirement;
	}

	public FunctionalRequirement getRequirementReferenced() {
		return requirementReferenced;
	}

	public void setRequirementReferenced(FunctionalRequirement requirementReferenced) {
		this.requirementReferenced = requirementReferenced;
	}


	/**
	 * @return
	 */
	public boolean isFinalStep() {
		/*Boolean b = true;
		if (this.mainStep) {
			for (ExecutionOrder eo: this.out) {
				if (eo.getTarget().mainStep) {
					b = false;
					break;
				}
			}
		} else {
			b = this.out.size() == 0;
		}
		
		return b;*/
		
		return this.finalStep;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static Step GetFinalStep() {
		Step s = new Step();
		s.finalStep = true;
		s.action = "End.";
		return s;
	}
	
	
	
	public boolean equals(Object o) {
		Step aux = (Step) o;
		if (this.mainStep != aux.mainStep)
			return false;
		if (this.action.equals(aux.action) == false)
			return false;
		if (this.in.size() != aux.in.size())
			return false;
		if (this.out.size() != aux.out.size())
			return false;
		
		return true;
	}
	
	public String toString() {
		String s = this.action + "(";
		
		for (ExecutionOrder outEO: this.out) 
			s += "-" + outEO.getExceptionPoint() + "-->" +outEO.getTarget().getAction() + ", ";
		
		return s;
	}

}//end Step