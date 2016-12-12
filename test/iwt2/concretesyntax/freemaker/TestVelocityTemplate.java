package iwt2.concretesyntax.freemaker;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.Arrays;

import org.junit.Test;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import iwt2.metamodel.gherkin.Background;
import iwt2.metamodel.gherkin.Given;
import iwt2.metamodel.gherkin.Scenario;

public class TestVelocityTemplate {

	@Test
	public void renderBackgroung() {
		Scenario scen = createScenario();
        Background b = new Background();
        b.addStep(new Given("GIVEN action"));

		VelocityContext context = new VelocityContext();
		context.put("background", b);
		context.put("scenarios", Arrays.asList(scen));		
		
		VelocityEngine ve = new VelocityEngine();
		ve.init();

		Template t = ve.getTemplate("./resources/templates/scenarios.velocity");
		StringWriter w = new StringWriter();
		t.merge( context, w );

		System.out.println(w.toString());
        assertTrue(w.toString().contains("Background"));
        assertTrue(w.toString().contains("GIVEN"));
	}
	
	private Scenario createScenario() {
		Scenario scen = new Scenario();
		
		scen.setName("XYZ");
		
		return scen;
	}
	

}
