package iwt2.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import iwt2.concretesyntax.template.VelocityTemplate;
import iwt2.metamodel.gherkin.Feature;


/**
 * Warning. this class only saves features with scenarios.
 * 
 * @author Javier
 *
 */
public class SaveFeatures {

NeSPConfiguration config;
	
	public SaveFeatures(NeSPConfiguration config) {
		this.config = config;
	}
	
	public void save(List<Feature> features) {
		this.renderTemplate(features);
	}
	
	void saveToFile(String fileName, String body) {
		try(  PrintWriter out = new PrintWriter( this.config.featuresPath() + "/" +  fileName + ".feature" )  ){
		    out.println( body.trim());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void renderTemplate(List<Feature> features) {
		for (Feature feat: features) {
			
			if (feat.hasNotScearios()) {
				//System.out.println("");
				continue;
			}
			
			VelocityTemplate template = new VelocityTemplate("feature.velocity");
			template.putInContext("feat", feat);
			System.out.println(template.processToString()); // Save to file
			saveToFile(feat.getName(), template.processToString());
		}
	}

	
}
