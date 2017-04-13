package iwt2.util;

import java.util.List;

import iwt2.cucumber.Launcher;
import iwt2.metamodel.gherkin.Feature;
import iwt2.transformations.GherkinScenariosToText;

public class NeSPCli {

	public static void main(String[] args) throws Throwable {
		NeSPConfiguration config = new NeSPConfiguration();
		
		config.setFeaturesPath("./test/features_genetared");
		
		GherkinScenariosToText stot = new GherkinScenariosToText(config);
		List<Feature> features = stot.transform("./test/resources/Scenarios 03.EAP", "UC - Tournament system");
		
		System.out.println("Features: " + features.size());
		new SaveFeatures(config).save(features);
		new Launcher().launch(config);
		
		
		
		System.out.println("never run");
	}
	
}
