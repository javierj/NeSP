package iwt2.output;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import iwt2.metamodel.gherkin.Feature;
import iwt2.metamodel.gherkin.Given;
import iwt2.metamodel.gherkin.Scenario;
import iwt2.concretesyntax.eap.EAPScenariosDAO;
import mdetest.concretesyntax.eap.EAPConnectionFacade;
import mdetest.concretesyntax.eap.EAPFunctionalRequirementDAO;
import mdetest.concretesyntax.eap.EAPPackageDAO;
import mdetest.metamodels.functionalrequirement.FunctionalRequirement;
import mdetest.metamodels.functionalrequirement.Subsystem;

public class SaveToText {

	
	public void saveToText(String eapFile, String packageName) {
		EAPConnectionFacade.Connect(eapFile);
		EAPFunctionalRequirementDAO frDAO = EAPConnectionFacade.getEAPFunctionalRequirementDAO();
		EAPScenariosDAO scenariosDAO = new EAPScenariosDAO(EAPConnectionFacade.Connection());
		
		
		String pbId = this.idFor(packageName);
		List<FunctionalRequirement> frs =  frDAO.getAllFunctionalRequirementsIn(null, pbId);
		
		
		List<Feature> features = new ArrayList<>();
		Feature feature;
		
		for (FunctionalRequirement fr: frs) {
			feature = new Feature();
			feature.setFunctionalRequirement(fr);
			features.add(feature);
			
			//System.out.println(fr.getName() + ": " + fr.getInternalId());
			feature.setScenarios(scenariosDAO.getScenariosFor(fr));

		}

		// Volcarlos a una plantilla.
		

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        try {
			cfg.setDirectoryForTemplateLoading(new File("./resources/templates"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

        
        Template temp = null;
		try {
			temp = cfg.getTemplate("scenario_notes.freemaker");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        /* Create a data-model */

        for(Feature feat: features) {
        	Map<String, Object> root = new HashMap<>();
	        root.put("scenarios", feat.scenarios());
	        
        	
	        Writer out = new OutputStreamWriter(System.out);
	        try {
				temp.process(root, out);
			} catch (TemplateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	//-----------------------------------------------
	
	private String idFor(String packageName) {
		EAPPackageDAO packages = EAPConnectionFacade.getEAPPackageDAO();
		Subsystem sub = packages.getPackage(packageName);	
		return packages.getIdOfSubsystem(sub);
	}
	
	
	//------------------------------------------
	
	public static void main(String[] args) {
		SaveToText stot = new SaveToText();
		stot.saveToText("./test/resources/ForTesting 01.EAP", "UC - Tournament system");
	}
}
