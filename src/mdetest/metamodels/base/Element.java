package mdetest.metamodels.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rincew
 * @version 1.0
 * @created 13-abr-2010 12:11:26
 */
public class Element {

	/**
	 * Relaciones de trazabilidad con otros elementos
	 */
	private List<Element> trace;

	public Element(){
		trace = new ArrayList<Element>(6);
	}

	public void finalize() throws Throwable {

	}

	public void addTrace(Element e) {
		this.trace.add(e);
	}
	
	public List<Element> getTrace() {
		return this.trace;
	}
	
}//end Element