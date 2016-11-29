package iwt2.concretesyntax.freemaker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import iwt2.metamodel.gherkin.Scenario;

public class TestFMTemplate {

	@Test
	public void renderTemplateWithAnScenario() {
		Scenario scen = createScenario();
		
		Map<String, Object> root = new HashMap<>();
        root.put("scenarios", Arrays.asList(scen));
        
        FMTemplate temp = new FMTemplate();
        temp.setTemplateFile("scenario_notes.freemaker");
        String res = temp.processToString(root);
        
        System.out.println(res);
        assertNotNull(res);
        assertTrue(res.contains(scen.getName()));
	}

	
	private Scenario createScenario() {
		Scenario scen = new Scenario();
		
		scen.setName("XYZ");
		return scen;
	}

}
