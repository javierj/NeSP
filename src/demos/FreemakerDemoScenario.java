package demos;

import java.io.*;
import java.util.*;

import freemarker.template.*;
import nesp.metamodel.gherkin.Given;
import nesp.metamodel.gherkin.Scenario;

public class FreemakerDemoScenario {

	
	    public static void main(String[] args) throws Exception {

	        /* ------------------------------------------------------------------------ */
	        /* You should do this ONLY ONCE in the whole application life-cycle:        */

	        /* Create and adjust the configuration singleton */
	        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
	        cfg.setDirectoryForTemplateLoading(new File("./resources/templates"));
	        cfg.setDefaultEncoding("UTF-8");
	        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	        cfg.setLogTemplateExceptions(false);

	        /* ------------------------------------------------------------------------ */
	        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

	        /* Create a data-model */
	        Map root = new HashMap();
	        
	        Scenario scen = new Scenario();
	        scen.addStep(new Given("Given step"));
	        root.put("givens", scen.givens());
	        root.put("whens", new ArrayList());
	        root.put("thens", new ArrayList());

	        /* Get the template (uses cache internally) */
	        Template temp = cfg.getTemplate("basic_scenario.freemaker");

	        /* Merge data-model with template */
	        Writer out = new OutputStreamWriter(System.out);
	        temp.process(root, out);
	        // Note: Depending on what `out` is, you may need to call `out.close()`.
	        // This is usually the case for file output, but not for servlet output.
	    }
	
}
