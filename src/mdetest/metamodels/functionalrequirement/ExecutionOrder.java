package mdetest.metamodels.functionalrequirement;

import mdetest.metamodels.base.Constraint;
import mdetest.metamodels.base.Element;

/**
 * @author Rincew
 * @version 1.0
 * @created 13-abr-2010 12:11:26
 */
public class ExecutionOrder extends Element {

	private Constraint exceptionPoint;
	private Step source;
	private Step target;

	public ExecutionOrder(Step source, Step target){
		this.source = source;
		this.target = target;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @return
	 */
	public Constraint getExceptionPoint() {
		return exceptionPoint;
	}

	/**
	 * 
	 * @param exceptionPoint
	 */
	public void setExceptionPoint(Constraint exceptionPoint) {
		this.exceptionPoint = exceptionPoint;
	}

	public Step getSource() {
		return source;
	}

	public void setSource(Step source) {
		this.source = source;
	}

	public Step getTarget() {
		return target;
	}

	public void setTarget(Step target) {
		this.target = target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exceptionPoint == null) ? 0 : exceptionPoint.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExecutionOrder other = (ExecutionOrder) obj;
		if (exceptionPoint == null) {
			if (other.exceptionPoint != null)
				return false;
		} else if (!exceptionPoint.equals(other.exceptionPoint))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.source + " to " + this.target;
	}

}//end ExecutionOrder