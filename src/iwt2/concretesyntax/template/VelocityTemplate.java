package iwt2.concretesyntax.template;

import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class VelocityTemplate {


	Template template;
	 public static String TEMP_DIR = "./resources/templates";
	 VelocityContext context ;
	 
	public VelocityTemplate(String file) {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		context = new VelocityContext();

		template = ve.getTemplate(TEMP_DIR + "/" + file);

	}
	
	
	/**
	 * Adds an object to the context of the template
	 * @param key
	 * @param value
	 */
	public void putInContext(String key, Object value) {
		this.context.put(key, value);
	}
	 
	/**
	 * Renders the template with the context
	 * @return template rendered
	 */
	public String processToString() {
		StringWriter out = new StringWriter();
		template.merge( context, out );
		return out.toString();
		
	}

	
}
