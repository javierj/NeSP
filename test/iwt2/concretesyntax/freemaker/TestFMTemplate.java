package iwt2.concretesyntax.freemaker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;
import iwt2.concretesyntax.template.FMTemplate;
import iwt2.metamodel.gherkin.Background;
import iwt2.metamodel.gherkin.Given;
import iwt2.metamodel.gherkin.Scenario;


/**
 * Dont wotk
 * @author Javier
 *
 */
public class TestFMTemplate {

	@Test
	public void renderTemplateWithAnScenario() {
		Scenario scen = createScenario();
		
		Map<String, Object> root = this.createModel();
	        
        FMTemplate temp = new FMTemplate();
        temp.setTemplateFile("scenario_notes.freemaker");
        String res = temp.processToString(root);
        
        //System.out.println(res);
        assertNotNull(res);
        assertTrue(res.contains(scen.getName()));
	}

	@Test
	public void renderBackgroung() {
		Scenario scen = createScenario();
		
		Map<String, Object> root = this.createModel();
        
        
        FMTemplate temp = new FMTemplate();
        temp.setTemplateFile("scenario_notes.freemaker");
        String res = temp.processToString(root);
        
        System.out.println(res);
        assertNotNull(res);
        assertTrue(res.contains("Background"));
	}

	 

	private Map<String, Object> createModel() {
		Scenario scen = createScenario();
		
		DefaultObjectWrapperBuilder bulder = new DefaultObjectWrapperBuilder(new Version(2, 3, 22));
		DefaultObjectWrapper dow = bulder.build();
		
		Map<String, Object> root = new HashMap<>();
        root.put("scenarios", Arrays.asList(scen));		
        Background b = new Background();
        b.addStep(new Given("GIVEN action"));
        try {
			root.put("background", dow.wrap(b));
		} catch (TemplateModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return root;
	}
	
	private Scenario createScenario() {
		Scenario scen = new Scenario();
		
		scen.setName("XYZ");
		
		return scen;
	}

}
